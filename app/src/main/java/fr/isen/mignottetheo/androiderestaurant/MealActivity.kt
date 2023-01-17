package fr.isen.mignottetheo.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityMealBinding
import org.json.JSONObject

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Modifier le titre en fct de la cat seletionee
        val categorytodisplay = intent.getStringExtra("categorytodisplay")
        val actionBar = supportActionBar
        binding.mealTitleTop1.text = categorytodisplay
        actionBar?.title = categorytodisplay

        when(categorytodisplay){
            "Entrees" -> {
                binding.mealImage.setImageResource(R.drawable.salade)
                loadDishesFromAPI()
                //Afficher liste des entrees
                val starters = resources.getStringArray(R.array.starter)
                val starterList = ArrayList<String>(starters.toList())
                binding.categoryList.adapter = MealAdapter(arrayListOf()){
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("detailitemtodisplay", it)
                    startActivity(intent)
                }


                binding.categoryList.layoutManager = LinearLayoutManager(this)

            }
            "Plats" -> {
                binding.mealImage.setImageResource(R.drawable.burger)

                //Afficher liste des plats
                val mainmeals = resources.getStringArray(R.array.mainmeal)
                val mainmealsList = ArrayList<String>(mainmeals.toList())
                binding.categoryList.adapter = MealAdapter(mainmealsList){
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("detailitemtodisplay", it)
                    startActivity(intent)
                }

                binding.categoryList.layoutManager = LinearLayoutManager(this)
            }
            "Desserts" -> {
                binding.mealImage.setImageResource(R.drawable.cookie)

                //Afficher liste des desserts
                val deserts = resources.getStringArray(R.array.desert)
                val desertsList = ArrayList<String>(deserts.toList())
                binding.categoryList.adapter = MealAdapter(desertsList){
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("detailitemtodisplay", it)
                    startActivity(intent)
                }

                binding.categoryList.layoutManager = LinearLayoutManager(this)
            }
        }




    }


    private fun loadDishesFromAPI(){
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,{
            Log.w("MealActivity", "response : $it")
        }, {
            Log.w("MealActivity", "error : $it")

        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }
}
