package com.revolve44.emojipasswordmanager.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.ui.screens.MainScreenFragment
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.models.PairNameandPassword

class MassiveAdapter(private val list: List<PairNameandPassword>,
                     private val listener: MainScreenFragment
):
    RecyclerView.Adapter<MassiveAdapter.MassiveViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MassiveViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.massive_item,
            parent, false)
        return MassiveViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MassiveViewHolder, position: Int) {
        val currentItem = list[position]

        //holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.nameCompany
        holder.textView2.text = currentItem.password.toString()
    }

    override fun getItemCount()= list.size

    inner class MassiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),


        View.OnClickListener {
        //val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)



        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}