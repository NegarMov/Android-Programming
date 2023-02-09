package aut.ce.android_hw02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val faalButton: Button = findViewById(R.id.button)
        faalButton.setOnClickListener {
            val intent = Intent(this, FaalActivity::class.java)
            startActivity(intent)
        }

        val aboutButton: Button = findViewById(R.id.button2)
        aboutButton.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }
    }
}