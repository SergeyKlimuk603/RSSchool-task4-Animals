package by.klimuk.workingwithstorage.fragments

import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.activity.addCallback
import androidx.preference.PreferenceFragmentCompat
import by.klimuk.workingwithstorage.R
import by.klimuk.workingwithstorage.interfaces.SettingFragmentListener

class SettingsFragment : PreferenceFragmentCompat() {

    private var listener: SettingFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SettingFragmentListener) {
            listener = context
        }
        activity?.title = resources.getString(R.string.toolbar_title_settings)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            back()
        }
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.menu_settings)
        item.isVisible = false
    }

    private fun back() {
        listener?.back()
    }
}