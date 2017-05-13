package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

class NullableIntPreferenceDelegate(context: Context, val key: String) {

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	operator fun getValue(a: Any?, b: KProperty<*>): Int? =
			if (sharedPreferences.contains(key)) sharedPreferences.getInt(key, 0)
			else null

	operator fun setValue(a: Any?, b: KProperty<*>, value: Int?): Unit {
		val editor = sharedPreferences.edit()
		if (value != null) editor.putInt(key, value)
		else editor.remove(key)
		editor.apply()
	}
}
