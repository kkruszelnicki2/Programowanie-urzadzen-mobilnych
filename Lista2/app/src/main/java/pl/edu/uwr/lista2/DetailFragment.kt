package pl.edu.uwr.lista2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        view.findViewById<FloatingActionButton>(R.id.fabB).setOnClickListener {
            val action = DetailFragmentDirections.toListFragment()
            Navigation.findNavController(view).navigate(action)
        }

        val text = arguments?.getString("description")

        val textView = view.findViewById<TextView>(R.id.description)

        textView.text = text

        return view
    }
}