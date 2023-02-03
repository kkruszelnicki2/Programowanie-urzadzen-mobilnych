package pl.edu.uwr.projekt

import android.content.Context
import android.graphics.Color
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

class Adapter(
    private val context: Context,
    private var Lists: MutableList<Lista>

) : RecyclerView.Adapter<Adapter.AdapterViewHolder>() {

    private val TASK_LIST = "tasks"
    private val TASK_FILE = "task_file"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter.AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_item,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: Adapter.AdapterViewHolder, position: Int) {
        val date = Lists[position].date
        holder.listTitle.text = date.toString()

        val bundle = Bundle()
        bundle.putString("description",Lists[position].description)
        bundle.putString("title",Lists[position].date.toString())
        bundle.putInt("position",position)

        holder.delete.setOnClickListener() {
            Lists.removeAt(position)
            notifyDataSetChanged()
            saveTaskList(context)
        }

        holder.listTitle.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.to_detailFragment,bundle)
        )

        val cmp = LocalDate.parse(date).compareTo(LocalDate.now())

        if(cmp == 0) {
            holder.fullItem.setBackgroundColor(GREEN)
        }
        else if(cmp > 0) {
            holder.fullItem.setBackgroundColor(RED)
        }
    }

    override fun getItemCount() = Lists.size

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listTitle: TextView = itemView.findViewById(R.id.title)

        val delete: Button = itemView.findViewById(R.id.delete)

        val fullItem: LinearLayout = itemView.findViewById(R.id.fullLayout)
    }

    fun addItem() {
        Lists.add(Lista(LocalDate.now().toString(),""))
    }

    fun editList(position: Int, date: String, description: String) {
        Lists.set(position,Lista(date,description))
        saveTaskList(context)
    }

    fun saveTaskList(context: Context) {
        val json = Gson().toJson(Lists)
        val sharedPreferences = context.getSharedPreferences(TASK_FILE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(TASK_LIST, json).apply()
    }

    fun getTasksList(context: Context) {
        val sharedPreferences = context.getSharedPreferences(TASK_FILE, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(TASK_LIST, "{}")

        val type = object : TypeToken<ArrayList<Lista>>() {}.type
        Lists = Gson().fromJson(json, type)
    }

}