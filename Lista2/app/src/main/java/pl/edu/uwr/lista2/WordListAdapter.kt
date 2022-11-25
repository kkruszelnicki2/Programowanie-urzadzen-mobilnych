package pl.edu.uwr.lista2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter(
    private val context: Context,
    private val crimeList: List<Crime>
) : RecyclerView.Adapter<WordListAdapter.WordListViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordListAdapter.WordListViewHolder {
        return WordListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.rv_item,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: WordListAdapter.WordListViewHolder, position: Int) {
        val word = crimeList[position].title
        val ind = crimeList[position].indeks
        val message = crimeList[position].descript

        val bundle = Bundle()
        bundle.putString("description", message)

        holder.crimeTitle.text = word
        holder.indeks.text = "Index Number: $ind"

        if(crimeList[position].solved) {
            holder.img.setImageResource(R.drawable.handcuffs)
        }
        else{
            holder.img.setImageDrawable(null)
        }

        holder.crime.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.to_detailFragment,bundle)
        )
    }

    override fun getItemCount() = crimeList.size

    class WordListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val crimeTitle: TextView = itemView.findViewById((R.id.title))
        val indeks: TextView = itemView.findViewById((R.id.indexNumber))
        val crime: LinearLayout = itemView.findViewById(R.id.FullLayout)
        val img: ImageView = itemView.findViewById((R.id.isSolved))
    }

}