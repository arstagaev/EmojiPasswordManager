package com.revolve44.emojipasswordmanager.ui.screens

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.adapters.MainScreenRecycleviewAdapter
import com.revolve44.emojipasswordmanager.base.ItemElementsDelegate
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
//import com.revolve44.emojipasswordmanager.ui.changeColor
import kotlinx.android.synthetic.main.fragment_mainscreen.*
import timber.log.Timber
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainScreenFragment : Fragment(R.layout.fragment_mainscreen) {

    private lateinit var recycler_view : RecyclerView
    private lateinit var mainScreenLayout: RelativeLayout


    private lateinit var viewModel : MainViewModel
    private val mAdapter = MainScreenRecycleviewAdapter()

    private lateinit var fab: FloatingActionButton
    private var howmanyclickedChangeColorinMenu = 0
    private lateinit var topMainScreenLayout: RelativeLayout
    private lateinit var poolEmpty: TextView
    private var clickedPosition = -1

    //private val timer = Timer("schedule2")



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //mainScreenLayout = view.findViewById(R.id.top_main_screen_layout)
        fab = view.findViewById(R.id.fab)
        topMainScreenLayout = view.findViewById(R.id.top_main_screen_layout)
        poolEmpty = view.findViewById(R.id.pool_empty)
        recycler_view = view.findViewById(R.id.recycler_view)


        val activity = activity as Context
        viewModel =(activity as MainActivity).viewModel




    }

    override fun onResume() {
        super.onResume()
        Timber.i("lifec Main onresume")
        showRecyclerView()
        showFabActionButton()

    }

    override fun onPause() {
        super.onPause()
        Timber.i("lifec Main onpause")
    }

    private fun showRecyclerView() {
        mAdapter.attachDelegate(object : ItemElementsDelegate<PairNameandPassword> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onElementClick(model: PairNameandPassword, view: View, clickedPosition: Int) {


                this@MainScreenFragment.clickedPosition = clickedPosition

                PopupMenu(activity,view,Gravity.RIGHT).apply {
                    inflate(R.menu.menu_in_row_on_main_recyclerview)           // пункты меню
                    setOnMenuItemClickListener { item ->  // обработчик клика пункта меню
                        when(item.itemId) {
                            R.id.menu_row_edit -> {
                                Timber.i("ccc edit!")

                                viewModel.pairPasswordAndName.value = model
                                findNavController().navigate(R.id.action_gameFragment_to_resultFragment)

                                true
                            }
                            R.id.menu_row_delete -> {
                                Timber.i("ccc delete!!")
                                //Timber.i("ccc ${getItemId(position)}")
                                viewModel.deletePassword(model)

                                true
                            }
                            else -> false
                        }
                    }
                }.show()  //показ меню

            }
        })

        recycler_view.adapter = mAdapter
        recycler_view.layoutManager = LinearLayoutManager(activity)

        recycler_view.setHasFixedSize(false)

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


        //var updatesOld : List<PairNameandPassword> = emptyList()

        viewModel.getAllPasswords().observe(viewLifecycleOwner, Observer { updates ->
            Timber.i("${updates.toString()} and size is ${updates.size}")

            if (updates.isEmpty() && mAdapter.sizeOfItems == 0){
                //Toast.makeText(activity,"1 REFRESH item ${clickedPosition}",Toast.LENGTH_SHORT).show()
                mAdapter.updateItems(updates)
                poolEmpty.setText("パスワードはまだ保存されていません... \n" +
                        " \uD83E\uDD7A \n" +
                        " \uD83D\uDC49\uD83D\uDC48")
                val timer = object: CountDownTimer(4000, 4000) {
                    override fun onTick(millisUntilFinished: Long) {

                    }

                    override fun onFinish() {
                        poolEmpty.setText("You have no saved passwords, yet... \n \uD83E\uDD7A \n \uD83D\uDC49\uD83D\uDC48")

                    }
                }
                timer.start()

            }else if (updates.size > mAdapter.sizeOfItems){
                //Toast.makeText(activity," ADDED 2  item ${clickedPosition}",Toast.LENGTH_SHORT).show()
                poolEmpty.text = ""
                mAdapter.updateItems(updates)
            }else if (updates.size < mAdapter.sizeOfItems){
                poolEmpty.text = ""
                //.makeText(activity,"DELETEED 3   item ${clickedPosition}",Toast.LENGTH_SHORT).show()
                mAdapter.notifyItemRemoved(clickedPosition)
            }

//            }
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


}