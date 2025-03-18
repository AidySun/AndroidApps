package com.example.recylerviewtest.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recylerviewtest.R
import com.example.recylerviewtest.data.Item


class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }

    // Extension function to convert dp to pixels
    fun Context.dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)

        // Adjust layout parameters for the first item
        if (position == 0) {
            val layoutParams = holder.imageView.layoutParams
            layoutParams.height = holder.itemView.context.dpToPx(160)
            layoutParams.width = holder.itemView.context.dpToPx(160)
            holder.imageView.layoutParams = layoutParams
        }
    }

    override fun getItemCount() = items.size
}