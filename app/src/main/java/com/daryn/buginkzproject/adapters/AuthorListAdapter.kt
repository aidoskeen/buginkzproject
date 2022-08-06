package com.daryn.buginkzproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daryn.buginkzproject.databinding.AuthorItemBinding
import com.daryn.buginkzproject.models.AuthorViewModel

class AuthorListAdapter (
    private val authorList: ArrayList<AuthorViewModel>,
    private val listener: (AuthorViewModel, Int) -> Unit

): RecyclerView.Adapter<AuthorListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = AuthorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(authorList[position])
        holder.itemView.setOnClickListener { listener(authorList[position], position) }
    }

    override fun getItemCount(): Int {
        return authorList.size
    }
    class ViewHolder(var itemBinding: AuthorItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(authorViewModel: AuthorViewModel) {
            itemBinding.authorImage.setImageResource(authorViewModel.image)
            itemBinding.authorName.text = authorViewModel.name
        }
    }
}

