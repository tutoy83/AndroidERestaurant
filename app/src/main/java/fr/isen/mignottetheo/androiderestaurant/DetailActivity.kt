package fr.isen.mignottetheo.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityMealBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Modifier le titre en fct de la cat seletionee
        val itemtodisplay = intent.getStringExtra("detailItemName")
        binding.detailTitleArticle.text = itemtodisplay


    }
}