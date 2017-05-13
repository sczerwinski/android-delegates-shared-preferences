package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

fun Context.stringPreferenceDelegate(key: String) =
		NullableSharedPreferenceDelegate<String>(
				this, key, "",
				SharedPreferences::getString,
				SharedPreferences.Editor::putString)

fun Context.stringPreferenceDelegate(key: String, defaultValue: String) =
		SharedPreferenceDelegate<String>(
				this, key, defaultValue,
				SharedPreferences::getString,
				SharedPreferences.Editor::putString)
