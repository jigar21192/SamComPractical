package com.example.samcomprac

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samcomprac.adapter.DataAdapter
import com.example.samcomprac.databinding.ActivityMainBinding
import com.example.samcomprac.model.DataModel
import com.example.samcomprac.retrofit.Resource
import com.example.samcomprac.viewModel.DataViewModel
import com.example.samcomprac.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataViewModel by viewModels {
        ViewModelFactory(application)
    }

    private var dataList = ArrayList<DataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setObserver()
        viewModel.getDataCall()
    }

    private fun setRecyclerView(dataList: ArrayList<DataModel>) {
        binding.rvData.layoutManager = LinearLayoutManager(this)
        binding.rvData.adapter = DataAdapter(this, dataList,object :DataAdapter.OnClickListener{
            override fun onClick(position: Int) {

//                startActivity(
//                    Intent(this@MainActivity,DetailsActivity::class.java)
//                    .putExtra("data",dataList[position]))
            }
        })

    }

    private fun setObserver() {
        viewModel.getData().observe(this) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pvProgress.visibility= View.GONE
                    it.data?.let { it1 -> dataList=it1 }
                    setRecyclerView(dataList)
                }
                Resource.Status.LOADING -> {
                    if(dataList.size==0)
                    binding.pvProgress.visibility= View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    binding.pvProgress.visibility= View.GONE
                    it.let {
                        val builder = AlertDialog.Builder(this)
                        builder.setTitle(getString(R.string.alert))
                        builder.setMessage(getString(R.string.some_wrong))

                        builder.setPositiveButton("Ok") { dialogInterface, which ->
                            dialogInterface.dismiss()
                        }
                        builder.setNegativeButton("") { dialogInterface, which ->

                        }
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.setCancelable(true)
                        alertDialog.show()

                    }
                }
            }
        }
    }
}