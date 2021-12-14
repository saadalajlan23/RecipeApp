package com.example.recipeapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*


class RecyclerViewAdapter(private var recipe: Recipe): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView:View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = recipe[position]

        holder.itemView.apply {
            tvTitle.text = user.title
            tvAuthor.text = user.author
            tvIngredients.text = user.ingredients
            tvInstructions.text = user.instructions
        }
    }

    override fun getItemCount() = recipe.size
    fun update(recipe: Recipe){
        this.recipe = recipe
        notifyDataSetChanged()
    }
}
