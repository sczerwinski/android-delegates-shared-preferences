package it.czerwinski.android.delegates.sharedpreferences

import android.app.Activity
import android.os.Bundle
import android.widget.RelativeLayout

class IntPreferenceDelegateActivity : Activity() {

	val readOnlyPreference by intSharedPreference("TEST_DELEGATE_KEY")
	val readOnlyPreferenceWithDefaultValue by intSharedPreference("TEST_DELEGATE_KEY", 1024)
	var writablePreference by intSharedPreference("TEST_DELEGATE_KEY")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(RelativeLayout(this))
	}
}