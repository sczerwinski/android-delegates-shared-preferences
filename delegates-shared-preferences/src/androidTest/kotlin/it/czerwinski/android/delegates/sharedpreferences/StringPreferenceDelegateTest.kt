package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class StringPreferenceDelegateTest {

	@Before
	@Throws(Exception::class)
	fun clearSharedPreferences() {
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit().clear().apply()
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldBeNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY")
		// then:
		assertEquals(null, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY", "Default value")
		// then:
		assertEquals("Default value", testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldReturnNonNullString() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY", "Default value")
		// then compiles:
		val text: String = testPreference
	}

	@Test
	@Throws(Exception::class)
	fun delegateForInitializedPreferenceShouldReturnValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putString("TEST_DELEGATE_KEY", "Test value")
				.apply()
		// when:
		val testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY")
		// then:
		assertEquals("Test value", testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateDefaultValueShouldNotOverwriteInitializedPreference() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putString("TEST_DELEGATE_KEY", "Test value")
				.apply()
		// when:
		val testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY", "Default value")
		// then:
		assertEquals("Test value", testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY")
		// when:
		testPreference = "New value"
		// then:
		assertEquals("New value", preferences.getString("TEST_DELEGATE_KEY", null))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldRemovePreferenceIfSetToNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putString("TEST_DELEGATE_KEY", "Initial value")
				.apply()
		var testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY")
		// when:
		testPreference = null
		// then:
		assertFalse(preferences.contains("TEST_DELEGATE_KEY"))
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldSetAssignedPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.stringPreferenceDelegate("TEST_DELEGATE_KEY", "Default value")
		// when:
		testPreference = "New value"
		// then:
		assertEquals("New value", preferences.getString("TEST_DELEGATE_KEY", null))
	}

	@Test
	@Throws(Exception::class)
	fun twoDelegatesShouldHaveTheSameValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		var testPreference1 by context.stringPreferenceDelegate("TEST_DELEGATE_KEY")
		val testPreference2 by context.stringPreferenceDelegate("TEST_DELEGATE_KEY")
		// when:
		testPreference1 = "Some value"
		// then:
		assertEquals("Some value", testPreference2)
	}
}
