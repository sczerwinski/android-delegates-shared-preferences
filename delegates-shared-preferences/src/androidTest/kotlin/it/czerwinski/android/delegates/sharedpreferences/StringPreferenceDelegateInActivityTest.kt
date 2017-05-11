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
class StringPreferenceDelegateInActivityTest {

	@Rule
	@JvmField
	var activityRule = ActivityTestRule<StringPreferenceDelegateActivity>(StringPreferenceDelegateActivity::class.java)

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
		assertEquals("Default value", delegateValue)
	}

	@Test
	@Throws(Exception::class)
	fun delegatedActivityFieldShouldSetPreferenceValue() {
		// given:
		val delegateActivity = activityRule.activity
		val preferences = PreferenceManager.getDefaultSharedPreferences(delegateActivity)
		// when:
		delegateActivity.writablePreference = "New value"
		// then:
		assertEquals("New value", preferences.getString("TEST_DELEGATE_KEY", null))
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToActivityFieldShouldBeReadFromLocalDelegate() {
		// given:
		val delegateActivity = activityRule.activity
		val testPreference by delegateActivity.stringPreferenceDelegate("TEST_DELEGATE_KEY", "Default value")
		// when:
		delegateActivity.writablePreference = "Some value"
		// then:
		assertEquals("Some value", testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToLocalDelegateShouldBeReadFromActivityField() {
		// given:
		val delegateActivity = activityRule.activity
		var testPreference by delegateActivity.stringPreferenceDelegate("TEST_DELEGATE_KEY", "Default value")
		// when:
		testPreference = "Another value"
		// then:
		assertEquals("Another value", delegateActivity.readOnlyPreference)
	}
}
