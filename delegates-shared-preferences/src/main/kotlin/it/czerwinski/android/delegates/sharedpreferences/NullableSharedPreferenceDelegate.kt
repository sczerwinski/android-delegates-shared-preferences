package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

class NullableSharedPreferenceDelegate<T>(
		context: Context,
		private val key: String,
		private val defaultValue: T,
		private val getter: SharedPreferences.(String, T) -> T,
		private val setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor) {

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	operator fun getValue(ref: Any?, property: KProperty<*>): T? =
			if (sharedPreferences.contains(key)) sharedPreferences.getter(key, defaultValue)
			else null

	operator fun setValue(ref: Any?, property: KProperty<*>, value: T?): Unit {
		val editor = sharedPreferences.edit()
		if (value != null) editor.setter(key, value)
		else editor.remove(key)
		editor.apply()
	}
}
