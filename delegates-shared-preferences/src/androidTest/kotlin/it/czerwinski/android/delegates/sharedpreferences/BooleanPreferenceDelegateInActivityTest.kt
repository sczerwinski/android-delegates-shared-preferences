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
class BooleanPreferenceDelegateInActivityTest {

	@Rule
	@JvmField
	var activityRule = ActivityTestRule<BooleanPreferenceDelegateActivity>(BooleanPreferenceDelegateActivity::class.java)

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
		assertEquals(true, delegateValue)
	}

	@Test
	@Throws(Exception::class)
	fun delegatedActivityFieldShouldSetPreferenceValue() {
		// given:
		val delegateActivity = activityRule.activity
		val preferences = PreferenceManager.getDefaultSharedPreferences(delegateActivity)
		// when:
		delegateActivity.writablePreference = false
		// then:
		assertEquals(false, preferences.getBoolean("TEST_DELEGATE_KEY", true))
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToActivityFieldShouldBeReadFromLocalDelegate() {
		// given:
		val delegateActivity = activityRule.activity
		val testPreference by delegateActivity.booleanSharedPreference("TEST_DELEGATE_KEY", true)
		// when:
		delegateActivity.writablePreference = false
		// then:
		assertEquals(false, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToLocalDelegateShouldBeReadFromActivityField() {
		// given:
		val delegateActivity = activityRule.activity
		var testPreference by delegateActivity.booleanSharedPreference("TEST_DELEGATE_KEY", true)
		// when:
		testPreference = false
		// then:
		assertEquals(false, delegateActivity.readOnlyPreference)
	}
}
