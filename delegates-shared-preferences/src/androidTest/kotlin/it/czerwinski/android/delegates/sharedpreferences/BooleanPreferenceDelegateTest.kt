package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class BooleanPreferenceDelegateTest {

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
		val testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertNull(testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY", false)
		// then:
		assertEquals(false, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldReturnNonNullBoolean() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY", false)
		// then compiles:
		val value: Boolean = testPreference
	}

	@Test
	@Throws(Exception::class)
	fun delegateForInitializedPreferenceShouldReturnValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putBoolean("TEST_DELEGATE_KEY", true)
				.apply()
		// when:
		val testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(true, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateDefaultValueShouldNotOverwriteInitializedPreference() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putBoolean("TEST_DELEGATE_KEY", true)
				.apply()
		// when:
		val testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY", false)
		// then:
		assertEquals(true, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = true
		// then:
		assertEquals(true, preferences.getBoolean("TEST_DELEGATE_KEY", false))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldRemovePreferenceIfSetToNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putBoolean("TEST_DELEGATE_KEY", true)
				.apply()
		var testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY")
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
		var testPreference by context.booleanSharedPreference("TEST_DELEGATE_KEY", false)
		// when:
		testPreference = true
		// then:
		assertEquals(true, preferences.getBoolean("TEST_DELEGATE_KEY", false))
	}

	@Test
	@Throws(Exception::class)
	fun twoDelegatesShouldHaveTheSameValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		var testPreference1 by context.booleanSharedPreference("TEST_DELEGATE_KEY")
		val testPreference2 by context.booleanSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference1 = true
		// then:
		assertEquals(true, testPreference2)
	}
}
