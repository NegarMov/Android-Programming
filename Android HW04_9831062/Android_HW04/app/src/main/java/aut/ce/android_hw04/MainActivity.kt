package aut.ce.android_hw04

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        attachObservers()

        attachChangeListeners()
    }

    private fun attachObservers() {
        val observer = { editText : EditText -> { it : String? ->
                if (it != null)
                    editText.setText(it)
                else
                    editText.text.clear()
            }
        }

        val irValue: LiveData<String> = viewModel.irValue
        irValue.observe(this, observer(findViewById(R.id.ir_editText)))

        val usValue: LiveData<String> = viewModel.usValue
        usValue.observe(this, observer(findViewById(R.id.us_editText)))

        val caValue: LiveData<String> = viewModel.caValue
        caValue.observe(this, observer(findViewById(R.id.ca_editText)))

        val gbValue: LiveData<String> = viewModel.gbValue
        gbValue.observe(this, observer(findViewById(R.id.gb_editText)))

        val euValue: LiveData<String> = viewModel.euValue
        euValue.observe(this, observer(findViewById(R.id.eu_editText)))

        val aeValue: LiveData<String> = viewModel.aeValue
        aeValue.observe(this, observer(findViewById(R.id.ae_editText)))
    }

    private fun attachChangeListeners() {
        val irEditText = findViewById<EditText>(R.id.ir_editText)
        irEditText.addTextChangedListener(CurrencyTextWatcher(irEditText, viewModel, 0))

        val usEditText = findViewById<EditText>(R.id.us_editText)
        usEditText.addTextChangedListener(CurrencyTextWatcher(usEditText, viewModel, 1))

        val caEditText = findViewById<EditText>(R.id.ca_editText)
        caEditText.addTextChangedListener(CurrencyTextWatcher(caEditText, viewModel, 2))

        val gbEditText = findViewById<EditText>(R.id.gb_editText)
        gbEditText.addTextChangedListener(CurrencyTextWatcher(gbEditText, viewModel, 3))

        val euEditText = findViewById<EditText>(R.id.eu_editText)
        euEditText.addTextChangedListener(CurrencyTextWatcher(euEditText, viewModel, 4))

        val aeEditText = findViewById<EditText>(R.id.ae_editText)
        aeEditText.addTextChangedListener(CurrencyTextWatcher(aeEditText, viewModel, 5))
    }
}

class CurrencyTextWatcher(
    private val editText : EditText,
    private val viewModel : MainActivityViewModel,
    private val index : Int
) : TextWatcher {
    override fun afterTextChanged(s: Editable) {}

    override fun beforeTextChanged(s: CharSequence, start: Int,
                                   count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int,
                               before: Int, count: Int) {
        if (editText.hasFocus())
            viewModel.updateValues(index, s)
    }
}