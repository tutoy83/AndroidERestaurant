package fr.isen.mignottetheo.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.util.Log
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intent = Intent(this, MealActivity::class.java)

        val actionstarter = findViewById<Button>(R.id.homeStarterSelection)
        actionstarter.setOnClickListener{
            intent.putExtra("categorytodisplay", "Entrees")
            startActivity(intent)
        }

        val actionmainmeal = findViewById<Button>(R.id.homeMainmealSelection)
        actionmainmeal.setOnClickListener{
            intent.putExtra("categorytodisplay", "Plats")
            startActivity(intent)
        }
        val actiondesert = findViewById<Button>(R.id.homeDesertSelection)
        actiondesert.setOnClickListener{
            intent.putExtra("categorytodisplay", "Desserts")
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Debug:", "On Destroy entered")
    }
}