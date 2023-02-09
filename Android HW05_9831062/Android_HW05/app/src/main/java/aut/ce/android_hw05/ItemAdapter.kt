package aut.ce.android_hw05

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import aut.ce.android_hw05.network.DailyForecast
import aut.ce.android_hw05.viewModel.WeatherViewModel
import coil.load

private const val BASE_URL = "https://openweathermap.org/img/wn/"

class ItemAdapter(
    private val context: Context,
    private val viewModel: WeatherViewModel
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val iconView: ImageView = view.findViewById(R.id.icon)
        val dateView: TextView = view.findViewById(R.id.date)
        val weatherView: TextView = view.findViewById(R.id.weather)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val forecast: DailyForecast = viewModel.forecasts[position]

        holder.iconView.load(BASE_URL + "${forecast.weatherInfo[0].icon}@2x.png")

        holder.dateView.text = context.resources.getString(R.string.date_info,
            forecast.date.subSequence(0, 10),
            forecast.date.subSequence(10, forecast.date.length))

        holder.weatherView.text = context.resources.getString(R.string.temp_info,
            forecast.weatherInfo[0].description,
            forecast.mainInfo.temp_min,
            forecast.mainInfo.temp_max)
    }

    override fun getItemCount(): Int {
        return viewModel.forecasts.size
    }
}