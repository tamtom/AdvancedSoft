package com.google.advancedsoft.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.advancedsoft.HomeRepo
import com.google.advancedsoft.R
import com.google.advancedsoft.base.BaseViewModel
import com.google.advancedsoft.network.AppResult
import com.google.advancedsoft.network.DashBoardUiModel
import com.google.advancedsoft.network.HomeResponse

class DashBoardViewModel(private val homeRepo: HomeRepo) : BaseViewModel() {
    private val _uiSectionsLiveData: MutableLiveData<AppResult<List<DashBoardUiModel>>> =
        MutableLiveData()
    val uiSectionsLiveData: LiveData<AppResult<List<DashBoardUiModel>>> = _uiSectionsLiveData

    fun fetchData() {
        executeAndNotify(_uiSectionsLiveData) {
            val response = homeRepo.fetchDashboard()
            mapDashboardResponseToUiResponse(response)
        }
    }

    private fun mapDashboardResponseToUiResponse(response: HomeResponse): List<DashBoardUiModel> {
        val uiList = mutableListOf<DashBoardUiModel>()
        uiList.add(DashBoardUiModel.ProfileUi(response.profile))
        uiList.add(
            DashBoardUiModel.RectangleSection(
                "E-Thank you Card",
                response.cardSection.received_cards,
                response.cardSection.sent_cards,
                response.cardSection.total_cards,
                R.drawable.icon_ethankyou
            )
        )
        uiList.add(
            DashBoardUiModel.BoxSection(
                "Stars",
                response.starsSection.received_stars,
                response.starsSection.sent_stars,
                response.starsSection.total_stars,
                R.drawable.icon_stars
            )
        )
        uiList.add(
            DashBoardUiModel.BoxSection(
                "Certificate", response.certSection.received_cert, response.certSection.sent_cert,
                response.certSection.total_cert, R.drawable.icon_certificate
            )
        )
        uiList.add(  DashBoardUiModel.BoxSection(
            "Gift", response.giftsSection.received_gifts, response.giftsSection.sent_gifts,
            response.giftsSection.total_gifts, R.drawable.icon_gift
        ))
        uiList.add(DashBoardUiModel.BoxSection(
            "Keys", response.keySectionSection.received_received_key, response.keySectionSection.sent_sent_key,
            response.keySectionSection.total_total_key, R.drawable.icon_ekey
        ))
        return uiList
    }

}