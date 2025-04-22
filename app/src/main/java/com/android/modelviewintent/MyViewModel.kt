package com.android.modelviewintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.MyIntent
import com.android.MyViewState

class MyViewModel : ViewModel() {
    private val _viewState = MutableLiveData<MyViewState>()
    val viewState: LiveData<MyViewState> = _viewState

    fun processIntent(intent: MyIntent) {
        when (intent) {
            is MyIntent.LoadData -> loadData()
            is MyIntent.DataReceived -> updateData(intent.data)
            is MyIntent.ErrorOccurred -> handleError(intent.error)
        }
    }

    private fun loadData() {
        // Update the state to show loading
        _viewState.value = MyViewState(isLoading = true)

        // Simulate data fetching (e.g., call repository or API)
        // For now, we'll simulate the success path
        val data = "Fetched Data"  // You would replace this with actual data
        processIntent(MyIntent.DataReceived(data))
    }

    private fun updateData(data: String) {
        // Update the state to show the fetched data
        _viewState.value = MyViewState(data = data)
    }

    private fun handleError(error: String) {
        // Update the state to show an error
        _viewState.value = MyViewState(error = error)
    }
}
