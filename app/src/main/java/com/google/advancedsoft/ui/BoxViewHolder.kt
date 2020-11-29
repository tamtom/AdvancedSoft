package com.google.advancedsoft.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.advancedsoft.network.DashBoardUiModel
import kotlinx.android.synthetic.main.box_item.view.*

class BoxViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: DashBoardUiModel.BoxSection) = with(itemView) {
        title.text = item.title
        total.text = item.total.toString()
        icon.setImageResource(item.icon)
        recived.text = item.received.toString()
        sent.text = item.sent.toString()
        background.setColorFilter(
            list[adapterPosition-2],
            PorterDuff.Mode.SRC_ATOP
        );

    }

    val list = listOf<Int>(Color.GREEN, Color.BLUE, Color.BLACK, Color.RED)
}