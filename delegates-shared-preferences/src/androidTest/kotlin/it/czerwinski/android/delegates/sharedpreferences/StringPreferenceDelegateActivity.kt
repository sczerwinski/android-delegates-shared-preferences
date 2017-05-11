package it.czerwinski.android.delegates.sharedpreferences

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout

class StringPreferenceDelegateActivity : Activity() {

	val readOnlyPreference by stringPreferenceDelegate("TEST_DELEGATE_KEY")
	val readOnlyPreferenceWithDefaultValue by stringPreferenceDelegate("TEST_DELEGATE_KEY", "Default value")
	var writablePreference by stringPreferenceDelegate("TEST_DELEGATE_KEY")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(RelativeLayout(this))
	}
}