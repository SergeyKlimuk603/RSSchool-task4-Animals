package by.klimuk.workingwithstorage.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.klimuk.workingwithstorage.databinding.AnimalItemBinding
import by.klimuk.workingwithstorage.interfaces.AnimalItemListener
import by.klimuk.workingwithstorage.models.Animal

class AnimalsAdapter (
    private val context: Context,
    private val btnListener: AnimalItemListener
        ) : ListAdapter<Animal, AnimalViewHolder> (itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AnimalItemBinding.inflate(layoutInflater, parent, false)
        return AnimalViewHolder(binding, context, btnListener)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {
        private val itemComparator = object : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.name == newItem.name
            }
            override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.age == newItem.age &&
                        oldItem.breed == newItem.breed
            }
        }
    }
}