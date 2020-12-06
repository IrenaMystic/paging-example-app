package com.irenaljubik.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.irenaljubik.newsapp.core.coroutineproviders.CoroutineProvider
import com.irenaljubik.newsapp.di.factory.ViewModelFactory
import com.irenaljubik.newsapp.ui.common.SearchViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var coroutineProvider: CoroutineProvider

    private val searchViewModel: SearchViewModel by viewModels { viewModelFactory }

    private lateinit var coroutineScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!::coroutineScope.isInitialized) {
            coroutineScope = coroutineProvider.createCoroutineScope()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        menu.findItem(R.id.search).apply {
            initSearchItem(this)
            initSearchView(this)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                navHostFragment.findNavController().navigate(R.id.action_global_articlesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initSearchItem(item: MenuItem) {
        item.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                submitQuery("")
                navHostFragment.findNavController().navigateUp()
                return true
            }
        })
    }

    private fun initSearchView(item: MenuItem) {
        (item.actionView as SearchView).apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                private var searchFor = ""

                override fun onQueryTextSubmit(search: String): Boolean {
                    return true
                }

                override fun onQueryTextChange(search: String): Boolean {
                    val searchText = search.trim()
                    if (searchText == searchFor)
                        return true

                    searchFor = searchText

                    coroutineScope.launch {
                        delay(500)  //debounce timeOut
                        if (searchText != searchFor)
                            return@launch
                        submitQuery(searchText)
                    }

                    return true
                }
            })
        }
    }

    private fun submitQuery(search: String) {
        searchViewModel.search.postValue(search)
    }

}