package com.example.flashcard
import FlashcardViewModel
import androidx.lifecycle.SavedStateHandle
import org.junit.Test

class FlashcardViewModelTest {
    @Test
    fun providesExpectedInitialState() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = FlashcardViewModel(savedStateHandle)

        assert(quizViewModel.currentProblemText == null )
        assert(quizViewModel.score == 0)
        assert(quizViewModel.currentProblemIndex== 0)
    }

}

