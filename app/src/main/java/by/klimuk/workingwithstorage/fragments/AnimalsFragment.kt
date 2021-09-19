package by.klimuk.workingwithstorage.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.klimuk.workingwithstorage.R
import by.klimuk.workingwithstorage.adapters.AnimalsAdapter
import by.klimuk.workingwithstorage.databinding.FragmentAnimalsBinding
import by.klimuk.workingwithstorage.db.dao.ORMLiteAnimalDao
import by.klimuk.workingwithstorage.db.dao.RoomAnimalDao
import by.klimuk.workingwithstorage.db.dao.SQLiteAnimalDao
import by.klimuk.workingwithstorage.interfaces.AnimalDao
import by.klimuk.workingwithstorage.interfaces.AnimalItemListener
import by.klimuk.workingwithstorage.interfaces.AnimalsFragmentListener
import by.klimuk.workingwithstorage.models.Animal
import by.klimuk.workingwithstorage.ulilites.*

class AnimalsFragment : Fragment(), AnimalItemListener {

    private var _binding: FragmentAnimalsBinding? = null
    private val binding get() = _binding!!

    private var listener: AnimalsFragmentListener? = null

    private var animalsAdapter: AnimalsAdapter? = null

    private lateinit var animalDao: AnimalDao

    private var storage: String = DEFAULT_STORAGE
    private var sortCriterion: String = DEFAULT_SORT_BY

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        storage = prefs.getString(STORAGE_TYPE_KEY, DEFAULT_STORAGE).toString()
        sortCriterion = prefs.getString(SORT_BY_KEY, DEFAULT_SORT_BY).toString()
        when (storage) {
            SQLITE_STORAGE -> animalDao = SQLiteAnimalDao(context)
            ORMLITE_STORAGE -> animalDao = ORMLiteAnimalDao()
            ROOM_STORAGE -> animalDao = RoomAnimalDao()
        }
        animalsAdapter = AnimalsAdapter(context, this)
        if (context is AnimalsFragmentListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalsBinding.inflate(inflater, container, false)
        binding.btnAdd.setOnClickListener {
            listener?.createAnimal()
        }
        activity?.title = resources.getString(R.string.toolbar_title_animals)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.apply {
            tvStorageValue.text = "Storage: $storage"
            tvSortingValue.text = "Sort by: $sortCriterion"
            rvAnimals.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = animalsAdapter
            }
        }
        updateRecyclerView(getSortedListAnimalsFromDB())
    }

    private fun updateRecyclerView(newAnimalsList: List<Animal>) {
        animalsAdapter?.submitList(newAnimalsList)
    }

    private fun getSortedListAnimalsFromDB(): List<Animal> {
        val listFromDB = animalDao.queryForAll()
        return sortList(listFromDB)
    }

    private fun sortList(list: List<Animal>): List<Animal> {
        return when (sortCriterion) {
            SORT_BY_NAME -> list.sortedBy { it.name }
            SORT_BY_AGE -> list.sortedBy { it.age }
            SORT_BY_BREED -> list.sortedBy { it.breed }
            else -> list
        }
    }

    override fun delete(animal: Animal) {
        animalDao.delete(animal)
        updateRecyclerView(getSortedListAnimalsFromDB())
    }

    override fun edit(animal: Animal) {
        listener?.createAnimal(animal)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        animalDao.close()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AnimalsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}