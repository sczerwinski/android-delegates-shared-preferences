package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class IntPreferenceDelegateTest {

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
		val testPreference by context.intSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(null, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.intSharedPreference("TEST_DELEGATE_KEY", 5)
		// then:
		assertEquals(5, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldReturnNonNullInt() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.intSharedPreference("TEST_DELEGATE_KEY", 0)
		// then compiles:
		val value: Int = testPreference
	}

	@Test
	@Throws(Exception::class)
	fun delegateForInitializedPreferenceShouldReturnValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putInt("TEST_DELEGATE_KEY", 17)
				.apply()
		// when:
		val testPreference by context.intSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(17, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateDefaultValueShouldNotOverwriteInitializedPreference() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putInt("TEST_DELEGATE_KEY", 23)
				.apply()
		// when:
		val testPreference by context.intSharedPreference("TEST_DELEGATE_KEY", 0)
		// then:
		assertEquals(23, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.intSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = 13
		// then:
		assertEquals(13, preferences.getInt("TEST_DELEGATE_KEY", 0))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldRemovePreferenceIfSetToNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putInt("TEST_DELEGATE_KEY", 31)
				.apply()
		var testPreference by context.intSharedPreference("TEST_DELEGATE_KEY")
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
		var testPreference by context.intSharedPreference("TEST_DELEGATE_KEY", 0)
		// when:
		testPreference = 7
		// then:
		assertEquals(7, preferences.getInt("TEST_DELEGATE_KEY", 0))
	}

	@Test
	@Throws(Exception::class)
	fun twoDelegatesShouldHaveTheSameValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		var testPreference1 by context.intSharedPreference("TEST_DELEGATE_KEY")
		val testPreference2 by context.intSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference1 = 37
		// then:
		assertEquals(37, testPreference2)
	}
}
