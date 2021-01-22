package com.revolve44.emojipasswordmanager.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.revolve44.emojipasswordmanager.R
import com.revolve44.emojipasswordmanager.models.SuggestPair
import com.revolve44.emojipasswordmanager.ui.MainViewModel

class SuggestionsAdapter4(
    private val list: List<SuggestPair>,
    context: Context,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<SuggestionsAdapter4.SuggestViewHolder>() {

    // Define listener member variable
    private var listener: OnItemClickListener? = null
    var onItemClick: ((SuggestPair) -> Unit)? = null

    // Define the listener interface
    interface OnItemClickListener {
        fun onItemClick(itemView: View?, position: Int)
    }

    // Define the method that allows the parent activity or fragment to define the listener
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SuggestionsAdapter4.SuggestViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.suggestion_item,
            parent, false
        )
        return SuggestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SuggestionsAdapter4.SuggestViewHolder, position: Int) {
        val item = list.get(position)
        holder.suggestPassword.text = item.emojiPasswordSuggest.toString()
        holder.textMeaning.text = item.whatIsMean.toString()

    }



    override fun getItemCount()= list.size

//    class SuggestViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val textMeaning: TextView = itemView.findViewById<TextView>(R.id.suggest_meaning)
//        val suggestPassword: TextView = itemView.findViewById<TextView>(R.id.suggest_password)
//
//        init {
//            itemView.setOnClickListener {
//                onIte
//            }
//        }
//
//
//        //val button = view.findViewById<Button>(R.id.button_item)
//    }
    inner class SuggestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textMeaning: TextView = itemView.findViewById<TextView>(R.id.suggest_meaning)
        val suggestPassword: TextView = itemView.findViewById<TextView>(R.id.suggest_password)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

}