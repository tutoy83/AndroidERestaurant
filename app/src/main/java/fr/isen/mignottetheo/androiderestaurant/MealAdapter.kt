package fr.isen.mignottetheo.androiderestaurant

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mignottetheo.androiderestaurant.model.Items

class MealAdapter(var dishes: ArrayList<Items>, val onItemClickListener: (dishName: String) -> Unit): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    class MealViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cellName =  view.findViewById<TextView>(R.id.cellName)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_cell, parent, false)

        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val dish = dishes[position]

        holder.cellName.text = dish.nameFr
        holder.itemView.setOnClickListener{
            onItemClickListener(dish.nameFr!!)
        }
    }

    override fun getItemCount(): Int = dishes.size

    fun refreshList(dishesFromAPI: ArrayList<Items>){
        dishes = dishesFromAPI
        notifyDataSetChanged()
    }
}
