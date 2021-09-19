package by.klimuk.workingwithstorage

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.klimuk.workingwithstorage.databinding.ActivityMainBinding
import by.klimuk.workingwithstorage.fragments.AnimalsFragment
import by.klimuk.workingwithstorage.fragments.EditAnimalFragment
import by.klimuk.workingwithstorage.fragments.SettingsFragment
import by.klimuk.workingwithstorage.interfaces.AnimalsFragmentListener
import by.klimuk.workingwithstorage.interfaces.EditAnimalFragmentListener
import by.klimuk.workingwithstorage.interfaces.SettingFragmentListener
import by.klimuk.workingwithstorage.models.Animal

class MainActivity : AppCompatActivity(),
    AnimalsFragmentListener, SettingFragmentListener, EditAnimalFragmentListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(AnimalsFragment.newInstance(), false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        runSettings()
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment, backStack: Boolean) {
        if (backStack) {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    // Interfaces implementation-------------------------------------------------------
    //AnimalsFragmentListener implementation
    override fun runSettings() {
        replaceFragment(SettingsFragment(), false)
    }
    override fun createAnimal(animal: Animal?) {
        replaceFragment(EditAnimalFragment.newInstance(animal), true)
    }

    //SettingFragmentListener implementation
    override fun back() {
        supportFragmentManager.popBackStack()
        replaceFragment(AnimalsFragment.newInstance(), false)
    }

    //EditAnimalFragmentListener implementation
    override fun create(animal: Animal) {
    }
    override fun update(animal: Animal) {
    }
}