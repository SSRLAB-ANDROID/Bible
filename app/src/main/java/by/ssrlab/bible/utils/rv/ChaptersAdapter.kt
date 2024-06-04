package by.ssrlab.bible.utils.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.RvListItemBinding
import by.ssrlab.bible.db.objects.data.Chapter

class ChaptersAdapter(
    private val entitiesList: List<Chapter>,
): RecyclerView.Adapter<ChaptersAdapter.ChaptersHolder>() {

    inner class ChaptersHolder(val binding: RvListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChaptersHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RvListItemBinding>(inflater, R.layout.rv_list_item, parent, false)
        return ChaptersHolder(binding)
    }

    override fun onBindViewHolder(holder: ChaptersHolder, position: Int) {
        holder.binding.apply {
            listEntity = entitiesList[position]
            executePendingBindings()

            rvListRipple.setOnClickListener {
//                moveAction(entitiesList[position])
            }
        }
    }

    override fun getItemCount() = entitiesList.size
}