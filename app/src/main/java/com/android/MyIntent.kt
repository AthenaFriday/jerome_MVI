package com.android

sealed class MyIntent {
    object LoadData : MyIntent()
    data class DataReceived(val data: String) : MyIntent()
    data class ErrorOccurred(val error: String) : MyIntent()
}
