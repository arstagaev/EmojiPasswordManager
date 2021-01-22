package com.revolve44.emojipasswordmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.base.BaseAdapter
import com.revolve44.emojipasswordmanager.base.BaseViewHolder
import com.revolve44.emojipasswordmanager.base.ItemElementsDelegate
import com.revolve44.emojipasswordmanager.models.PairNameandPassword
import com.revolve44.emojipasswordmanager.ui.MainViewModel
import com.revolve44.emojipasswordmanager.ui.screens.MainScreenFragment
import timber.log.Timber
import java.util.*


class MainScreenRecycleviewAdapter: BaseAdapter<PairNameandPassword>(){

    var pairDelegate: ItemElementsDelegate<PairNameandPassword>? = null

    fun attachDelegate(callback: ItemElementsDelegate<PairNameandPassword>) {
        this.pairDelegate = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PairNameandPassword> {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pair_name_and_password_item,parent,false))

    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<PairNameandPassword>(itemView = itemView){
        private val txtName : TextView = itemView.findViewById(R.id.name_service)
        private val txtPassword : TextView = itemView.findViewById(R.id.password_of_service)
        private val optionsInRow : ImageView = itemView.findViewById(R.id.options_in_row)

        override fun bind(model: PairNameandPassword) {
            txtName.text = model.nameCompany
            txtPassword.text = model.password
            optionsInRow.setOnClickListener {
                pairDelegate?.onElementClick(model,itemView,this.adapterPosition)



            }

        }
    }


}