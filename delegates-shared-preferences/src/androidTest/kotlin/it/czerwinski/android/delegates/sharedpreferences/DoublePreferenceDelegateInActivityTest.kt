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
class DoublePreferenceDelegateInActivityTest {

	@Rule
	@JvmField
	var activityRule = ActivityTestRule<DoublePreferenceDelegateActivity>(DoublePreferenceDelegateActivity::class.java)

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
		assertEquals(0.0001, delegateValue, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegatedActivityFieldShouldSetPreferenceValue() {
		// given:
		val delegateActivity = activityRule.activity
		val preferences = PreferenceManager.getDefaultSharedPreferences(delegateActivity)
		// when:
		delegateActivity.writablePreference = 0.2
		// then:
		assertEquals(0.2, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToActivityFieldShouldBeReadFromLocalDelegate() {
		// given:
		val delegateActivity = activityRule.activity
		val testPreference by delegateActivity.doubleSharedPreference("TEST_DELEGATE_KEY", 3.14)
		// when:
		delegateActivity.writablePreference = 0.125
		// then:
		assertEquals(0.125, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToLocalDelegateShouldBeReadFromActivityField() {
		// given:
		val delegateActivity = activityRule.activity
		var testPreference by delegateActivity.doubleSharedPreference("TEST_DELEGATE_KEY", 0.333)
		// when:
		testPreference = 0.25
		// then:
		assertEquals(0.25, delegateActivity.readOnlyPreference)
	}
}
