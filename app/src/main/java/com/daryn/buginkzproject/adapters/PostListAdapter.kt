package com.daryn.buginkzproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daryn.buginkzproject.R
import com.daryn.buginkzproject.models.Datum
import com.daryn.buginkzproject.models.PostViewModel
import com.squareup.picasso.Picasso


class PostListAdapter(private val mList: List<Datum>) : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val postViewModel = mList[position]


        Picasso.get()
            .load(postViewModel.newsImage)
            .into(holder.postImage)

        // sets the text to the textview from our itemHolder class
        holder.postText.text = postViewModel.newsName
        holder.buginCount.text = postViewModel.bugin
        holder.viewCount.text = postViewModel.viewCount
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val postImage: ImageView = itemView.findViewById(R.id.post_image)
        val postText: TextView = itemView.findViewById(R.id.caption)
        val buginCount: TextView = itemView.findViewById(R.id.buginCount)
        val viewCount: TextView = itemView.findViewById(R.id.viewsCount)

    }
}