package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Creates a new instance of [NullableSharedPreferenceDelegate]
 * for a String preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [NullableSharedPreferenceDelegate] for a String preference.
 */
fun Context.stringSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<String>(
				this, key, "",
				SharedPreferences::getString,
				SharedPreferences.Editor::putString)

/**
 * Creates a new instance of [SharedPreferenceDelegate]
 * for a String preference in this Context.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return A new instance of [SharedPreferenceDelegate] for a String preference.
 */
fun Context.stringSharedPreference(key: String, defaultValue: String) =
		SharedPreferenceDelegate<String>(
				this, key, defaultValue,
				SharedPreferences::getString,
				SharedPreferences.Editor::putString)

/**
 * Creates a new instance of [NullableSharedPreferenceDelegate]
 * for an Int preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [NullableSharedPreferenceDelegate] for an Int preference.
 */
fun Context.intSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Int>(
				this, key, 0,
				SharedPreferences::getInt,
				SharedPreferences.Editor::putInt)

/**
 * Creates a new instance of [SharedPreferenceDelegate]
 * for an Int preference in this Context.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return A new instance of [SharedPreferenceDelegate] for an Int preference.
 */
fun Context.intSharedPreference(key: String, defaultValue: Int) =
		SharedPreferenceDelegate<Int>(
				this, key, defaultValue,
				SharedPreferences::getInt,
				SharedPreferences.Editor::putInt)

/**
 * Creates a new instance of [NullableSharedPreferenceDelegate]
 * for a Long preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [NullableSharedPreferenceDelegate] for a Long preference.
 */
fun Context.longSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Long>(
				this, key, 0L,
				SharedPreferences::getLong,
				SharedPreferences.Editor::putLong)

/**
 * Creates a new instance of [SharedPreferenceDelegate]
 * for a Long preference in this Context.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return A new instance of [SharedPreferenceDelegate] for a Long preference.
 */
fun Context.longSharedPreference(key: String, defaultValue: Long) =
		SharedPreferenceDelegate<Long>(
				this, key, defaultValue,
				SharedPreferences::getLong,
				SharedPreferences.Editor::putLong)

/**
 * Creates a new instance of [NullableSharedPreferenceDelegate]
 * for a Float preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [NullableSharedPreferenceDelegate] for a Float preference.
 */
fun Context.floatSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Float>(
				this, key, 0f,
				SharedPreferences::getFloat,
				SharedPreferences.Editor::putFloat)

/**
 * Creates a new instance of [SharedPreferenceDelegate]
 * for a Float preference in this Context.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return A new instance of [SharedPreferenceDelegate] for a Float preference.
 */
fun Context.floatSharedPreference(key: String, defaultValue: Float) =
		SharedPreferenceDelegate<Float>(
				this, key, defaultValue,
				SharedPreferences::getFloat,
				SharedPreferences.Editor::putFloat)

/**
 * Creates a new instance of [NullableSharedPreferenceDelegate]
 * for a Double preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [NullableSharedPreferenceDelegate] for a Double preference.
 */
fun Context.doubleSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Double>(
				this, key, 0.0,
				SharedPreferences::getDouble,
				SharedPreferences.Editor::putDouble)

/**
 * Creates a new instance of [SharedPreferenceDelegate]
 * for a Double preference in this Context.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return A new instance of [SharedPreferenceDelegate] for a Double preference.
 */
fun Context.doubleSharedPreference(key: String, defaultValue: Double) =
		SharedPreferenceDelegate<Double>(
				this, key, defaultValue,
				SharedPreferences::getDouble,
				SharedPreferences.Editor::putDouble)

/**
 * Creates a new instance of [NullableSharedPreferenceDelegate]
 * for a Boolean preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [NullableSharedPreferenceDelegate] for a Boolean preference.
 */
fun Context.booleanSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Boolean>(
				this, key, false,
				SharedPreferences::getBoolean,
				SharedPreferences.Editor::putBoolean)

/**
 * Creates a new instance of [SharedPreferenceDelegate]
 * for a Boolean preference in this Context.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return A new instance of [SharedPreferenceDelegate] for a Boolean preference.
 */
fun Context.booleanSharedPreference(key: String, defaultValue: Boolean) =
		SharedPreferenceDelegate<Boolean>(
				this, key, defaultValue,
				SharedPreferences::getBoolean,
				SharedPreferences.Editor::putBoolean)

/**
 * Creates a new instance of [NullableSharedPreferenceDelegate]
 * for a String Set preference in this Context.
 *
 * @param key The name of the preference.
 *
 * @return A new instance of [NullableSharedPreferenceDelegate] for a String Set preference.
 */
fun Context.stringSetSharedPreference(key: String) =
		NullableSharedPreferenceDelegate<Set<String>>(
				this, key, emptySet<String>(),
				SharedPreferences::getStringSet,
				SharedPreferences.Editor::putStringSet)

/**
 * Creates a new instance of [SharedPreferenceDelegate]
 * for a String Set preference in this Context.
 *
 * @param key The name of the preference.
 * @param defaultValue The value to be returned if the preference does not exist.
 *
 * @return A new instance of [SharedPreferenceDelegate] for a String Set preference.
 */
fun Context.stringSetSharedPreference(key: String, defaultValue: Set<String>) =
		SharedPreferenceDelegate<Set<String>>(
				this, key, defaultValue,
				SharedPreferences::getStringSet,
				SharedPreferences.Editor::putStringSet)
