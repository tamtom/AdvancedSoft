package com.google.advancedsoft.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
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
                    val adapter = DashBoardAdapter()
                    adapter.updateDataItems(it.data)
                    val manager = GridLayoutManager(this, 2)
                    manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return when (adapter.getItemViewType(position)) {
                                R.layout.profile_item, R.layout.rectangle_item -> 2
                                else -> 1
                            }
                        }

                    }
                    rvDashboard.adapter = adapter
                    rvDashboard.layoutManager = manager
                }
                is AppResult.Failure -> {
                }
            }
        })
        viewModel.fetchData()

    }
}