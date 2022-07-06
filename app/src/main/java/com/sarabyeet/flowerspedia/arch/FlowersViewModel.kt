package com.sarabyeet.flowerspedia.arch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sarabyeet.flowerspedia.arch.FlowersRepository

class FlowersViewModel:ViewModel() {
    private val repository = FlowersRepository()

    var flowersListLiveData = MutableLiveData<List<String>>()

    var selectedFlowerLetterLiveData = MutableLiveData<String>()

    fun init(context: Context) {
        val list = repository.parseFlowersList(context)
        flowersListLiveData.postValue(list)
    }
}