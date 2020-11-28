package com.google.advancedsoft.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.advancedsoft.R
import com.google.advancedsoft.network.AppResult
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashBoardActivity : AppCompatActivity() {
    val viewModel: DashBoardViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.uiSectionsLiveData.observe(this, Observer {
            when (it) {
                AppResult.Loading -> progress.visibility = View.VISIBLE
                is AppResult.Success -> {
                    progress.visibility = View.GONE
                    print(it.data.toString())
                }
                is AppResult.Failure -> {}
            }
        })
        viewModel.fetchData()

    }
}