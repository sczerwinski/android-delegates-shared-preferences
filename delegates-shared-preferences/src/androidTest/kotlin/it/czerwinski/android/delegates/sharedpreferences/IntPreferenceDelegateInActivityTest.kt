package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class IntPreferenceDelegateInActivityTest {

	@Rule
	@JvmField
	var activityRule = ActivityTestRule<IntPreferenceDelegateActivity>(IntPreferenceDelegateActivity::class.java)

	@Before
	@Throws(Exception::class)
	fun clearSharedPreferences() {
		val delegateActivity = activityRule.activity
		val preferences = PreferenceManager.getDefaultSharedPreferences(delegateActivity)
		preferences.edit().clear().apply()
	}

	@Test
	@Throws(Exception::class)
	fun delegatedActivityFieldForUninitializedPreferenceShouldBeNull() {
		// given:
		val delegateActivity = activityRule.activity
		// when:
		val delegateValue = delegateActivity.readOnlyPreference
		// then:
		assertEquals(null, delegateValue)
	}

	@Test
	@Throws(Exception::class)
	fun delegatedActivityFieldForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val delegateActivity = activityRule.activity
		// when:
		val delegateValue = delegateActivity.readOnlyPreferenceWithDefaultValue
		// then:
		assertEquals(1024, delegateValue)
	}

	@Test
	@Throws(Exception::class)
	fun delegatedActivityFieldShouldSetPreferenceValue() {
		// given:
		val delegateActivity = activityRule.activity
		val preferences = PreferenceManager.getDefaultSharedPreferences(delegateActivity)
		// when:
		delegateActivity.writablePreference = 512
		// then:
		assertEquals(512, preferences.getInt("TEST_DELEGATE_KEY", 0))
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToActivityFieldShouldBeReadFromLocalDelegate() {
		// given:
		val delegateActivity = activityRule.activity
		val testPreference by delegateActivity.intPreferenceDelegate("TEST_DELEGATE_KEY", 117)
		// when:
		delegateActivity.writablePreference = 256
		// then:
		assertEquals(256, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToLocalDelegateShouldBeReadFromActivityField() {
		// given:
		val delegateActivity = activityRule.activity
		var testPreference by delegateActivity.intPreferenceDelegate("TEST_DELEGATE_KEY", 3)
		// when:
		testPreference = 4096
		// then:
		assertEquals(4096, delegateActivity.readOnlyPreference)
	}
}
