package com.google.advancedsoft.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, M : Any> : RecyclerView.Adapter<VH>() {

    private var selectionMode = SelectionMode.NONE
    private val selectionMap = mutableMapOf<Int, M>()
    private var bindItemClickListener = true

    var onItemSelected: ((pos: Int, View?, M) -> Unit)? = null
        set(value) {
            field = value
            if (selectionMode == SelectionMode.NONE) {
                selectionMode = SelectionMode.SINGLE
            }
        }

    var dataItems: MutableList<M> = mutableListOf()
        private set

    open fun getItem(pos: Int) = dataItems[pos]

    fun <Z : M> getItemAs(pos: Int): Z = getItem(pos) as Z

    fun removeItem(pos: Int) {
        if (isValidPos(pos)) {
            dataItems.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    fun addItem(m: M) {
        dataItems.add(m)
        notifyItemInserted(dataItems.size - 1)
    }

    fun addItems(list: List<M>) {
        if (list.isNotEmpty()) {
            dataItems.addAll(list)
            notifyItemInserted(dataItems.size - 1)
        }
    }

    fun addItem(m: M, pos: Int) {
        dataItems.add(pos, m)
        notifyItemInserted(pos)
    }

    fun updateItem(m: M, pos: Int) {
        if (pos == RecyclerView.NO_POSITION) return
        dataItems[pos] = m
        notifyItemChanged(pos)
    }

    open fun updateDataItems(newItems: List<M>) {
        dataItems.clear()
        dataItems.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setSelectionMode(mode: SelectionMode) {
        selectionMode = mode
    }

    // If the adapter don't want to make all items clickable
    open fun isItemClickable(position: Int) = true

    fun disableBindItemClickListener() {
        bindItemClickListener = false
    }

    open fun clear() {
        dataItems.clear()
        selectionMap.clear()
        notifyDataSetChanged()
    }

    fun clearSelection() {
        selectionMap.clear()
        notifyDataSetChanged()
    }

    fun getSelectionIndices() = selectionMap.keys.toList()

    fun getSelection() = selectionMap.values.toList()

    fun hasSelection() = selectionMap.isNotEmpty()

    fun isLastItem(position: Int): Boolean = position == itemCount - 1

    fun hasData() = dataItems.isNotEmpty()

    private fun isValidPos(pos: Int) = pos in 0 until dataItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = parent.inflate(getResLayout(viewType))
        val viewHolder = constructViewHolder(itemView, viewType)
        bindItemClickListener = selectionMode != SelectionMode.NONE && bindItemClickListener
        if (bindItemClickListener) {
            viewHolder.itemView.setOnClickListener {
                if (isItemClickable(viewHolder.adapterPosition).not()) return@setOnClickListener
                val selectedIndex = viewHolder.adapterPosition
                setSelectedIndexInternally(selectedIndex, it)
            }
        }
        return viewHolder
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        onItemSelected = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    fun setSelectedIndex(position: Int) {
        setSelectedIndexInternally(position)
    }

    private fun setSelectedIndexInternally(position: Int, clickedView: View? = null) {
        if (position == RecyclerView.NO_POSITION || dataItems.isEmpty()) {
            return
        }
        when (selectionMode) {
            SelectionMode.SINGLE -> {
                if (selectionMap.keys.isNotEmpty()) {
                    removePreviousSelection()
                }
                updateSelection(position)
            }
            SelectionMode.MULTIPLE -> {
                updateSelection(position)
            }
            else -> {
                // *Do nothing * //
            }
        }
        onItemSelected?.invoke(position, clickedView, dataItems[position])
    }

    private fun removePreviousSelection() {
        val oldSelection = selectionMap.keys.first()
        selectionMap.remove(oldSelection)
        notifyItemChanged(oldSelection)
    }

    private fun updateSelection(position: Int) {
        if (selectionMap[position] != null) {
            selectionMap.remove(position)
        } else {
            selectionMap[position] = dataItems[position]
        }
        notifyItemChanged(position)
    }

    fun isIndexSelected(position: Int) = selectionMap.containsKey(position)

    abstract fun constructViewHolder(itemView: View, viewType: Int): VH

    abstract fun getResLayout(viewType: Int): Int

    override fun getItemCount() = dataItems.size
}
enum class SelectionMode {
    SINGLE, MULTIPLE, NONE
}
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}