package com.google.advancedsoft.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.advancedsoft.network.DashBoardUiModel

class DashBoardAdapter : BaseRecyclerAdapter<RecyclerView.ViewHolder, DashBoardUiModel>() {
    override fun constructViewHolder(itemView: View, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getResLayout(viewType: Int): Int = viewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is DashBoardUiModel.ProfileUi -> TODO()
            is DashBoardUiModel.RectangleSection -> TODO()
            is DashBoardUiModel.BoxSection -> TODO()
        }
    }
}