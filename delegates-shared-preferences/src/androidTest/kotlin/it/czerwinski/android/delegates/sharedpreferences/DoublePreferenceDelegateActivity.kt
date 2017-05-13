package it.czerwinski.android.delegates.sharedpreferences

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout

class DoublePreferenceDelegateActivity : Activity() {

	val readOnlyPreference by doubleSharedPreference("TEST_DELEGATE_KEY")
	val readOnlyPreferenceWithDefaultValue by doubleSharedPreference("TEST_DELEGATE_KEY", 0.0001)
	var writablePreference by doubleSharedPreference("TEST_DELEGATE_KEY")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(RelativeLayout(this))
	}
}