package it.czerwinski.android.delegates.sharedpreferences

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

/**
 * Retrieves a Double preference.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return The value of the preference if it exists, or [defaultValue].
 */
fun SharedPreferences.getDouble(key: String, defaultValue: Double): Double =
		if (contains(key)) java.lang.Double.longBitsToDouble(getLong(key, 0L))
		else defaultValue

/**
 * Stores a Double preference value in the [Editor].
 *
 * @param key The name of the preference.
 * @param value The new value of the preference.
 *
 * @return The same [Editor] object.
 */
fun Editor.putDouble(key: String, value: Double): Editor {
	putLong(key, java.lang.Double.doubleToRawLongBits(value))
	return this
}
