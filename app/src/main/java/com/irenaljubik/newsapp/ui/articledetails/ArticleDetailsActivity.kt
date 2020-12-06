package com.irenaljubik.newsapp.ui.articledetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.navigation.navArgs
import com.irenaljubik.newsapp.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : DaggerAppCompatActivity() {

    private val args: ArticleDetailsActivityArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        title = args.title
        webView.apply {
            loadUrl(args.url)
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            webViewClient = WebViewClient()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }
}