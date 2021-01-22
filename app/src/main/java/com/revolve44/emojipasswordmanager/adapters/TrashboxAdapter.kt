package com.revolve44.emojipasswordmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.base.BaseAdapter
import com.revolve44.emojipasswordmanager.base.BaseViewHolder
import com.revolve44.emojipasswordmanager.base.ItemElementsDelegate
import com.revolve44.emojipasswordmanager.models.DeletedPairsOfNameAndPassword
import com.revolve44.emojipasswordmanager.models.PairNameandPassword

class TrashboxAdapter: BaseAdapter<DeletedPairsOfNameAndPassword>() {


    var pairDelegate: ItemElementsDelegate<DeletedPairsOfNameAndPassword>? = null

    fun attachDelegate(callback: ItemElementsDelegate<DeletedPairsOfNameAndPassword>) {
        this.pairDelegate = callback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DeletedPairsOfNameAndPassword> {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.trashbox_item,parent,false))

    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<DeletedPairsOfNameAndPassword>(itemView = itemView){
        private val txtName : TextView = itemView.findViewById(R.id.name_service)
        private val txtPassword : TextView = itemView.findViewById(R.id.password_of_service)
        private val optionsInRow : ImageView = itemView.findViewById(R.id.restore_pair)

        override fun bind(model: DeletedPairsOfNameAndPassword) {
            txtName.text = model.deletedNameCompany
            txtPassword.text = model.deletedPassword
            optionsInRow.setOnClickListener {
                pairDelegate?.onElementClick(model,itemView,this.adapterPosition)


            }

        }
    }
}