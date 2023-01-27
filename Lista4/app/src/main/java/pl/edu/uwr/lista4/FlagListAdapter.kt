package pl.edu.uwr.lista4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class FlagListAdapter(
    private val context: Context,
    private val countryList: List<MyDataItem>
) : RecyclerView.Adapter<FlagListAdapter.WordListViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlagListAdapter.WordListViewHolder {
        return WordListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.country_flag,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: FlagListAdapter.WordListViewHolder, position: Int) {
        val countryName = countryList[position].name
        val flag = countryList[position].flags.png

        holder.CountryText.text = countryName
        Picasso.get().load(flag).into(holder.flagPNG);
    }

    override fun getItemCount() = countryList.size

    class WordListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val CountryText: TextView = itemView.findViewById((R.id.country))
        val flagPNG: ImageView = itemView.findViewById((R.id.flag))
    }

}