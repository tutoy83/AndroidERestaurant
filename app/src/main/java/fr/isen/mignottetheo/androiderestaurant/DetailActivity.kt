package fr.isen.mignottetheo.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.mignottetheo.androiderestaurant.model.Items
import com.squareup.picasso.Picasso
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityMealBinding
@Suppress("DEPRECATION")

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recuperer le menu choisi
        val meal = intent.getSerializableExtra("mealselected") as Items


        //Modifier le titre en fct de la cat seletionee
        //val itemtodisplay = intent.getStringExtra("detailItemName")
        binding.detailTitleArticle.text = meal?.nameFr

        //Afficher image du menu
        val mealSelectedPic = meal.images[0]
        if (mealSelectedPic.isNotEmpty()) {
            Picasso.get().load(mealSelectedPic).into(binding.detailImageArticle)
        }


        var currentQuantity = binding.quantityNumber.text.toString().toInt()

        //Afficher le prix a l initialisation de l activite
        updateTotal(meal, 1)


        //augmenter la quantite sur click du bouton
        binding.buttonDetailPlus.setOnClickListener {
            currentQuantity += 1
            //securite valeur negative
            if(currentQuantity<0){
                currentQuantity = 0
            }

            // update le TextView de la qte
            binding.quantityNumber.text = currentQuantity.toString()
            updateTotal(meal, currentQuantity)
        }

        //diminuer la quantite sur click du bouton
        binding.buttonDetailMinus.setOnClickListener {
            currentQuantity -= 1
            //securite valeur negative
            if(currentQuantity<0){
                currentQuantity = 0
            }
            // update le TextView de la qte
            binding.quantityNumber.text = currentQuantity.toString()
            updateTotal(meal, currentQuantity)

        }


        //afficher les ingredients
        val ingredientss = meal.ingredients.map { it.nameFr }
        val ingredientsString = ingredientss.joinToString(", ")
        binding.contentMealDetails.text = ingredientsString


    }

    private fun updateTotal(meal:Items, currentQuantity: Int) {
        val prices = meal.prices
        if (prices.isNotEmpty()) {
            val mealSelectedPrice = prices[0]
            var priceValue = mealSelectedPrice.price.toString().toInt() * currentQuantity

            //securite valeur negative
            if(priceValue<0){
                priceValue = 0
            }
            binding.totalPrice.text = "Total ${priceValue} €"
        }
    }
}