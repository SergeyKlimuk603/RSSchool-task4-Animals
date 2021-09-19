package by.klimuk.workingwithstorage.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import by.klimuk.workingwithstorage.R
import by.klimuk.workingwithstorage.databinding.FragmentEditAnimalBinding
import by.klimuk.workingwithstorage.db.dao.ORMLiteAnimalDao
import by.klimuk.workingwithstorage.db.dao.RoomAnimalDao
import by.klimuk.workingwithstorage.db.dao.SQLiteAnimalDao
import by.klimuk.workingwithstorage.interfaces.AnimalDao
import by.klimuk.workingwithstorage.interfaces.SettingFragmentListener
import by.klimuk.workingwithstorage.models.Animal
import by.klimuk.workingwithstorage.ulilites.*

class EditAnimalFragment : Fragment() {

    private var _binding: FragmentEditAnimalBinding? = null
    private val binding get() = _binding!!

    private var listener: SettingFragmentListener? = null

    private lateinit var animalDao: AnimalDao
    private var animal: Animal? = null

    private var storage: String = DEFAULT_STORAGE

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        storage = prefs.getString(STORAGE_TYPE_KEY, DEFAULT_STORAGE).toString()
        when (storage) {
            SQLITE_STORAGE -> animalDao = SQLiteAnimalDao(context)
            ORMLITE_STORAGE -> animalDao = ORMLiteAnimalDao()
            ROOM_STORAGE -> animalDao = RoomAnimalDao()
        }
        if (context is SettingFragmentListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            animal = it.getSerializable(ANIMAL_KEY) as Animal?
        }
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.menu_settings)
        item.isVisible = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditAnimalBinding.inflate(inflater, container, false)
        if (animal != null) {
            animal?.let {
                binding.etName.setText(it.name)
                binding.etAge.setText(it.age.toString())
                binding.etBreed.setText(it.breed)
            }
            activity?.title = resources.getString(R.string.toolbar_title_update_animal)
        } else {
            activity?.title = resources.getString(R.string.toolbar_title_add_new_animal)
        }

        binding.btnAdd.setOnClickListener(saveButtonListener)
        return binding.root
    }

    private val saveButtonListener = View.OnClickListener {
        val name = binding.etName.text.toString()
        val age = binding.etAge.text.toString().toIntOrNull()
        val breed = binding.etBreed.text.toString()
        if (name == "" || age == null || age < 0 || breed == "") {
            showToast(resources.getString(R.string.toast_enter_correct_data))
        } else {
            if (animal == null) {
                animal = Animal(null, name, age, breed)
                animal?.let {
                    animalDao.create(it)
                    showToast("Animal ${it.name} was created")
                }
            } else {
                animal?.let {
                    val updatedAnimal = Animal(it.id, name, age, breed)
                    animalDao.update(updatedAnimal)
                    showToast("Animal ${it.name} was updated")
                }
            }
            listener?.back()
        }
    }

    private fun showToast(message: String) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
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
        fun newInstance(animal: Animal?) =
            EditAnimalFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ANIMAL_KEY, animal)
                }
            }
    }
}