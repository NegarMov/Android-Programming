package aut.ce.android_hw05

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import aut.ce.android_hw05.viewModel.WeatherViewModel
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class ForecastFragment : Fragment() {

    private val viewModel: WeatherViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView

    private lateinit var info: TextView
    private lateinit var fetchedAt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = activity?.let { ItemAdapter(it, viewModel) }

        recyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        info = view.findViewById(R.id.info)
        info.text = resources.getString(R.string.forecast_info, viewModel.cityName)

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val currentTime = formatter.format(time)
        fetchedAt = view.findViewById(R.id.fetched_at)
        fetchedAt.text = resources.getString(R.string.fetched_at,  currentTime)
    }
}