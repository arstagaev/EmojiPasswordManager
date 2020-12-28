package com.revolve44.emojipasswordmanager.ui.screens

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.ui.MassiveAdapter
//import com.revolve44.emojipasswordmanager.ui.changeColor
import kotlinx.android.synthetic.main.fragment_mainscreen.*
import timber.log.Timber
import java.util.*
import kotlin.concurrent.schedule

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

    lateinit var recycler_view : RecyclerView
    private lateinit var mainScreenLayout: RelativeLayout


    lateinit var viewModel : MainViewModel
    lateinit var adapter : MassiveAdapter
    lateinit var fab: FloatingActionButton
    var howmanyclickedChangeColorinMenu = 0
    lateinit var topMainScreenLayout: RelativeLayout
    lateinit var poolEmpty: TextView

    //private val timer = Timer("schedule2")



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainScreenLayout = view.findViewById(R.id.top_main_screen_layout)
        fab = view.findViewById(R.id.fab)
        topMainScreenLayout = view.findViewById(R.id.top_main_screen_layout)
        poolEmpty = view.findViewById(R.id.pool_empty)
        recycler_view = view.findViewById(R.id.recycler_view)

        val activity = activity as Context
        viewModel =(activity as MainActivity).viewModel


//        val skyAnim2: ValueAnimator = ObjectAnimator.ofInt(mainScreenLayout, "backgroundColor", PreferenceMaestro.pickedColorofMainScreen,
//                listOfColor(howmanyclickedChangeColorinMenu))
//
//        skyAnim2.duration = 4000L
//        skyAnim2.setEvaluator(ArgbEvaluator())
//        skyAnim2.start()
        showRecyclerView()
        showFabActionButton()


    }

    private fun showRecyclerView() {
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0){
                    fab.hide();
                } else{
                    fab.show();
                }
                super.onScrolled(recyclerView, dx, dy)

            }
        })



        viewModel.getAllPasswords().observe(viewLifecycleOwner, Observer { updates ->
            Timber.i("${updates.toString()} and size is ${updates.size}")
            if (updates.isNotEmpty()){
                //poolEmpty.isInvisible
                poolEmpty.text = ""
                try {

                    adapter = MassiveAdapter(updates, this, viewModel)

                    recycler_view.adapter = adapter
                    recycler_view.layoutManager = LinearLayoutManager(activity)
                    recycler_view.setHasFixedSize(false)
                } catch (e: Exception) {
                    Timber.e("rclvw ERROR log: " + e.message)
                }
            }else{

                adapter.notifyDataSetChanged()
                poolEmpty.setText("パスワードはまだ保存されていません... \n" +
                        " \uD83E\uDD7A \n" +
                        " \uD83D\uDC49\uD83D\uDC48")
                val timer = object: CountDownTimer(6000, 6000) {
                    override fun onTick(millisUntilFinished: Long) {

                    }

                    override fun onFinish() {
                        poolEmpty.setText("You have no saved passwords, yet... \n \uD83E\uDD7A \n \uD83D\uDC49\uD83D\uDC48")

                    }
                }
                timer.start()

            }


        })
    }

    private fun showFabActionButton() {
        fab.setOnClickListener {
            //go to another fragment
            NavHostFragment.findNavController(this).navigate(R.id.action_gameFragment_to_resultFragment)
        }
        //fab.backgroundTintList = Color.BLACK
        fab.rippleColor = Color.BLACK
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