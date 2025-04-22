// MyView.kt
package com.android.modelviewintent.ui

import com.android.MyViewState

interface MyView {
    // Abstract render method without @Composable
    fun render(state: MyViewState)
}
