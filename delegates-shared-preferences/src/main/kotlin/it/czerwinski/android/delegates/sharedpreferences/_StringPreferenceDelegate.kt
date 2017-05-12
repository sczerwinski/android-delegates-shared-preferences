package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context

fun Context.stringPreferenceDelegate(key: String): NullableStringPreferenceDelegate =
		NullableStringPreferenceDelegate(this, key)

fun Context.stringPreferenceDelegate(key: String, defaultValue: String): StringPreferenceDelegate =
		StringPreferenceDelegate(this, key, defaultValue)
