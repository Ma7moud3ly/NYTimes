package com.nytimes.ui.articles

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nytimes.R
import com.nytimes.data.Article
import com.nytimes.databinding.ItemArticleBinding
import com.nytimes.ui.DetailsActivity

class ArticlesAdapter(
    private val list: MutableList<Article>,
) : RecyclerView.Adapter<ArticlesAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemArticleBinding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        position?.let { position ->
            val article: Article = list[position]
            val imgView = holder.binding.articleImg
            val imgUrl = article.thumbnail()
            val context = imgView.context

            imgUrl.let {
                Glide.with(context)
                    .load(it)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imgView);
            }

            holder.binding.article = article

            holder.binding.articleTab.setOnClickListener {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("article_url", article.url)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}