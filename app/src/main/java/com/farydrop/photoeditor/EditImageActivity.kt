package com.farydrop.photoeditor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.farydrop.photoeditor.databinding.ActivityEditImageBinding

class EditImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}