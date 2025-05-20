package za.ac.iie.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class Screen3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen3)

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtFeedback = findViewById<TextView>(R.id.txtFeedback)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val txtAsses = findViewById<TextView>(R.id.txtAsses)
        val btnHome = findViewById<Button>(R.id.btnHome)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getStringArrayExtra("answers")

        txtScore.text = "Your score: $score/5"

        if (score >= 3) {
            txtFeedback.text = "Great job!"
        } else {
            txtFeedback.text = "Keep practising!"
        }

        btnReview.setOnClickListener {
            if (questions != null && answers != null) {
                val builder = StringBuilder()
                for (i in questions.indices) {
                    builder.append("${i + 1}. ${questions[i]}\n")
                    builder.append("Answer: ${answers[i]}\n\n")
                }
                txtAsses.text = builder.toString()
                txtAsses.visibility = TextView.VISIBLE
            }
        }

        btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}