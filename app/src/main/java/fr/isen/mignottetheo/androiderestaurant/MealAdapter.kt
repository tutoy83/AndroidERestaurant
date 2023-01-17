package fr.isen.mignottetheo.androiderestaurant

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MealAdapter(var dishes: ArrayList<String>, val onItemClickListener: (dishName: String) -> Unit): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
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

        holder.cellName.text = dish
        holder.itemView.setOnClickListener{
            onItemClickListener(dish)
        }
    }

    override fun getItemCount(): Int = dishes.size
}
