package com.irenaljubik.newsapp.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.irenaljubik.newsapp.R
import com.irenaljubik.newsapp.core.data.NetworkState
import com.irenaljubik.newsapp.core.ui.OnItemClickListener
import com.irenaljubik.newsapp.data.models.Article
import com.irenaljubik.newsapp.databinding.FragmentArticlesBinding
import com.irenaljubik.newsapp.di.factory.ViewModelFactory
import com.irenaljubik.newsapp.ui.binding.BindingComponent
import com.irenaljubik.newsapp.ui.common.BaseFragment
import com.irenaljubik.newsapp.ui.common.SearchViewModel
import javax.inject.Inject

class ArticlesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var dataBindingComponent: DataBindingComponent = BindingComponent(this)

    private val searchViewModel: SearchViewModel by activityViewModels { viewModelFactory }

    private val articlesViewModel: ArticlesViewModel by viewModels { viewModelFactory }

    lateinit var binding: FragmentArticlesBinding

    private val itemClickListener = object : OnItemClickListener<Article> {
        override fun onItemClick(item: Article) {
            val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailsActivity(
                item.title,
                item.url
            )
            findNavController().navigate(action)
        }
    }

    private val articleAdapter = ArticleAdapter(dataBindingComponent, itemClickListener)

    private var category: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        category = arguments?.getString("category")
        category?.let {
            articlesViewModel.setCategoryQuery(it)
        }

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_articles,
            container,
            false,
            dataBindingComponent
        )
        binding.viewModel = articlesViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolBarView(category ?: "", !category.isNullOrEmpty())

        initAdapter()

        searchViewModel.search.observe(viewLifecycleOwner, {
            articlesViewModel.submitSearch(it)
        })

        articlesViewModel.articles.observe(viewLifecycleOwner, {
            if (it == null) return@observe

            articleAdapter.submitList(it)
            binding.executePendingBindings()
        })
        articlesViewModel.networkState.observe(viewLifecycleOwner, {
            if (it != NetworkState.LOADING) {
                binding.swipeRefresh.isRefreshing = false
            }
        })
        binding.swipeRefresh.setOnRefreshListener(articlesViewModel::refresh)
    }

    private fun initAdapter() {
        binding.articleList.apply {
            adapter = articleAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }
}