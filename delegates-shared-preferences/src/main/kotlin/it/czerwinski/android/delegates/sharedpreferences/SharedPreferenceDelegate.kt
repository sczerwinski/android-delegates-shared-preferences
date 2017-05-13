package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

class SharedPreferenceDelegate<T>(
		context: Context,
		val key: String,
		val defaultValue: T,
		val getter: SharedPreferences.(String, T) -> T,
		val setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor) {

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	operator fun getValue(a: Any?, b: KProperty<*>): T =
			sharedPreferences.getter(key, defaultValue)

	operator fun setValue(a: Any?, b: KProperty<*>, value: T): Unit {
		sharedPreferences.edit()
				.setter(key, value)
				.apply()
	}
}
