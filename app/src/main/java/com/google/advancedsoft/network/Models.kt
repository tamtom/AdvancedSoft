package com.google.advancedsoft.network

import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class StarsSection(
    @SerializedName("total_stars") val total_stars: Int,
    @SerializedName("sent_stars") val sent_stars: Int,
    @SerializedName("received_stars") val received_stars: Int
)

data class Profile(

    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("desigation") val desigation: String,
    @SerializedName("stars_sent") val stars_sent: Int,
    @SerializedName("stars_received") val stars_received: Int
)

data class KeySection(

    @SerializedName("total_key") val total_total_key: Int,
    @SerializedName("sent_key") val sent_sent_key: Int,
    @SerializedName("received_key") val received_received_key: Int
)

data class CardSection(

    @SerializedName("total_cards") val total_cards: Int,
    @SerializedName("sent_cards") val sent_cards: Int,
    @SerializedName("received_cards") val received_cards: Int
)

data class CertSection(

    @SerializedName("total_cert") val total_cert: Int,
    @SerializedName("sent_cert") val sent_cert: Int,
    @SerializedName("received_cert") val received_cert: Int
)

data class GiftsSection(
    @SerializedName("total_gifts") val total_gifts: Int,
    @SerializedName("sent_gifts") val sent_gifts: Int,
    @SerializedName("received_gifts") val received_gifts: Int
)

data class HomeResponse(
    @SerializedName("is_active") val is_active: Boolean,
    @SerializedName("color") val color: String,
    @SerializedName("last_card_date") val last_card_date: String,
    @SerializedName("profile") val profile: Profile,
    @SerializedName("card_section") val cardSection: CardSection,
    @SerializedName("stars_section") val starsSection: StarsSection,
    @SerializedName("cert_section") val certSection: CertSection,
    @SerializedName("gifts_section") val giftsSection: GiftsSection,
    @SerializedName("key_section") val keySectionSection: KeySection
)

sealed class DashBoardUiModel {
    data class ProfileUi(val profile: Profile) : DashBoardUiModel()
    data class RectangleSection(
        val title: String,
        val received: Int,
        val sent: Int,
        val total: Int,
        @DrawableRes val icon: Int
    ) : DashBoardUiModel()

    data class BoxSection(
        val title: String,
        val received: Int,
        val sent: Int,
        val total: Int,
        @DrawableRes val icon: Int
    ) : DashBoardUiModel()

}
