package it.czerwinski.android.delegates.sharedpreferences

import android.content.SharedPreferences

fun SharedPreferences.getDouble(key: String, defaultValue: Double): Double =
		if (contains(key)) java.lang.Double.longBitsToDouble(getLong(key, 0L))
		else defaultValue

fun SharedPreferences.Editor.putDouble(key: String, value: Double): SharedPreferences.Editor {
	putLong(key, java.lang.Double.doubleToRawLongBits(value))
	return this
}
