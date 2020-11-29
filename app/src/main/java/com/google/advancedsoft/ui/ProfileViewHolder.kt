package com.google.advancedsoft.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.advancedsoft.network.DashBoardUiModel
import kotlinx.android.synthetic.main.profile_item.view.*

class ProfileViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: DashBoardUiModel.ProfileUi) = with(itemView) {
        starsRecived.text = item.profile.stars_received.toString()
        starsSent.text = item.profile.stars_sent.toString()
        name.text = item.profile.name
        desigation.text = item.profile.desigation
    }

}
