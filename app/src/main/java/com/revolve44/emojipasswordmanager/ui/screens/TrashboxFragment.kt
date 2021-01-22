package com.revolve44.emojipasswordmanager.ui.screens

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.adapters.TrashboxAdapter
import com.revolve44.emojipasswordmanager.base.ItemElementsDelegate
import com.revolve44.emojipasswordmanager.models.DeletedPairsOfNameAndPassword
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import timber.log.Timber

class TrashboxFragment : Fragment(R.layout.fragment_trashbox){

    lateinit var viewModel: MainViewModel

    private lateinit var recycler_view : RecyclerView
    private lateinit var mainScreenLayout: RelativeLayout


    private val mAdapter = TrashboxAdapter()

    private lateinit var fab: FloatingActionButton
    private var howmanyclickedChangeColorinMenu = 0
    private lateinit var topMainScreenLayout: RelativeLayout
    //private lateinit var poolEmpty: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMainElements(view)
        val activity = activity as Context
        viewModel =(activity as MainActivity).viewModel

    }

    override fun onResume() {
        super.onResume()
        Timber.i("lifec Main onresume")
        showRecyclerView()


    }

    private fun initMainElements(view: View) {

        recycler_view = view.findViewById(R.id.trashbox_recyclerview)
        var clearTrashbox = view.findViewById<Button>(R.id.clear_trashbox) as Button
        clearTrashbox.setOnClickListener {
            viewModel.clearTrashbox()


        }
    }

    override fun onPause() {
        super.onPause()
        Timber.i("lifec Main onpause")
    }

    private fun showRecyclerView() {
        mAdapter.attachDelegate(object : ItemElementsDelegate<DeletedPairsOfNameAndPassword> {
            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onElementClick(model: DeletedPairsOfNameAndPassword, view: View,clickedPosition: Int) {

                Toast.makeText(activity,"xc name:"+model.deletedNameCompany.toString(), Toast.LENGTH_SHORT).show()
                viewModel.restorePassword(model)


            }
        })

        recycler_view.adapter = mAdapter
        recycler_view.layoutManager = LinearLayoutManager(activity)

        recycler_view.setHasFixedSize(false)


        viewModel.getAllPasswordsFromTrashbox().observe(viewLifecycleOwner, Observer { updates ->
            Timber.i("${updates.toString()} and size is ${updates.size}")

            if (updates.isNotEmpty() && !mAdapter.hasItems){
                //poolEmpty.text = ""
                mAdapter.setList(updates)

            }else {
                //poolEmpty.text = ""
                mAdapter.updateItems(updates)

            }


//
//            if (updates.isNotEmpty() && mAdapter.hasItems){
//                //poolEmpty.isInvisible
//
//                mAdapter.updateItems(updates)
//
//                if (!mAdapter.hasItems){
//
//                }else{
//
//                }
//            }else{
//
//
//            }
        })

    }
}