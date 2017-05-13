package it.czerwinski.android.delegates.sharedpreferences

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout

class FloatPreferenceDelegateActivity : Activity() {

	val readOnlyPreference by floatSharedPreference("TEST_DELEGATE_KEY")
	val readOnlyPreferenceWithDefaultValue by floatSharedPreference("TEST_DELEGATE_KEY", 0.0001f)
	var writablePreference by floatSharedPreference("TEST_DELEGATE_KEY")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(RelativeLayout(this))
	}
}