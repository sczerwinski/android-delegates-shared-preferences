package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

fun Context.stringSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<String>(
				this, key, "",
				SharedPreferences::getString,
				SharedPreferences.Editor::putString)

fun Context.stringSharedPreference(key: String, defaultValue: String) =
		SharedPreferenceDelegate<String>(
				this, key, defaultValue,
				SharedPreferences::getString,
				SharedPreferences.Editor::putString)

fun Context.intSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Int>(
				this, key, 0,
				SharedPreferences::getInt,
				SharedPreferences.Editor::putInt)

fun Context.intSharedPreference(key: String, defaultValue: Int) =
		SharedPreferenceDelegate<Int>(
				this, key, defaultValue,
				SharedPreferences::getInt,
				SharedPreferences.Editor::putInt)
