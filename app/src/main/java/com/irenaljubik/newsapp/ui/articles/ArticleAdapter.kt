package com.irenaljubik.newsapp.ui.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.irenaljubik.newsapp.R
import com.irenaljubik.newsapp.core.ui.DataBoundPagedListAdapter
import com.irenaljubik.newsapp.core.ui.OnItemClickListener
import com.irenaljubik.newsapp.data.models.Article
import com.irenaljubik.newsapp.databinding.ItemArticleBinding
import java.text.SimpleDateFormat
import java.util.*

class ArticleAdapter(
    private val dataBindingComponent: DataBindingComponent,
    private val itemClickListener: OnItemClickListener<Article>
) :
    DataBoundPagedListAdapter<Article, ItemArticleBinding>(diffCallback) {

    companion object {
        val diffCallback = object : ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemArticleBinding {
        val binding = DataBindingUtil.inflate<ItemArticleBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_article,
            parent,
            false, dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.article?.let { itemClickListener.onItemClick(it) }
        }
        return binding
    }

    override fun bind(binding: ItemArticleBinding, item: Article?, position: Int) {
        item?.apply {
            binding.article = this
            val parsedDateObj = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
                .parse(this.publishedAt)
            parsedDateObj?.apply {
                val formattedDateStr = SimpleDateFormat("dd, MMM yyyy", Locale.US)
                    .format(this)
                binding.publishedAt.text = formattedDateStr
            }
        }
    }
}