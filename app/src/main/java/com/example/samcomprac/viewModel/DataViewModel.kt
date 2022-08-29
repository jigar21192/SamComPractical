package com.example.samcomprac.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.samcomprac.model.DataModel
import com.example.samcomprac.retrofit.ApiServiceHelper
import com.example.samcomprac.retrofit.Resource
import com.example.samcomprac.room.MyDao
import com.example.samcomprac.room.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DataViewModel(context: Application) : BaseViewModel(context) {
    private var database: MyDatabase?
    private val getAllData = MutableLiveData<Resource<ArrayList<DataModel>>>()
    init {
         database = MyDatabase.getInstance(context)
        loadFromDb()
    }

    fun getDataCall() {
        viewModelScope.launch {
            try {
                getAllData.postValue(Resource.loading(null))
                val usersFromApi = ApiServiceHelper.getData()
                if (usersFromApi?.isSuccessful == true) {
                    getAllData.postValue(Resource.success(usersFromApi.body()))
                    saveToRoomDb(usersFromApi.body())
                }else {
                    getAllData.postValue(
                        Resource.error(
                            super.errorHandler(usersFromApi?.code()!!, usersFromApi.errorBody()!!),
                            null
                        )
                    )

                }


            } catch (e: Exception) {
                e.message?.let { Log.e("Error", it) }
            }
        }
    }

    private fun saveToRoomDb(data: ArrayList<DataModel>?) {
        viewModelScope.launch(Dispatchers.IO) {
                for (item in data!!){
                        val id=database?.myDao()?.getItemId(item.Id!!)
                        if(id==null){
                            database?.myDao()?.insert(item)
                        }else{
                            database?.myDao()?.update(item)
                        }
                }
        }
    }

    private fun loadFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
                getAllData.postValue(
                    Resource.success(
                        database?.myDao()
                            ?.getAllData() as ArrayList<DataModel> /* = java.util.ArrayList<com.example.samcomprac.model.DataModel> */
                    )
                )
        }
    }



    fun getData(): LiveData<Resource<ArrayList<DataModel>>> = getAllData

}
