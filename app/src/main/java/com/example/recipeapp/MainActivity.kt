 package com.example.recipeapp

 import android.content.Intent
 import androidx.appcompat.app.AppCompatActivity
 import android.os.Bundle
 import android.util.Log
 import android.widget.Button
 import android.widget.EditText
 import android.widget.Toast
 import androidx.recyclerview.widget.LinearLayoutManager
 import androidx.recyclerview.widget.RecyclerView
 import retrofit2.Call
 import retrofit2.Callback
 import retrofit2.Response

 class MainActivity : AppCompatActivity() {
     private lateinit var rvMain: RecyclerView
     private lateinit var rvAdapter: RecyclerViewAdapter

     private lateinit var etName: EditText
     private lateinit var etLocation: EditText
     private lateinit var btAdd: Button

     private lateinit var recipe: Recipe

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         recipe = Recipe()
         btAdd = findViewById(R.id.btAdd)

         rvMain = findViewById(R.id.rvMain)
         rvAdapter = RecyclerViewAdapter(recipe)
         rvMain.adapter = rvAdapter
         rvMain.layoutManager = LinearLayoutManager(this)

         val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

         apiInterface?.getAll()?.enqueue(object : Callback<Recipe> {
             override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                 recipe = response.body()!!
                 rvAdapter.update(recipe)
             }

             override fun onFailure(call: Call<Recipe>, t: Throwable) {
                 Log.d("MAIN", "Unable to get data")
             }
         })


         btAdd.setOnClickListener {
             val intent = Intent(this, AddRecipeList::class.java)
             startActivity(intent)
         }
     }
 }