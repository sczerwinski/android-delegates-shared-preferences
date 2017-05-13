package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

class IntPreferenceDelegate(context: Context, val key: String, val defaultValue: Int) {

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	operator fun getValue(a: Any?, b: KProperty<*>): Int =
			sharedPreferences.getInt(key, defaultValue);

	operator fun setValue(a: Any?, b: KProperty<*>, value: Int): Unit {
		sharedPreferences.edit()
				.putInt(key, value)
				.apply()
	}
}
