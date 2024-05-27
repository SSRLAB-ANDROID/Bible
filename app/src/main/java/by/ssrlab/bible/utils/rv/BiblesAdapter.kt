package by.ssrlab.bible.utils.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.RvListItemBinding
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.utils.rv.BiblesAdapter.ListHolder

class BiblesAdapter(
    private val entitiesList: ArrayList<Bible>,
    private val moveAction: (Bible) -> Unit
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
            moveAction(entitiesList[position])
        }
    }

    override fun getItemCount(): Int = entitiesList.size
}