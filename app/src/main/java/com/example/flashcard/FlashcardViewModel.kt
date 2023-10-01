package com.example.flashcard


import androidx.lifecycle.ViewModel

class FlashcardViewModel : ViewModel() {
    var currentProblemIndex = 0
    var score = 0
    var currentProblemText: String? = null
}
