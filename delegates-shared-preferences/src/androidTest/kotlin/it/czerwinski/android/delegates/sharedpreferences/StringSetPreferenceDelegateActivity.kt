package it.czerwinski.android.delegates.sharedpreferences

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout

class StringSetPreferenceDelegateActivity : Activity() {

	val readOnlyPreference by stringSetSharedPreference("TEST_DELEGATE_KEY")
	val readOnlyPreferenceWithDefaultValue by stringSetSharedPreference("TEST_DELEGATE_KEY", setOf("abc", "def"))
	var writablePreference by stringSetSharedPreference("TEST_DELEGATE_KEY")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(RelativeLayout(this))
	}
}