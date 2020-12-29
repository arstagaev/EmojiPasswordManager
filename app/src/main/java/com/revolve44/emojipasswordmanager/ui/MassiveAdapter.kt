package com.revolve44.emojipasswordmanager.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.MainActivity
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.ui.screens.MainScreenFragment
import timber.log.Timber


class MassiveAdapter(private val list: List<PairNameandPassword>,
                     private val listener: MainScreenFragment,
                     private val viewModel: MainViewModel
): RecyclerView.Adapter<MassiveAdapter.MassiveViewHolder>(){

    //private lateinit var mainViewModel : MainViewModel

//    fun CartListAdapter(list: List<PairNameandPassword>,
//                        listener: MainScreenFragment) {
//        context = context
//        cartModels = cartModels
//        cartViewModel = ViewModelProviders.of(context as FragmentActivity).get(CartViewModel::class.java)
//    }
//    init {
//    var context: Context
//    mainViewModel = ViewModelProviders.of(context as MainActivity).get(MainViewModel::class.java)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MassiveViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.pair_name_and_password_item,
                parent, false)
        return MassiveViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MassiveViewHolder, position: Int) {
        val currentItem = list[position]

        //holder.imageView.setImageResource(currentItem.imageResource)
        holder.nameOfService.text = currentItem.nameCompany
        //holder.nameOfService.rotation = 180F
        holder.passwordBody.text = currentItem.password.toString()
        holder.passwordBody.isSelected = true
        holder.optionsInRow.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.menu_in_row_on_main_recyclerview)           // пункты меню
                setOnMenuItemClickListener { item ->  // обработчик клика пункта меню
                    when(item.itemId) {
                        R.id.menu_row_edit -> {
                            Timber.i("ccc edit!")
                            //val action = bundleOf("reg",list.get(position))
                            viewModel.pairPasswordAndName.value = list.get(position)
                            holder.itemView.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)

                            true
                        }
                        R.id.menu_row_delete -> {
                            Timber.i("ccc delete!!")
                            Timber.i("ccc ${getItemId(position)}")
                            viewModel.deletePassword(list.get(position))
                            notifyItemRemoved(position)
                            notifyItemRangeChanged(0,position)
                            true
                        }
                        else -> false
                    }
                }
            }.show()  //показ меню

        }
        //holder.textView2.marqueeRepeatLimit
    }

//    private fun bundleOf(pairs: String, pairs1: PairNameandPassword): Bundle {
//
//    }

    override fun getItemCount()= list.size

    inner class MassiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),


        View.OnClickListener {
        //val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val nameOfService: TextView = itemView.findViewById(R.id.text_view_1)
        val passwordBody: TextView = itemView.findViewById(R.id.text_view_2)
        val optionsInRow: ImageView = itemView.findViewById(R.id.options_in_row)





        override fun onClick(v: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
            //showPopupMenu(v)
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    /////////////////////////////////

}