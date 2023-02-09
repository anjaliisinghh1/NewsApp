package com.project.newsapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.util.Util
import com.google.android.material.snackbar.Snackbar
import com.project.newsapp.R
import com.project.newsapp.databinding.FragmentArticleBinding
import com.project.newsapp.models.Article
import com.project.newsapp.ui.NewsActivity
import com.project.newsapp.ui.NewsViewModel
import com.project.newsapp.util.shareNews

class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    lateinit var binding: FragmentArticleBinding
    val args: ArticleFragmentArgs by navArgs()

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.rotate_close_anim) }
    private val rotateFromBottom: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.from_bottom_anim) }
    private val rotateToBottom: Animation by lazy { AnimationUtils.loadAnimation(context,R.anim.to_bottom_anim) }

    private var clicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        binding = FragmentArticleBinding.bind(view)

        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        binding.fabAdd.setOnClickListener {
            onAddButtonClicked()
        }

        binding.fabShare.setOnClickListener {
            shareNews(context,article)
        }

        binding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view,"Article saved successfully",Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun onAddButtonClicked(){
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean){
        if (!clicked){
            binding.fabShare.visibility = View.VISIBLE
            binding.fabSave.visibility = View.VISIBLE
        }else{
            binding.fabShare.visibility = View.INVISIBLE
            binding.fabSave.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean){
        if (!clicked){
            binding.fabShare.startAnimation(rotateFromBottom)
            binding.fabSave.startAnimation(rotateFromBottom)
            binding.fabAdd.startAnimation(rotateOpen)
        }else{
            binding.fabShare.startAnimation(rotateToBottom)
            binding.fabSave.startAnimation(rotateToBottom)
            binding.fabAdd.startAnimation(rotateClose)
        }
    }
}