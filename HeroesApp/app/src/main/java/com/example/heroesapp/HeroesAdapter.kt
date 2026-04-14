package com.example.heroesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HeroesAdapter(
    private val heroes: List<Hero>,
    private val context: Context,
    private val click: (Hero) -> Unit
) : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val heroName = itemView.findViewById<TextView>(R.id.heroName)
        val heroCompany = itemView.findViewById<TextView>(R.id.heroCompany)
        val heroImage = itemView.findViewById<ImageView>(R.id.heroImage)

        fun bind(hero: Hero) {
            heroName.text = hero.heroName
            heroCompany.text = hero.heroCompany
            heroImage.setImageResource(hero.heroImage)
            itemView.setOnClickListener { click(hero) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.recycler_view_item, parent, false)
        return HeroesViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

}