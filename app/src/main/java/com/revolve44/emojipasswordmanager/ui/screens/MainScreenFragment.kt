package com.revolve44.emojipasswordmanager.ui.screens

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.storage.PreferenceMaestro
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.ui.MassiveAdapter
//import com.revolve44.emojipasswordmanager.ui.changeColor
import com.revolve44.emojipasswordmanager.utils.listOfColor
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
//    private var param1: String? = null
//    private var param2: String? = null

   // private val exampleList = generateDummyList(500)
   // private var adapter = MassiveAdapter(exampleList, this)
    lateinit var recycler_view : RecyclerView
    private lateinit var mainScreenLayout: RelativeLayout


    lateinit var viewModel : MainViewModel
    //lateinit var mainScreenLayout : ConstraintLayout
    var howmanyclickedChangeColorinMenu = 0

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        arguments?.let {
////            param1 = it.getString(ARG_PARAM1)
////            param2 = it.getString(ARG_PARAM2)
////        }
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainScreenLayout = view.findViewById(R.id.xxx)

        val activity = activity as Context
        viewModel =(activity as MainActivity).viewModel

        val skyAnim2: ValueAnimator = ObjectAnimator.ofInt(mainScreenLayout, "backgroundColor", PreferenceMaestro.pickedColorofMainScreen,
                listOfColor(howmanyclickedChangeColorinMenu))

        skyAnim2.duration = 4000L
        skyAnim2.setEvaluator(ArgbEvaluator())
        skyAnim2.start()





        fab.setOnClickListener {
            //go to another fragment
            NavHostFragment.findNavController(this).navigate(R.id.action_gameFragment_to_resultFragment)
        }
        //fab.backgroundTintList = Color.BLACK
        fab.rippleColor = Color.BLACK

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


    fun changeBackgroundColor(){
        //changeColor()
        //SkyLayout =


//        val skyAnim2: ValueAnimator = ObjectAnimator.ofInt(mainScreenLayout, "backgroundColor", PreferenceMaestro.pickedColorofMainScreen,
//                listOfColor(howmanyclickedChangeColorinMenu))
//
//        skyAnim2.duration = 1000L
//        skyAnim2.setEvaluator(ArgbEvaluator())
//        skyAnim2.start()
        howmanyclickedChangeColorinMenu++
    }



//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment GameFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            MainScreenFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

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