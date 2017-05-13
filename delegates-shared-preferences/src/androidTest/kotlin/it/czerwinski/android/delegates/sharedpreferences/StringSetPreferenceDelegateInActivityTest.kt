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
class StringSetPreferenceDelegateInActivityTest {

	@Rule
	@JvmField
	var activityRule = ActivityTestRule<StringSetPreferenceDelegateActivity>(StringSetPreferenceDelegateActivity::class.java)

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
		assertEquals(setOf("abc", "def"), delegateValue)
	}

	@Test
	@Throws(Exception::class)
	fun delegatedActivityFieldShouldSetPreferenceValue() {
		// given:
		val delegateActivity = activityRule.activity
		val preferences = PreferenceManager.getDefaultSharedPreferences(delegateActivity)
		// when:
		delegateActivity.writablePreference = setOf("xyz", "mno")
		// then:
		assertEquals(setOf("xyz", "mno"), preferences.getStringSet("TEST_DELEGATE_KEY", null))
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToActivityFieldShouldBeReadFromLocalDelegate() {
		// given:
		val delegateActivity = activityRule.activity
		val testPreference by delegateActivity.stringSetSharedPreference("TEST_DELEGATE_KEY", emptySet())
		// when:
		delegateActivity.writablePreference = setOf("xyz", "mno")
		// then:
		assertEquals(setOf("xyz", "mno"), testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun valueWrittenToLocalDelegateShouldBeReadFromActivityField() {
		// given:
		val delegateActivity = activityRule.activity
		var testPreference by delegateActivity.stringSetSharedPreference("TEST_DELEGATE_KEY", setOf("xyz", "mno"))
		// when:
		testPreference = emptySet()
		// then:
		assertEquals(emptySet<String>(), delegateActivity.readOnlyPreference)
	}
}
