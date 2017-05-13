package it.czerwinski.android.delegates.sharedpreferences

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

fun SharedPreferences.getDouble(key: String, defaultValue: Double): Double =
		if (contains(key)) java.lang.Double.longBitsToDouble(getLong(key, 0L))
		else defaultValue

fun Editor.putDouble(key: String, value: Double): Editor {
	putLong(key, java.lang.Double.doubleToRawLongBits(value))
	return this
}
