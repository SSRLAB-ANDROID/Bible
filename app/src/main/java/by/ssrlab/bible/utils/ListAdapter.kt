package by.ssrlab.bible.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.RvListItemBinding
import by.ssrlab.bible.db.objects.book.Book
import by.ssrlab.bible.utils.ListAdapter.ListHolder

class ListAdapter(
    private val entitiesList: ArrayList<Book>,
    private val moveAction: () -> Unit
): RecyclerView.Adapter<ListHolder>() {

    inner class ListHolder(val binding: RvListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RvListItemBinding>(inflater, R.layout.rv_list_item, parent, false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.binding.book = entitiesList[position]
        holder.binding.executePendingBindings()

        holder.binding.rvListRipple.setOnClickListener {
            moveAction()
        }
    }

    override fun getItemCount(): Int = entitiesList.size
}