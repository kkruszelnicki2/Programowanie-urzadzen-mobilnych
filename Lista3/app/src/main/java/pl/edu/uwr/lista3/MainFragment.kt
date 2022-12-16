package pl.edu.uwr.lista3

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Lista (
    var title: String,
    var description: String,
)

object Listy {
    val listy: MutableList<Lista> = mutableListOf(
        Lista("Lista 1","Zadanie 1"),
        Lista("Lista 2","Zadanie 2"),
        Lista("Lista 3","Zadanie 3"),
        Lista("Lista 4","Zadanie 4"),
    )
}

class MainFragment : Fragment() {
    private val TASK_LIST = "tasks"
    private val TASK_FILE = "task_file"

    private var allLists: MutableList<Lista> = Listy.listy

    private var mContext: Context? = null

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.adapter = WordListAdapter(requireContext(), allLists)

        view.findViewById<Button>(R.id.addList).setOnClickListener() {
            (recyclerView.adapter as WordListAdapter).addItem()
            context?.let { it1 -> saveTaskList(it1) }
            recyclerView.adapter?.notifyDataSetChanged()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTasksList(requireContext())

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val position = arguments?.getInt("position")

        if(title != null) {
            allLists[position!!].title = title

            if (description != null) {
                allLists[position!!].description = description
                (recyclerView.adapter as WordListAdapter).editList(position,title,description)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun saveTaskList(context: Context) {
        (recyclerView.adapter as WordListAdapter).saveTaskList(context)
    }

    fun getTasksList(context: Context) {
        (recyclerView.adapter as WordListAdapter).getTasksList(context)
    }
}