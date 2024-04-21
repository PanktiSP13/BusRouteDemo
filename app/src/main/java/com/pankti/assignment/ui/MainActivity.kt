package com.pankti.assignment.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pankti.assignment.R
import com.pankti.assignment.databinding.ActivityMainBinding
import com.pankti.assignment.domain.BusDataUIModel
import com.pankti.assignment.domain.BusRouteViewModel
import com.pankti.assignment.ui.adapter.BusRouteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BusRouteAdapter
    private val viewModel: BusRouteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observeData()
        performClick()
    }

    private fun observeData() {
        viewModel.busDataModel.observe(this) {
            setBusRouteData(it)
        }
    }

    private fun performClick() {
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    private fun setBusRouteData(list: List<BusDataUIModel>) {
        if (::adapter.isInitialized) {
            adapter.updateData(list)
        } else {
            adapter = BusRouteAdapter(list)
            binding.rvBusRouteList.adapter = adapter
        }
    }
}

