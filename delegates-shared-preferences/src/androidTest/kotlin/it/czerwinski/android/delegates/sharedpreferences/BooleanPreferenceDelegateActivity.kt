package it.czerwinski.android.delegates.sharedpreferences

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout

class BooleanPreferenceDelegateActivity : Activity() {

	val readOnlyPreference by booleanSharedPreference("TEST_DELEGATE_KEY")
	val readOnlyPreferenceWithDefaultValue by booleanSharedPreference("TEST_DELEGATE_KEY", true)
	var writablePreference by booleanSharedPreference("TEST_DELEGATE_KEY")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(RelativeLayout(this))
	}
}