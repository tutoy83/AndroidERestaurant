package fr.isen.mignottetheo.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityMealBinding
import fr.isen.mignottetheo.androiderestaurant.MealAdapter



class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val starters = resources.getStringArray(R.array.starter)
        val starterList = ArrayList<String>(starters.toList())
        binding.categoryList.adapter = MealAdapter(starterList)


        //Modifier le titre en fct de la cat seletionee
        val categorytodisplay = intent.getStringExtra("categorytodisplay")
        val actionBar = supportActionBar
        actionBar?.title = categorytodisplay

    }
}
