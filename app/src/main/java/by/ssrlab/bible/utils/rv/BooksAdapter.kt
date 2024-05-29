package by.ssrlab.bible.utils.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import by.ssrlab.bible.R
import by.ssrlab.bible.databinding.RvListItemBinding
import by.ssrlab.bible.db.objects.data.Book

class BooksAdapter(
    private val entitiesList: ArrayList<Book>,
    private val moveAction: (Book) -> Unit
): RecyclerView.Adapter<BooksAdapter.BooksHolder>() {

    inner class BooksHolder(val binding: RvListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RvListItemBinding>(inflater, R.layout.rv_list_item, parent, false)
        return BooksHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksHolder, position: Int) {
        holder.binding.apply {
            listEntity = entitiesList[position]
            executePendingBindings()

            rvListRipple.setOnClickListener {
                moveAction(entitiesList[position])
            }
        }
    }

    override fun getItemCount(): Int = entitiesList.size
}