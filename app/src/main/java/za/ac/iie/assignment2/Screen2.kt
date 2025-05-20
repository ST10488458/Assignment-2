package za.ac.iie.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class Screen2 : AppCompatActivity() {

    private val questions = arrayOf(
        "Italy won the World Cup in 2006",
        "Apple has manufactured a car",
        "The Chicago Bulls have the most NBA championships",
        "Neil Armstrong was the first person on the moon",
        "Brazil has 3 World Cups"
    )

    private val answers = booleanArrayOf(true, false, false, true, false)

    private var currentQuestionIndex = 0
    private var score = 0
    private var hasAnswered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        val txtQuiz = findViewById<TextView>(R.id.txtQuiz)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)
        val feedbackText = findViewById<TextView>(R.id.txtFeedback)
        val btnNext = findViewById<Button>(R.id.btnNext)

        fun loadQuestion() {
            txtQuiz.text = questions[currentQuestionIndex]
            feedbackText.text = ""
            hasAnswered = false
        }

        fun checkAnswer(userAnswer: Boolean) {
            if (hasAnswered) return
            hasAnswered = true
            val correct = answers[currentQuestionIndex]
            if (userAnswer == correct) {
                feedbackText.text = "Correct!"
                score++
            } else {
                feedbackText.text = "Incorrect"
            }
        }

        btnTrue.setOnClickListener { checkAnswer(true) }
        btnFalse.setOnClickListener { checkAnswer(false) }

        btnNext.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                loadQuestion()
            } else {

                val intent = Intent(this, Screen3::class.java)
                intent.putExtra("score", score)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                startActivity(intent)
                finish()
            }
        }

        loadQuestion()
    }
}