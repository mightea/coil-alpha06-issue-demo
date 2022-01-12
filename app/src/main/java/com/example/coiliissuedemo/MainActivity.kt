package com.example.coiliissuedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coiliissuedemo.databinding.ActivityMainBinding

import android.content.ContentResolver
import android.net.Uri
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger


class MainActivity : AppCompatActivity(), ImageLoaderFactory {
    private lateinit var binding: ActivityMainBinding

    private fun res2uri(resId: Int?): Uri? {
        return resId?.let {
            val resources = resources

            Uri.Builder()
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(resources.getResourcePackageName(it))
                .appendPath(it.toString())
                .build()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MainViewModel()
        viewModel.img1 = res2uri(R.drawable.logo_vector)
        viewModel.img2 = res2uri(R.drawable.logo_bmp)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        binding.model = viewModel

        setContentView(view)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .logger(DebugLogger())
            .build()
    }
}