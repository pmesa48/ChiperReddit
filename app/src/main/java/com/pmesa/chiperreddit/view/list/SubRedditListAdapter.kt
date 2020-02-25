package com.pmesa.chiperreddit.view.list

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pmesa.chiperreddit.R
import com.pmesa.chiperreddit.common.inflate
import com.pmesa.chiperreddit.repo.source.cache.RoomSubReddit
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SubRedditListAdapter(private var onClickListener: (Int, RoomSubReddit) -> Unit,
                           private var onContrastChanged: (RoomSubReddit) -> Unit,
                           private var onDeleteListener: (RoomSubReddit) -> Unit): RecyclerView.Adapter<SubRedditViewHolder>() {

    private var subreddits: List<RoomSubReddit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubRedditViewHolder =
        SubRedditViewHolder(parent.inflate(R.layout.view_holder_subreddit), onClickListener, onContrastChanged, onDeleteListener)


    override fun getItemCount(): Int = subreddits.size

    override fun onBindViewHolder(holder: SubRedditViewHolder, position: Int) {
        holder.bind(subreddits[position], position)
    }

    fun update(list: List<RoomSubReddit>){
        subreddits = list
        notifyDataSetChanged()
    }

}

class SubRedditViewHolder(view: View, var onClickListener: (Int, RoomSubReddit) -> Unit,
                          var onContrastChanged: (RoomSubReddit)-> Unit,
                          var onDeleteListener: (RoomSubReddit) -> Unit) : RecyclerView.ViewHolder(view){

    private var mTitle: TextView = view.findViewById(R.id.subreddit_title)
    private var mSubtitle: TextView = view.findViewById(R.id.subreddit_subtitle)
    private var mLang: TextView = view.findViewById(R.id.lang_tv)
    private var mSubscribers: TextView = view.findViewById(R.id.subscribers_tv)
    private var mContainer: View = view.findViewById(R.id.subreddit_clickeable_layout)
    private var mDelete: View = view.findViewById(R.id.remove_iv)

    fun bind(
        subReddit: RoomSubReddit,
        position: Int
    ) {
        mTitle.text = subReddit.displayName
        mSubtitle.text = subReddit.url
        mSubscribers.text = "${subReddit.subscribers}  ${itemView.context.getString(R.string.subscribers_lbl)}"
        mLang.text = subReddit.lang?.toUpperCase()
        mTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.blackTitle))
        mContainer.setBackgroundResource(R.color.cardview_light_background)
        bindTextColor(subReddit)
        if(subReddit.background != null && subReddit.background.isNotBlank()){
            Picasso.get().load(subReddit.background).into(target(subReddit))
        }
        mDelete.setOnClickListener { onDeleteListener(subReddit) }
        mContainer.setOnClickListener { onClickListener(position,subReddit)  }
    }

    private fun target(
        subReddit: RoomSubReddit
    ): Target {
        return object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bitmap?.let { mContainer.background = BitmapDrawable(it) }
                GlobalScope.launch(Dispatchers.IO) {
                    if (bitmap?.getAvgRgb()?.needContrast() == true) {
                        GlobalScope.launch(Dispatchers.IO) {
                            subReddit.contrast = true
                            onContrastChanged(subReddit)
                        }
                    }
                }
            }

        }
    }

    private fun bindTextColor(subReddit: RoomSubReddit) {
        if (!subReddit.contrast) {
            mTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.blackTitle))
            mSubtitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.blackTitle))
            mSubscribers.setTextColor(ContextCompat.getColor(itemView.context, R.color.blackTitle))
            mLang.setTextColor(ContextCompat.getColor(itemView.context, R.color.blackTitle))
        } else {
            mTitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.whiteTitle))
            mSubtitle.setTextColor(ContextCompat.getColor(itemView.context, R.color.whiteTitle))
            mSubscribers.setTextColor(ContextCompat.getColor(itemView.context, R.color.whiteTitle))
            mLang.setTextColor(ContextCompat.getColor(itemView.context, R.color.whiteTitle))
        }
    }

}

private fun Bitmap.getAvgRgb(): RGB {

    var redColors = 0
    var greenColors = 0
    var blueColors = 0
    var pixelCount = 0

    for (y in 0 until this.height) {
        for (x in 0 until this.width) {
            val c = this.getPixel(x, y)
            pixelCount++
            redColors += Color.red(c)
            greenColors += Color.green(c)
            blueColors += Color.blue(c)
        }
    }
    return RGB(redColors / pixelCount, greenColors / pixelCount, blueColors / pixelCount)
}

data class RGB(val red: Int, val green: Int, val blue: Int) {
    fun needContrast(): Boolean {
        return red + green + blue < THRESHOLD
    }

    companion object{
        const val THRESHOLD = 300
    }
}
