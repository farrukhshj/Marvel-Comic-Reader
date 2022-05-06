package com.example.android.marvelcomicreader

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.marvelcomicreader.databinding.ActivityMainBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        val viewModelProviderFactory = MainActivityViewModelProviderFactory()
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.getComicData()

        viewModel.comicData.observe(this) {
            binding.tvComicTitle.text = it.title
            binding.tvDescription.text = it.description

            val picasso = Picasso.get()
            picasso.isLoggingEnabled = true
            picasso.load(it.coverImageUrl).into(binding.ivComicCover, object : Callback {
                override fun onSuccess() {
                    Log.d(javaClass.simpleName, "Image loaded successfully from URL")
                }

                override fun onError(e: Exception?) {
                    Log.d(javaClass.simpleName, "Image failed to load from URL")
                    picasso.load(R.drawable.cover).into(binding.ivComicCover)
                }

            })

        }


    }

}