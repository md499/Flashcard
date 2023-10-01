package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText;
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import kotlin.random.Random;


class NewActivity : AppCompatActivity() {

    private lateinit var problemTextView: TextView
    private lateinit var nextButton: Button
    private lateinit var generateButton: Button
    private lateinit var answerField: EditText

    private val problems = mutableListOf<String>()
    private var currentProblemIndex = 0
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_acitivity)


        val op2 = findViewById<TextView>(R.id.ops) as TextView
        val size = 10

        problemTextView = findViewById(R.id.problem)
        nextButton = findViewById(R.id.next)
        generateButton = findViewById(R.id.generate)
        answerField = findViewById(R.id.answer)


        generateProblems()
        nextButton.isEnabled = false


        generateButton.setOnClickListener {
            displayCurrentProblem()
            generateButton.isEnabled = false
            nextButton.isEnabled = true
        }


        nextButton.setOnClickListener {
            val userAnswerText = answerField.text.toString()
            val userAnswerInt = userAnswerText.toInt()
            if (checkAnswer(userAnswerInt)) {
                score++
            }
            if (currentProblemIndex < problems.size - 1) {
                currentProblemIndex++
                displayCurrentProblem()
            } else {
                problemTextView.text = "No more problems"
                val scoreStr = "Score: $score out of 10"
                Toast.makeText(this, scoreStr, Toast.LENGTH_SHORT).show()

            }
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
        val currentProblem = problems[currentProblemIndex]
        problemTextView.text = "Problem ${currentProblemIndex + 1}: $currentProblem"
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






