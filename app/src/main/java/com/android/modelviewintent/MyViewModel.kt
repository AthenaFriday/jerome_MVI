import androidx.lifecycle.ViewModel
import com.android.MyIntent
import com.android.MyViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(MyViewState())
    val viewState: StateFlow<MyViewState> = _viewState

    fun processIntent(intent: MyIntent) {
        when (intent) {
            is MyIntent.LoadData -> loadData()
            is MyIntent.DataReceived -> updateData(intent.data)
            is MyIntent.ErrorOccurred -> handleError(intent.error)
        }
    }

    private fun loadData() {
        _viewState.value = MyViewState(isLoading = true)

        // Simulate data fetching or API call
        val data = "Fetched Data" // Replace with real data
        processIntent(MyIntent.DataReceived(data))
    }

    private fun updateData(data: String) {
        _viewState.value = MyViewState(data = data)
    }

    private fun handleError(error: String) {
        _viewState.value = MyViewState(error = error)
    }
}
