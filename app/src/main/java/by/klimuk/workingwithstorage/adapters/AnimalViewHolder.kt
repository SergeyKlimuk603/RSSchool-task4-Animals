package by.klimuk.workingwithstorage.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import by.klimuk.workingwithstorage.R
import by.klimuk.workingwithstorage.databinding.AnimalItemBinding
import by.klimuk.workingwithstorage.interfaces.AnimalItemListener
import by.klimuk.workingwithstorage.models.Animal

class AnimalViewHolder (
    private val binding: AnimalItemBinding,
    private val context: Context,
    private val btnListener: AnimalItemListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: Animal) {
            val name = "${context.resources.getString(R.string.name)}:  ${animal.name}"
            val age = "${context.resources.getString(R.string.age)}:  ${animal.age}"
            val breed = "${context.resources.getString(R.string.breed)}:  ${animal.breed}"
            binding.apply{
                tvName.text = name
                tvAge.text = age
                tvBreed.text = breed
            }
            initButton(animal)
        }

    private fun initButton(animal: Animal) {
        binding.apply {
            btnEdit.setOnClickListener {
                btnListener.edit(animal)
            }
            btnDelete.setOnClickListener {
                btnListener.delete(animal)
            }
        }
    }
}