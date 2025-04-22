package com.android.modelviewintent

import MyViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.MyIntent
import com.android.MyViewState
import com.android.modelviewintent.ui.MyView

// Main Activity class
class MyActivity : ComponentActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel using Compose's viewModel() function
        viewModel = viewModel()

        // Set content using Jetpack Compose
        setContent {
            // Observe the viewState from the ViewModel and pass it to the composable function
            val state = viewModel.viewState.collectAsState().value

            // MyScreen is a composable function that takes state and viewModel to render the UI
            MyScreen(state = state, viewModel = viewModel)
        }
    }
}

// Composable function to display UI
@Composable
fun MyScreen(state: MyViewState, viewModel: MyViewModel) {
    // Box allows for stacking UI components (ProgressBar, Error message, Data)
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Show the ProgressBar when data is loading
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {
            // Show error message if there's an error
            Text(
                text = state.error,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error
            )
        } else if (state.data != null) {
            // Show the data when it's available
            Text(
                text = state.data,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Add a button to trigger LoadData intent
        Button(onClick = {
            // Trigger the LoadData intent to fetch data
            viewModel.processIntent(MyIntent.LoadData)
        }) {
            Text(text = "Load Data")
        }
    }
}

// Preview of the UI to see the layout
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // This preview is showing the UI when data is loaded
    MyScreen(state = MyViewState(isLoading = false, data = "Fetched Data", error = null), viewModel = MyViewModel())
}
