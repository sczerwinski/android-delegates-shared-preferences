package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

/**
 * Represents a nullable value stored in Android [SharedPreferences].
 *
 * @constructor Creates a new instance of [NullableSharedPreferenceDelegate].
 *
 * @param context The Android [Context] of the preference.
 *
 * @property key The name of the preference.
 * @property defaultValue The value to be returned if the preference does not exist.
 * @property getter The getter method to be used to retrieve the preference.
 * @property setter The setter method to be used to store the preference.
 */
class NullableSharedPreferenceDelegate<T>(
		context: Context,
		private val key: String,
		private val defaultValue: T,
		private val getter: SharedPreferences.(String, T) -> T,
		private val setter: SharedPreferences.Editor.(String, T) -> SharedPreferences.Editor) {

	private val sharedPreferences by lazy {
		PreferenceManager.getDefaultSharedPreferences(context)
	}

	/**
	 * Retrieves the value of the preference.
	 *
	 * @param ref Reference to the delegating object.
	 * @param property Reference to the delegating property.
	 *
	 * @return The value of the preference.
	 */
	operator fun getValue(ref: Any?, property: KProperty<*>): T? =
			if (sharedPreferences.contains(key)) sharedPreferences.getter(key, defaultValue)
			else null

	/**
	 * Stores the value of the preference.
	 *
	 * @param ref Reference to the delegating object.
	 * @param property Reference to the delegating property.
	 * @param value The value of the preference.
	 */
	operator fun setValue(ref: Any?, property: KProperty<*>, value: T?): Unit {
		val editor = sharedPreferences.edit()
		if (value != null) editor.setter(key, value)
		else editor.remove(key)
		editor.apply()
	}
}
