package com.example.samcomprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.samcomprac.databinding.ActivityDetailsBinding
import com.example.samcomprac.model.DataModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var dataModel:DataModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        dataModel=intent.getSerializableExtra("data") as DataModel
        binding.model=dataModel
    }
}