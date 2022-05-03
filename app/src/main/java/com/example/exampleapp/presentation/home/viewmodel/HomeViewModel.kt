package com.example.exampleapp.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exampleapp.domain.interactor.GetCards
import com.example.exampleapp.model.Cards
import com.example.exampleapp.presentation.common.model.ResourceState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val getCards: GetCards) : ViewModel() {

    private val cardListLiveData: MutableLiveData<ResourceState<List<Cards>>> = MutableLiveData()
    var disposable: Disposable? = null

    val selectedCard = MutableLiveData<Cards>()

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }

    fun getCards(): LiveData<ResourceState<List<Cards>>> {
        return cardListLiveData
    }

    fun fetchCards() {
        cardListLiveData.postValue(ResourceState.loading())

        disposable = getCards.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ cards ->
                cardListLiveData.postValue(ResourceState.success(cards))
            }, { throwable ->
                throwable.localizedMessage?.let {
                    cardListLiveData.postValue(ResourceState.error(it))
                }
            })
    }

}