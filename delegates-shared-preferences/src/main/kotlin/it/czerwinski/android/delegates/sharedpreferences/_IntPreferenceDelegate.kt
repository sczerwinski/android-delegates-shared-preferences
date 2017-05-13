package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

fun Context.intPreferenceDelegate(key: String) =
		NullableSharedPreferenceDelegate<Int>(
				this, key, 0,
				SharedPreferences::getInt,
				SharedPreferences.Editor::putInt)

fun Context.intPreferenceDelegate(key: String, defaultValue: Int) =
		SharedPreferenceDelegate<Int>(
				this, key, defaultValue,
				SharedPreferences::getInt,
				SharedPreferences.Editor::putInt)
