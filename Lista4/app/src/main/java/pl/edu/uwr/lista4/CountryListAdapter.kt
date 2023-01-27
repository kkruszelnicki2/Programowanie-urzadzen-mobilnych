package pl.edu.uwr.lista4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryListAdapter(
    private val context: Context,
    private val countryList: List<MyDataItem>
) : RecyclerView.Adapter<CountryListAdapter.WordListViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryListAdapter.WordListViewHolder {
        return WordListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.country_capital,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: CountryListAdapter.WordListViewHolder, position: Int) {
        val countryName = countryList[position].name
        val countryCapital = countryList[position].capital

        holder.countryText.text = countryName
        holder.capitalText.text = countryCapital
    }

    override fun getItemCount() = countryList.size

    class WordListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val capitalText: TextView = itemView.findViewById((R.id.capital))
        val countryText: TextView = itemView.findViewById((R.id.country))
    }

}