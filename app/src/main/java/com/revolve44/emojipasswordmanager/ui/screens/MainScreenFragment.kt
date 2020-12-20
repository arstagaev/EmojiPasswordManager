package com.revolve44.emojipasswordmanager.ui.screens

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.ui.MassiveAdapter
import kotlinx.android.synthetic.main.fragment_mainscreen.*
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainScreenFragment : Fragment(R.layout.fragment_mainscreen), MassiveAdapter.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

   // private val exampleList = generateDummyList(500)
   // private var adapter = MassiveAdapter(exampleList, this)
    lateinit var recycler_view : RecyclerView


    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as Context
        viewModel =(activity as MainActivity).viewModel




        fab.setOnClickListener {
            //go to another fragment
            NavHostFragment.findNavController(this).navigate(R.id.action_gameFragment_to_resultFragment)
        }

        recycler_view = view.findViewById(R.id.recycler_view)



        viewModel.getAllPasswords().observe(viewLifecycleOwner, Observer { updates ->
            try {
                val adapter = MassiveAdapter(updates, this)
                recycler_view.adapter = adapter
                recycler_view.layoutManager = LinearLayoutManager(activity)
                recycler_view.setHasFixedSize(true)
            } catch (e: Exception) {
                Timber.e("rclvw ERROR log: " + e.message)
            }


        })



    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onItemClick(position: Int) {

    }

//    private fun generateDummyList(size: Int): ArrayList<PairNameandPassword> {
//        val list = ArrayList<PairNameandPassword>()
//        for (i in 0 until size) {
//            val drawable = when (i % 3) {
//                0 -> R.drawable.ic_baseline_add_24
//                1 -> R.drawable.ic_baseline_add_24
//                else -> R.drawable.ic_baseline_add_24
//            }
//            val item = PairNameandPassword(drawable, "Item $i", "Line 2")
//            list += item
//        }
//        return list
//    }
}