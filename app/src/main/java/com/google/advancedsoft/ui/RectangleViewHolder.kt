package com.google.advancedsoft.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.advancedsoft.network.DashBoardUiModel
import kotlinx.android.synthetic.main.rectangle_item.view.*

class RectangleViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: DashBoardUiModel.RectangleSection) = with(itemView) {
        title.text = item.title
        total.text = item.total.toString()
        icon.setImageResource(item.icon)
        recived.text = item.received.toString()
        sent.text = item.sent.toString()

    }
}