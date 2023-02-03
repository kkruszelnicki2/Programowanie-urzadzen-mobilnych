package pl.edu.uwr.projekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail_fragment, container, false)

        var title = arguments?.getString("title")
        var description = arguments?.getString("description")
        var position = arguments?.getInt("position")

        val titleView = view.findViewById<TextView>(R.id.titleEdit)
        val descriptionView = view.findViewById<TextView>(R.id.descriptionEdit)

        titleView.text = title
        descriptionView.text = description

        view.findViewById<Button>(R.id.save).setOnClickListener {
            title = titleView.text.toString()
            description = descriptionView.text.toString()
        }

        view.findViewById<Button>(R.id.goBack).setOnClickListener {
            val bundle = Bundle()

            bundle.putString("description",description)
            bundle.putString("title",title)
            if (position != null) {
                bundle.putInt("position",position)
            }

            view.findNavController().navigate(R.id.to_mainFragment,bundle)
        }

        return view
    }
}