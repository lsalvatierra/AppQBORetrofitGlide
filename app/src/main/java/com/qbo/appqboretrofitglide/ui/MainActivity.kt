package com.qbo.appqboretrofitglide.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.qbo.appqboretrofitglide.adapter.PhotoAdapter
import com.qbo.appqboretrofitglide.api.ApiPhotoService
import com.qbo.appqboretrofitglide.databinding.ActivityMainBinding
import com.qbo.appqboretrofitglide.model.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var apiservice: ApiPhotoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val vista = binding.root
        setContentView(vista)
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        //binding.rvphoto.layoutManager = LinearLayoutManager(this)
        //binding.rvphoto.layoutManager = LinearLayoutManager(this)
        //binding.rvphoto.layoutManager = StaggeredGridLayoutManager(3,
        //      StaggeredGridLayoutManager.VERTICAL)
        binding.rvphoto.layoutManager = GridLayoutManager(this, 3)
        apiservice = retrofit.create<ApiPhotoService>(ApiPhotoService::class.java)
        obtenerPhotos()

    }

    private fun obtenerPhotos() {
        apiservice.getAllPhotos().enqueue(object: Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if(!response.body().isNullOrEmpty()){
                    val lista = response.body()!!
                    binding.rvphoto.apply {
                        adapter = PhotoAdapter(lista)
                    }
                }
            }
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                Log.e("ErrorAPI", t.message)
            }
        })
    }

}