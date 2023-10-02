import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class FlashcardViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var currentProblemText: String? = null
    var currentProblemIndex = 0
    var score = 0


}


