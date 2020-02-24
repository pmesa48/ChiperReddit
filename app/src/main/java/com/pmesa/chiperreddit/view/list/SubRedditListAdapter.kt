package com.pmesa.chiperreddit.view.list

import android.R.attr
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pmesa.chiperreddit.R
import com.pmesa.chiperreddit.common.inflate
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class SubRedditListAdapter(private var onClickListener: (Int, RoomSubReddit) -> Unit): RecyclerView.Adapter<SubRedditViewHolder>() {

    private var subreddits: List<RoomSubReddit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubRedditViewHolder =
        SubRedditViewHolder(parent.inflate(R.layout.view_holder_subreddit), onClickListener)


    override fun getItemCount(): Int = subreddits.size

    override fun onBindViewHolder(holder: SubRedditViewHolder, position: Int) {
        holder.bind(subreddits[position], position)
    }

    fun update(list: List<RoomSubReddit>){
        subreddits = list
        notifyDataSetChanged()
    }

}

class SubRedditViewHolder(view: View, var onClickListener: (Int, RoomSubReddit) -> Unit ) : RecyclerView.ViewHolder(view){

    private var mTitle: TextView = view.findViewById(R.id.subreddit_title)
    private var mSubtitle: TextView = view.findViewById(R.id.subreddit_subtitle)
    private var mLang: TextView = view.findViewById(R.id.lang_tv)
    private var mSubscribers: TextView = view.findViewById(R.id.subscribers_tv)
    private var mContainer: View = view.findViewById(R.id.subreddit_clickeable_layout)

    fun bind(subReddit: RoomSubReddit, position: Int) {
        mTitle.text = subReddit.displayName
        mSubtitle.text = subReddit.url
        mSubscribers.text = "${subReddit.subscribers}  ${itemView.context.getString(R.string.subscribers_lbl)}"
        mLang.text = subReddit.lang?.toUpperCase()
        mContainer.setBackgroundResource(R.color.cardview_light_background)
        if(subReddit.background != null && subReddit.background.isNotBlank()){
            Picasso.get().load(subReddit.background).into(object : Target{
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    mContainer.background =  BitmapDrawable(bitmap)
                }

            })
        }
        mContainer.setOnClickListener { onClickListener(position,subReddit)  }
    }

}
