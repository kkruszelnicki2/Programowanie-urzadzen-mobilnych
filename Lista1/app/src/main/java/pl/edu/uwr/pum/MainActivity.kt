package pl.edu.uwr.pum

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val CHEAT = "pl.edu.uwr.pum.CHEAT"

class Question(
    val question: String,
    val correctAnswer: Int
)

object Questions {
    val questions: List<Question> = listOf(
        Question("Prędkość światła wynosi c=299 892 458 m/s",1),
        Question("Istnieją tylko 2 zasady dynamiki Newtona",2),
        Question("Ciężar i masa obiektu pozostają takie same w każdych warunkach",2),
        Question("Przejście ze stanu gazowego w stały nazywamy resublimacją",1),
        Question("Ciśnienie ma wpływ na temperaturę wrzenia cieczy",1),
        Question("Jednostką oporu elektrycznego jest Ohm",1),
        Question("Przyspieszenie jest przykładem siły zachowawczej",2),
        Question("Przyspieszenie ciała opadającego w próżni wzrasta",2),
        Question("Okres obrotu księżyca wokół własnej osi wynosi 4 tygodnie",1),
        Question("Isaac Newton był pierwszym lauraetem nagrody nobla w dziedzinie fizyki",2)
    )
}

class MainActivity : AppCompatActivity() {
    private var currentPosition = 1
    private val questions: List<Question> = Questions.questions
    private var selectedAnswerPosition = 0
    private var points = 0
    private var correctAnswers = 0
    private var cheatAmount = 0

    private val answerOne: Button by lazy { findViewById(R.id.text_view_answer_one) }
    private val answerTwo: Button by lazy { findViewById(R.id.text_view_answer_two)}
    private val cheat: Button by lazy { findViewById(R.id.text_view_answer_three)}
    private val questionText: TextView by lazy { findViewById(R.id.text_view_question)}
    private val progressText: TextView by lazy { findViewById(R.id.text_view_progress)}
    private val progressBar: ProgressBar by lazy { findViewById(R.id.progress_bar)}
    private val finalPoints: TextView by lazy {findViewById(R.id.points)}
    private val finalAnswers: TextView by lazy {findViewById(R.id.corrAnswers)}
    private val finalCheats: TextView by lazy {findViewById(R.id.cheats)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("question_number")
            points = savedInstanceState.getInt("points")
            correctAnswers = savedInstanceState.getInt("correctAnswers")
            cheatAmount = savedInstanceState.getInt("cheatAmount")
        }

        var progBar = currentPosition-1
        progressText.text = "$progBar/10"
        progressBar.progress = currentPosition - 1

        setQuestion()

        answerOne.setOnClickListener {setSelectedView(answerOne,1)
        submit()}
        answerTwo.setOnClickListener {setSelectedView(answerTwo,2)
        submit()}
        cheat.setOnClickListener {cheating(questions[currentPosition - 1].correctAnswer)}

        findViewById<Button>(R.id.search).setOnClickListener{
            val intent = Intent(Intent.ACTION_WEB_SEARCH)
            val url = findViewById<TextView>(R.id.text_view_question).text.toString()
            intent.putExtra(SearchManager.QUERY, url)
            startActivity(intent)
        }
    }

    private fun setQuestion() {
        val question = questions[currentPosition - 1]
        questionText.text = question.question
    }

    private fun setSelectedView(button: Button, selectedAnswer: Int) {
        selectedAnswerPosition = selectedAnswer
    }

    private fun cheating(answer: Int) {
        points = points - 15
        cheatAmount++
        var message: String
        if(answer == 1)
            message = "TRUTH"
        else
            message = "FALSE"
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra(CHEAT,message)
        }
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("question_number", currentPosition)
        outState.putInt("points", points)
        outState.putInt("cheatAmount",cheatAmount)
        outState.putInt("correctAnswers",correctAnswers)
    }

    private fun submit() {
        if(currentPosition > questions.size)
            finish()

        val question = questions[currentPosition - 1]

        if (question.correctAnswer == selectedAnswerPosition) {
            correctAnswers++
            points = points + 10
        }

        if(currentPosition<=10) {
            progressText.text = "$currentPosition/10"
            progressBar.progress = currentPosition
        }

        currentPosition++

        if(currentPosition <= questions.size)
            setQuestion()
        else {
            finalPoints.text = "Points: $points"
            finalAnswers.text = "Correct answers: $correctAnswers"
            finalCheats.text = "Cheats: $cheatAmount"
            questionText.text = "KONIEC"
        }
    }
}