package aut.ce.android_hw04

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val currecies = listOf(1F, 41800F, 30752.26F, 50316.54F, 44417.1F, 11380.96F)

    private var _irValue = MutableLiveData<String>()
    private var _usValue = MutableLiveData<String>()
    private var _caValue = MutableLiveData<String>()
    private var _gbValue = MutableLiveData<String>()
    private var _euValue = MutableLiveData<String>()
    private var _aeValue = MutableLiveData<String>()

    val irValue: LiveData<String>
        get() = _irValue
    val usValue: LiveData<String>
        get() = _usValue
    val caValue: LiveData<String>
        get() = _caValue
    val gbValue: LiveData<String>
        get() = _gbValue
    val euValue: LiveData<String>
        get() = _euValue
    val aeValue: LiveData<String>
        get() = _aeValue

    fun updateValues(index:Int, value:CharSequence) {
        if (value.toString().isBlank()) {
            _irValue.value = null
            _usValue.value = null
            _caValue.value = null
            _gbValue.value = null
            _euValue.value = null
            _aeValue.value = null
            return
        }

        val valueAsFloat = value.toString().toFloat()

        if (index != 0)
            _irValue.value = String.format("%.2f", valueAsFloat * (currecies[index] / currecies[0]))

        if (index != 1)
            _usValue.value = String.format("%.2f", valueAsFloat * (currecies[index] / currecies[1]))

        if (index != 2)
            _caValue.value = String.format("%.2f", valueAsFloat * (currecies[index] / currecies[2]))

        if (index != 3)
            _gbValue.value = String.format("%.2f", valueAsFloat * (currecies[index] / currecies[3]))

        if (index != 4)
            _euValue.value = String.format("%.2f", valueAsFloat * (currecies[index] / currecies[4]))

        if (index != 5)
            _aeValue.value = String.format("%.2f", valueAsFloat * (currecies[index] / currecies[5]))
    }

}

