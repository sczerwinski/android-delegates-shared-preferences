package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DoublePreferenceDelegateTest {

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
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertNull(testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.5)
		// then:
		assertEquals(0.5, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldReturnNonNullDouble() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// then compiles:
		val value: Double = testPreference
	}

	@Test
	@Throws(Exception::class)
	fun delegateForInitializedPreferenceShouldReturnValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putDouble("TEST_DELEGATE_KEY", 1.7)
				.apply()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(1.7, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateDefaultValueShouldNotOverwriteInitializedPreference() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putDouble("TEST_DELEGATE_KEY", 2.3)
				.apply()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// then:
		assertEquals(2.3, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = 1.3
		// then:
		assertEquals(1.3, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldRemovePreferenceIfSetToNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putDouble("TEST_DELEGATE_KEY", 3.1)
				.apply()
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
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
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// when:
		testPreference = 0.7
		// then:
		assertEquals(0.7, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun twoDelegatesShouldHaveTheSameValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		var testPreference1 by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		val testPreference2 by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// when:
		testPreference1 = 3.7
		// then:
		assertEquals(3.7, testPreference2, 0.0)
	}
}
