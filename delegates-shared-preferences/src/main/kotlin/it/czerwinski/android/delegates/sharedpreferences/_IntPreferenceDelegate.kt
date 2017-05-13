package it.czerwinski.android.delegates.sharedpreferences

import android.content.Context

fun Context.intPreferenceDelegate(key: String): NullableIntPreferenceDelegate =
		NullableIntPreferenceDelegate(this, key)

fun Context.intPreferenceDelegate(key: String, defaultValue: Int): IntPreferenceDelegate =
		IntPreferenceDelegate(this, key, defaultValue)
