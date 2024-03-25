package com.example.harrypotter_appdev

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypotter_appdev.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = CharacterItemBinding.bind(view)

    fun bind(characterItemResponse: Character) {
        if (characterItemResponse.image.isNotEmpty()) {
            Picasso.get().load(characterItemResponse.image).into(binding.imgCharacter)
        } else {
            Picasso.get().load(R.drawable.howards).into(binding.imgCharacter)
        }
        binding.tvName.text = characterItemResponse.name
        binding.tvGender.text = characterItemResponse.gender
        binding.tvSpecies.text = characterItemResponse.species
    }
}