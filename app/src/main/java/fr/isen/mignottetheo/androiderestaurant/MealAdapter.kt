package fr.isen.mignottetheo.androiderestaurant

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mignottetheo.androiderestaurant.model.Items
import com.squareup.picasso.Picasso
import android.widget.ImageView



class MealAdapter(var dishes: ArrayList<Items>, val onItemClickListener: (dishName: String) -> Unit): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    class MealViewHolder(view: View):RecyclerView.ViewHolder(view){
        val cellName =  view.findViewById<TextView>(R.id.cellName)
        val cellPrice =  view.findViewById<TextView>(R.id.cellPrice)
        val cellImage = view.findViewById<ImageView>(R.id.cellImage)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_cell, parent, false)

        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val dish = dishes[position]

        holder.cellName.text = dish.nameFr
        holder.cellPrice.text = "${dish.prices[0].price} €"


        if(dish.images[0]==""){
            //If image empty, we put an empty one
            dish.images[0] = "https://www.davincischools.org/wp-content/uploads/2017/08/Icon-plate.png"
        }

        val foodPhoto = dish.images[0]
        Picasso.get().load(foodPhoto).into(holder.cellImage)


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
