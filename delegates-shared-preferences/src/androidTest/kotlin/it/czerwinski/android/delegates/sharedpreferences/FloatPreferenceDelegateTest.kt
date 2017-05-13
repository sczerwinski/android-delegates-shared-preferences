package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class FloatPreferenceDelegateTest {

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
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertNull(testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY", 0.5f)
		// then:
		assertEquals(0.5f, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldReturnNonNullFloat() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY", 0f)
		// then compiles:
		val value: Float = testPreference
	}

	@Test
	@Throws(Exception::class)
	fun delegateForInitializedPreferenceShouldReturnValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", 1.7f)
				.apply()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(1.7f, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateDefaultValueShouldNotOverwriteInitializedPreference() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", 2.3f)
				.apply()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY", 0f)
		// then:
		assertEquals(2.3f, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = 1.3f
		// then:
		assertEquals(1.3f, preferences.getFloat("TEST_DELEGATE_KEY", 0f))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldRemovePreferenceIfSetToNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", 3.1f)
				.apply()
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
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
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY", 0f)
		// when:
		testPreference = 0.7f
		// then:
		assertEquals(0.7f, preferences.getFloat("TEST_DELEGATE_KEY", 0f))
	}

	@Test
	@Throws(Exception::class)
	fun twoDelegatesShouldHaveTheSameValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		var testPreference1 by context.floatSharedPreference("TEST_DELEGATE_KEY")
		val testPreference2 by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference1 = 3.7f
		// then:
		assertEquals(3.7f, testPreference2)
	}
}
