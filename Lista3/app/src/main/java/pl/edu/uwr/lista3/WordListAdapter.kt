package pl.edu.uwr.lista3

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter(
    private val context: Context,
    private val Lists: MutableList<Lista>

) : RecyclerView.Adapter<WordListAdapter.WordListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordListAdapter.WordListViewHolder {
        return WordListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: WordListAdapter.WordListViewHolder, position: Int) {
        val word = Lists[position].title
        holder.listTitle.text = word

        val bundle = Bundle()
        bundle.putString("description",Lists[position].description)
        bundle.putString("title",Lists[position].title)
        bundle.putInt("position",position)

        holder.delete.setOnClickListener() {
            Lists.removeAt(position)
            notifyDataSetChanged()
        }

        holder.listTitle.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.to_detailFragment,bundle)
        )
    }

    override fun getItemCount() = Lists.size

    class WordListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listTitle: TextView = itemView.findViewById(R.id.title)

        val delete: Button = itemView.findViewById(R.id.delete)
    }

    public fun addItem() {
        Lists.add(Lista("NewList",""))
    }

}