package andvhuvnh.itemlist

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import andvhuvnh.itemlist.data.model.Item

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.item_name)
        val listId: TextView = view.findViewById(R.id.list_id)
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerTitle: TextView = view.findViewById(R.id.header_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.header_view, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        if (holder is HeaderViewHolder) {
            holder.headerTitle.text = "List ID: ${item.listId}"
            Log.d("ItemAdapter", "Binding header for List ID: ${item.listId} at position $position")
        } else if (holder is ItemViewHolder) {
            holder.name.text = item.name
            holder.listId.text = "List ID: ${item.listId}"
            Log.d("ItemAdapter", "Binding item '${item.name}' for List ID: ${item.listId} at position $position")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return if (isHeader(position)) TYPE_HEADER else TYPE_ITEM
    }

    // A header is displayed when listId changes, or it's the first item
    private fun isHeader(position: Int): Boolean {
        return position == 0 || items[position].listId != items[position - 1].listId
    }
}
