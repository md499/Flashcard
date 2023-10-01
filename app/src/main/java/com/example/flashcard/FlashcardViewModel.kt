import androidx.lifecycle.ViewModel

class FlashcardViewModel : ViewModel() {
    var currentProblemText: String? = null
    var currentProblemIndex = 0
    var score = 0
}