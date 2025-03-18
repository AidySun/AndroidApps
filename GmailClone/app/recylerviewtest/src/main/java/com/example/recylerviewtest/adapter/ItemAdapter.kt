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
        val ll: LinearLayout = view.findViewById(R.id.ll)
        val imageView: ImageView = view.findViewById(R.id.item_image)
        val textView: TextView = view.findViewById(R.id.item_text)
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
        holder.textView.text = item.text

        // 确保LinearLayout使用底部对齐
        // 注意：FrameLayout.LayoutParams才有gravity属性，而不是ViewGroup.MarginLayoutParams
        val frameParams = holder.ll.layoutParams as android.widget.FrameLayout.LayoutParams
        frameParams.gravity = android.view.Gravity.BOTTOM
        holder.ll.layoutParams = frameParams

        // Adjust layout parameters for the first item
        if (position == 0) {
            val layoutParams = holder.ll.layoutParams
            layoutParams.height = holder.itemView.context.dpToPx(160)
            layoutParams.width = holder.itemView.context.dpToPx(160)
            holder.ll.layoutParams = layoutParams
        }
    }

    override fun getItemCount() = items.size
}