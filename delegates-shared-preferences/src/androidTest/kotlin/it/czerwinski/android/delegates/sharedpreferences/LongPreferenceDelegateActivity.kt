package it.czerwinski.android.delegates.sharedpreferences

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout

class LongPreferenceDelegateActivity : Activity() {

	val readOnlyPreference by longSharedPreference("TEST_DELEGATE_KEY")
	val readOnlyPreferenceWithDefaultValue by longSharedPreference("TEST_DELEGATE_KEY", 123_456_789_000L)
	var writablePreference by longSharedPreference("TEST_DELEGATE_KEY")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(RelativeLayout(this))
	}
}