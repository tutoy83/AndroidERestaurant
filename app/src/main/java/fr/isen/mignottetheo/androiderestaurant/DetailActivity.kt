package fr.isen.mignottetheo.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.mignottetheo.androiderestaurant.model.Items
import com.squareup.picasso.Picasso
import com.google.gson.Gson
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityMealBinding
import java.io.File
import java.io.FileOutputStream

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

        var totalPrice = 0

        //diminuer la quantite sur click du bouton
        binding.buttonDetailMinus.setOnClickListener {
            currentQuantity -= 1
            //securite valeur negative
            if(currentQuantity<0){
                currentQuantity = 0
            }
            // update le TextView de la qte
            binding.quantityNumber.text = currentQuantity.toString()
            totalPrice = updateTotal(meal, currentQuantity)
        }


        //afficher les ingredients
        val ingredientss = meal.ingredients.map { it.nameFr }
        val ingredientsString = ingredientss.joinToString(", ")
        binding.contentMealDetails.text = ingredientsString

        binding.totalPrice.setOnClickListener {
            // create the object that holds the meal information
            val mealInfo = MealInfo(meal.nameFr.toString(), currentQuantity, totalPrice)
            // convert the object to JSON format
            val jsonData = Gson().toJson(mealInfo)
            // write the JSON data to a file
            val file = File(applicationContext.filesDir, "panier.json")
            FileOutputStream(file).apply {
                write(jsonData.toByteArray())
                close()
            }
            // show a Snackbar or AlertDialog to inform the user that the meal has been added to the cart
            Snackbar.make(binding.root, "Meal added to cart", Snackbar.LENGTH_SHORT).show()

        }
    }

    private fun updateTotal(meal:Items, currentQuantity: Int): Int {
        val prices = meal.prices
        val mealSelectedPrice = prices[0]
        var priceValue = mealSelectedPrice.price.toString().toInt() * currentQuantity

        if (prices.isNotEmpty()) {
            //securite valeur negative
            if(priceValue<0){
                priceValue = 0
            }
            binding.totalPrice.text = "Total ${priceValue} €"

        }
        return priceValue
    }
    data class MealInfo(
        val name: String,
        val quantity: Int,
        val priceTotal: Int
        )
}