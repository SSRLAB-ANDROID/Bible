package by.ssrlab.bible.utils.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.RvBibleItemBinding
import by.ssrlab.bible.db.objects.data.Bible
import by.ssrlab.bible.utils.rv.BiblesAdapter.ListHolder

class BiblesAdapter(
    private val entitiesList: List<Bible>,
    private val moveAction: (Bible) -> Unit
): RecyclerView.Adapter<ListHolder>() {

    inner class ListHolder(val binding: RvBibleItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RvBibleItemBinding>(inflater, R.layout.rv_bible_item, parent, false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.binding.apply {
            book = entitiesList[position]
            executePendingBindings()

            rvBibleRipple.setOnClickListener {
                moveAction(entitiesList[position])
            }
        }
    }

    override fun getItemCount(): Int = entitiesList.size
}