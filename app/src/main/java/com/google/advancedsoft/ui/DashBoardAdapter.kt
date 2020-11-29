package com.google.advancedsoft.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.advancedsoft.R
import com.google.advancedsoft.network.DashBoardUiModel

class DashBoardAdapter : BaseRecyclerAdapter<RecyclerView.ViewHolder, DashBoardUiModel>() {
    override fun constructViewHolder(itemView: View, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.rectangle_item -> RectangleViewHolder(itemView)
            R.layout.box_item -> BoxViewHolder(itemView)
            R.layout.profile_item -> ProfileViewHolder(itemView)

            else -> throw IllegalAccessException("viewType is not handled")
        }
    }

    override fun getResLayout(viewType: Int): Int = viewType

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RectangleViewHolder -> {
                holder.bind(getItemAs(position))
            }
            is BoxViewHolder -> {
                holder.bind(getItemAs(position))
            }
            is ProfileViewHolder -> {
                holder.bind(getItemAs(position))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is DashBoardUiModel.ProfileUi -> R.layout.profile_item
            is DashBoardUiModel.RectangleSection -> R.layout.rectangle_item
            is DashBoardUiModel.BoxSection -> R.layout.box_item
        }
    }
}