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

fun Context.longSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Long>(
				this, key, 0L,
				SharedPreferences::getLong,
				SharedPreferences.Editor::putLong)

fun Context.longSharedPreference(key: String, defaultValue: Long) =
		SharedPreferenceDelegate<Long>(
				this, key, defaultValue,
				SharedPreferences::getLong,
				SharedPreferences.Editor::putLong)

fun Context.floatSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Float>(
				this, key, 0f,
				SharedPreferences::getFloat,
				SharedPreferences.Editor::putFloat)

fun Context.floatSharedPreference(key: String, defaultValue: Float) =
		SharedPreferenceDelegate<Float>(
				this, key, defaultValue,
				SharedPreferences::getFloat,
				SharedPreferences.Editor::putFloat)
