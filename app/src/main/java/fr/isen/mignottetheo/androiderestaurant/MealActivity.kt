package fr.isen.mignottetheo.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import fr.isen.mignottetheo.androiderestaurant.databinding.ActivityMealBinding
import fr.isen.mignottetheo.androiderestaurant.model.DataResult
import fr.isen.mignottetheo.androiderestaurant.model.Items
import org.json.JSONObject

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var categorytodisplay: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadDishesFromAPI()

        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Modifier le titre en fct de la cat seletionee
        categorytodisplay = intent.getStringExtra("categorytodisplay") ?: ""
        //if null renvoie empty


        val actionBar = supportActionBar
        binding.mealTitleTop1.text = categorytodisplay
        actionBar?.title = categorytodisplay


        binding.mealImage.setImageResource(R.drawable.salade)

        val value: ArrayList<Items> = arrayListOf()
        binding.categoryList.layoutManager = LinearLayoutManager(this)

        binding.categoryList.adapter = MealAdapter(value) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("detailitemtodisplay", it)
            startActivity(intent)
        }


    }


    private fun loadDishesFromAPI() {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject, {
            Log.w("MealActivity", "response : $it")
            handleAPIData(it.toString())
        }, {
            Log.w("MealActivity", "error : $it")

        })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String) {
        var dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategory = dishesResult.data.firstOrNull { it.nameFr == categorytodisplay }
        dishesResult.data[0].nameFr

        val adapter = binding.categoryList.adapter as MealAdapter
        adapter.refreshList(dishCategory?.items as ArrayList<Items>) //temporaire: juste pour renvoyer les titres de la description
    }
}
