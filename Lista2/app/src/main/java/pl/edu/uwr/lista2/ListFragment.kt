package pl.edu.uwr.lista2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Crime(
    val title: String,
    val solved: Boolean,
    val indeks: Int,
    val descript: String,
)

object Crimes {
    val crimes: List<Crime> = listOf(
        Crime("Crime #1",false,(280000..330000).random(),"1"),
        Crime("Crime #2",true,(280000..330000).random(),"2"),
        Crime("Crime #3",true,(280000..330000).random(),"3"),
        Crime("Crime #4",false,(280000..330000).random(),"4"),
        Crime("Crime #5",true,(280000..330000).random(),"5"),
        Crime("Crime #6",true,(280000..330000).random(),"6"),
        Crime("Crime #7",false,(280000..330000).random(),"7"),
        Crime("Crime #8",false,(280000..330000).random(),"8"),
        Crime("Crime #9",false,(280000..330000).random(),"9"),
        Crime("Crime #10",true,(280000..330000).random(),"10"),
        Crime("Crime #11",false,(280000..330000).random(),"11"),
        Crime("Crime #12",true,(280000..330000).random(),"12"),
        Crime("Crime #13",true,(280000..330000).random(),"13"),
        Crime("Crime #14",true,(280000..330000).random(),"14"),
        Crime("Crime #15",false,(280000..330000).random(),"15"),
        Crime("Crime #16",false,(280000..330000).random(),"16"),
        Crime("Crime #17",false,(280000..330000).random(),"17"),
        Crime("Crime #18",false,(280000..330000).random(),"18"),
        Crime("Crime #19",true,(280000..330000).random(),"19"),
        Crime("Crime #20",false,(280000..330000).random(),"20"),
    )
}

class ListFragment : Fragment()  {
    private val crimeList: List<Crime> = Crimes.crimes

    private var mContext: Context? = null

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.list_fragment, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(mContext)
        recyclerView.adapter = WordListAdapter(requireContext(), crimeList)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}