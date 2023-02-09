package aut.ce.android_hw05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var useDefaultLocation = false

    private lateinit var stateDropdown: AutoCompleteTextView
    private lateinit var cityDropdown: AutoCompleteTextView
    private lateinit var checkBox: CheckBox
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stateDropdown = findViewById(R.id.state_dropdown_text)
        cityDropdown = findViewById(R.id.city_dropdown_text)
        checkBox = findViewById(R.id.checkbox)
        button = findViewById(R.id.button)

        updateButtonStatus()

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            useDefaultLocation = isChecked
            stateDropdown.isEnabled = !isChecked
            cityDropdown.isEnabled = !isChecked

            updateButtonStatus()
        }

        stateDropdown.setOnItemClickListener { _, _, i, _ ->
            val aLLCities = resources.getStringArray(R.array.cities)
            val cities = aLLCities[i].split(",")

            val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, cities)
            cityDropdown.setAdapter(arrayAdapter)
            cityDropdown.setText("")
            updateButtonStatus()
        }

        cityDropdown.setOnItemClickListener { _, _, _, _ ->
            updateButtonStatus()
        }

        val states = resources.getStringArray(R.array.states)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, states)
        stateDropdown.setAdapter(arrayAdapter)

        button.setOnClickListener {
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putExtra("useDefaultLocation", useDefaultLocation)
            intent.putExtra("state", stateDropdown.text.toString())
            intent.putExtra("city", cityDropdown.text.toString())
            startActivity(intent)
        }
    }

    private fun updateButtonStatus() {
        button.isEnabled = useDefaultLocation ||
                (stateDropdown.text.isNotBlank() && cityDropdown.text.isNotBlank())
    }
}
