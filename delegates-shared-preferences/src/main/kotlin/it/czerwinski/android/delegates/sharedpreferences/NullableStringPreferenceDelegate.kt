package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

class NullableStringPreferenceDelegate(context: Context, val key: String) {

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	operator fun getValue(a: Any?, b: KProperty<*>): String? =
			sharedPreferences.getString(key, null)

	operator fun setValue(a: Any?, b: KProperty<*>, value: String?): Unit {
		sharedPreferences.edit()
				.putString(key, value)
				.apply()
	}
}
