package com.irenaljubik.newsapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.irenaljubik.newsapp.R
import com.irenaljubik.newsapp.core.ui.OnItemClickListener
import com.irenaljubik.newsapp.databinding.FragmentCategoriesBinding
import com.irenaljubik.newsapp.di.factory.ViewModelFactory
import com.irenaljubik.newsapp.ui.common.BaseFragment
import javax.inject.Inject

class CategoryFragment : BaseFragment() {

    lateinit var binding: FragmentCategoriesBinding

    private val itemListener = object : OnItemClickListener<String> {
        override fun onItemClick(item: String) {
            val action = CategoryFragmentDirections.actionCategoryFragmentToArticlesFragment(item)
            findNavController().navigate(action)
        }
    }
    private val categoryAdapter = CategoryAdapter(itemListener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        initAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBarView("Category", false)
    }

    private fun initAdapter() {
        binding.categoryList.run {
            adapter = categoryAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
        categoryAdapter.submitList(resources.getStringArray(R.array.categories).toList())
    }

}