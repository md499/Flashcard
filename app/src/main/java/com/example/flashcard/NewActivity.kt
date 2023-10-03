package com.example.flashcard

import FlashcardViewModel
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class NewActivity : AppCompatActivity() {

    private lateinit var problemTextView: TextView
    private lateinit var nameText: TextView
    private lateinit var nextButton: Button
    private lateinit var generateButton: Button
    private lateinit var answerField: EditText
    private lateinit var playButton: Button


    private val problems = mutableListOf<String>()
    private var currentProblemIndex = 0
    private var score = 0

    private lateinit var viewModel: FlashcardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_acitivity)

        viewModel = ViewModelProvider(this).get(FlashcardViewModel::class.java)
        // Retrieve the current problem text and index from the ViewModel
        val currentProblemText = viewModel.currentProblemText
        currentProblemIndex = viewModel.currentProblemIndex
        score = viewModel.score

        val size = 10

        problemTextView = findViewById(R.id.problem)
        nextButton = findViewById(R.id.next)
        generateButton = findViewById(R.id.generate)
        answerField = findViewById(R.id.answer)
        playButton = findViewById(R.id.repeat)
        nameText = findViewById(R.id.nameText)
        nameText.text = intent.getStringExtra("username")

        val quitButton = findViewById<Button>(R.id.quit) as Button

        currentProblemIndex = viewModel.currentProblemIndex
        score = viewModel.score

        generateProblems()
        nextButton.isEnabled = false
        playButton.isEnabled = false
        quitButton.isEnabled = false


        generateButton.setOnClickListener {
            displayCurrentProblem()
            generateButton.isEnabled = false
            playButton.isEnabled = false
            nextButton.isEnabled = true
        }


        nextButton.setOnClickListener {
            val userAnswerText = answerField.text.toString()
            Log.d("UserAnswerText", "User Answer Text: $userAnswerText")

            //check if user presses next on blank output...
           if (userAnswerText.isEmpty()) {
               val msg ="Please enter your answer!"
                Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
           }

            else{
               val userAnswerInt = userAnswerText.toInt()
               if (checkAnswer(userAnswerInt)) {
                   score++
               }

               if (currentProblemIndex < problems.size - 1) {
                   currentProblemIndex++
                   displayCurrentProblem()
                   answerField.text.clear()

               } else {
                   //problemTextView.text = "No more problems"
                   val scoreStr = "Score: $score out of 10"
                   Toast.makeText(this, scoreStr, Toast.LENGTH_SHORT).show()
                   //generateButton.isEnabled = true
                   answerField.text.clear()
                   playButton.isEnabled = true
                   nextButton.isEnabled = false
                   quitButton.isEnabled = true

               }

           }
        }

        playButton.setOnClickListener {
            score = 0
            currentProblemIndex = 0
            generateButton.isEnabled = true
            problems.clear()
            generateProblems()
            nextButton.isEnabled = false
            quitButton.isEnabled = true
           // playButton.isEnabled = false
            //displayCurrentProblem()

        }

        quitButton.setOnClickListener {
            finish()
        }
    }


            private fun generateProblems() {
        // Generate 10 problems and add them to the list
        for (i in 1..10) {
            val random1 = (1..99).random()
            val random2 = (1..20).random()
            val operation = if (i % 2 == 0) "+" else "-"
            val problem = "$random1 $operation $random2"
            problems.add(problem)

        }
        problems.shuffle()
    }

    private fun displayCurrentProblem() {
        if (currentProblemIndex < problems.size) {
            val currentProblem = problems[currentProblemIndex]
            problemTextView.text = "Problem ${currentProblemIndex + 1}: $currentProblem"
            // Update the problem text in the ViewModel
            viewModel.currentProblemText = currentProblem
        }
    }

    private fun checkAnswer(userAnswer: Int): Boolean {
        val current = problems[currentProblemIndex]
        var parts = current.split(" ")
        if (parts.size == 3) {
            val operand1 = parts[0].toInt()
            val operator = parts[1]
            val operand2 = parts[2].toInt()

            val correctAnswer = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                else -> null
            }

            val userAnswerVal = userAnswer.toInt()
            if (userAnswerVal == correctAnswer) {
                return true
            }
            return false
        }
        return false
    }

}