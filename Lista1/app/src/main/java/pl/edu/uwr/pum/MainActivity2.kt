package pl.edu.uwr.pum

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_CHEAT = "pl.edu.uwr.pum.RETURN"

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val message = intent.getStringExtra(CHEAT)

        findViewById<TextView>(R.id.cheatAnswer).apply {
            text = message
        }
    }

    fun returnCheat(view: View) {
        finish()
    }
}