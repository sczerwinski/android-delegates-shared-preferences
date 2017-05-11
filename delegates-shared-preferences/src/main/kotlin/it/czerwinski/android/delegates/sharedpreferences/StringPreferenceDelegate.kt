package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

class StringPreferenceDelegate(context: Context, val key: String, val defaultValue: String) {

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	operator fun getValue(a: Any?, b: KProperty<*>): String =
			sharedPreferences.getString(key, defaultValue)

	operator fun setValue(a: Any?, b: KProperty<*>, value: String): Unit {
		sharedPreferences.edit()
				.putString(key, value)
				.apply()
	}
}
