package aut.ce.android_hw02

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FaalActivity : AppCompatActivity() {

    val FAAL_COUNT = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faal)

        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.app_name)
        actionBar.setDisplayHomeAsUpEnabled(true)

        val chosenFaal = (1..FAAL_COUNT).random()

        val faalNameTextView: TextView = findViewById(R.id.textView)
        faalNameTextView.text = when (chosenFaal) {
            1 -> getString(R.string.ghazalName1)
            2 -> getString(R.string.ghazalName2)
            3 -> getString(R.string.ghazalName3)
            4 -> getString(R.string.ghazalName4)
            5 -> getString(R.string.ghazalName5)
            else -> getString(R.string.faalName)
        }

        val faalContentTextView: TextView = findViewById(R.id.textView2)
        faalContentTextView.movementMethod = ScrollingMovementMethod()
        faalContentTextView.text = when (chosenFaal) {
            1 -> getString(R.string.ghazal1)
            2 -> getString(R.string.ghazal2)
            3 -> getString(R.string.ghazal3)
            4 -> getString(R.string.ghazal4)
            5 -> getString(R.string.ghazal5)
            else -> getString(R.string.faalContent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}