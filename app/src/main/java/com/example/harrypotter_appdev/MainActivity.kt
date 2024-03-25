package com.example.harrypotter_appdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harrypotter_appdev.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        initGetCharacters()
        adapter = CharacterAdapter()
        binding.rvCharacters.setHasFixedSize(true)
        binding.rvCharacters.layoutManager = LinearLayoutManager(this)
        binding.rvCharacters.adapter = adapter
    }

    private fun initGetCharacters() {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = retrofit.create(ApiService::class.java).getAllCharacters()
                val response = call.execute()
                if (response.isSuccessful) {
                    val characters = response.body()
                    characters?.let {
                        runOnUiThread{
                            adapter.updateList(characters)
                            binding.progressBar.isVisible = false
                        }
                    }
                } else {
                    Log.e("API Call", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API Call", "Error: ${e.message}", e)
                runOnUiThread{
                    binding.progressBar.isVisible = false
                }
            }
        }
    }




    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}