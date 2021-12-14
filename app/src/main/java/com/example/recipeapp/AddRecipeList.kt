package com.example.recipeapp

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRecipeList : AppCompatActivity() {
    private lateinit var etTitle: EditText
    private lateinit var etAuthor: EditText
    private lateinit var etIngredients: EditText
    private lateinit var etInstructions: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe_list)
        etTitle = findViewById(R.id.title)
        etAuthor = findViewById(R.id.author)
        etIngredients = findViewById(R.id.ingredients)
        etInstructions = findViewById(R.id.instructions)
        btnSubmit = findViewById(R.id.submit)
        btnCancel = findViewById(R.id.cancel)

        btnSubmit.setOnClickListener {
            val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

            apiInterface!!.addUser(
                RecipeList(
                    0,
                    etTitle.text.toString(),
                    etAuthor.text.toString(),
                    etIngredients.text.toString(),
                    etInstructions.text.toString()

                )
            ).enqueue(object : Callback<RecipeList> {
                override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                    Toast.makeText(applicationContext, "User added!", Toast.LENGTH_LONG).show()
                    recreate()
                }

                override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                    Log.d("MAIN", "Something went wrong!")
                }

            })
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btnCancel.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


}