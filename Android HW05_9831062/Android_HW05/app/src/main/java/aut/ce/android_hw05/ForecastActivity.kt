package aut.ce.android_hw05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import aut.ce.android_hw05.viewModel.Status
import aut.ce.android_hw05.viewModel.WeatherViewModel

class ForecastActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val useDefaultLocation = intent!!.extras!!.getBoolean("useDefaultLocation")
        val city = intent!!.extras!!.getString("city")

        val status: LiveData<Status> = viewModel.status
        status.observe(this) {
            when (it) {
                Status.LOADING -> replaceFragment(LoadingFragment())
                Status.SUCCESS -> replaceFragment(ForecastFragment())
                Status.FAILED -> replaceFragment(ConnectionErrorFragment())
                else -> {}
            }
        }

        val actionBar = supportActionBar
        actionBar!!.title = resources.getString(R.string.forecastTitle, city)
        actionBar.setDisplayHomeAsUpEnabled(true)

        viewModel.updateWeatherInfo(useDefaultLocation, city)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}