package com.android.modelviewintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.MyViewState
import com.android.modelviewintent.ui.MyView

class MyActivity : AppCompatActivity(), MyView {
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        // Observe the ViewState and update the UI
        viewModel.viewState.observe(this, Observer { state ->
            render(state)
        })
    }

    override fun render(state: MyViewState) {
        // Update the UI based on the current state
        if (state.isLoading) {
            // Show loading spinner
        } else if (state.error != null) {
            // Show error message
        } else {
            // Show the data
        }
    }
}
