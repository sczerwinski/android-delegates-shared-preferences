package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class LongPreferenceDelegateTest {

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
		val testPreference by context.longSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertNull(testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.longSharedPreference("TEST_DELEGATE_KEY", 123_456_789_005L)
		// then:
		assertEquals(123_456_789_005L, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldReturnNonNullLong() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.longSharedPreference("TEST_DELEGATE_KEY", 0L)
		// then compiles:
		val value: Long = testPreference
	}

	@Test
	@Throws(Exception::class)
	fun delegateForInitializedPreferenceShouldReturnValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putLong("TEST_DELEGATE_KEY", 123_456_789_017L)
				.apply()
		// when:
		val testPreference by context.longSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(123_456_789_017L, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateDefaultValueShouldNotOverwriteInitializedPreference() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putLong("TEST_DELEGATE_KEY", 123_456_789_023L)
				.apply()
		// when:
		val testPreference by context.longSharedPreference("TEST_DELEGATE_KEY", 0L)
		// then:
		assertEquals(123_456_789_023L, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.longSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = 123_456_789_013L
		// then:
		assertEquals(123_456_789_013L, preferences.getLong("TEST_DELEGATE_KEY", 0L))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldRemovePreferenceIfSetToNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putLong("TEST_DELEGATE_KEY", 123_456_789_031L)
				.apply()
		var testPreference by context.longSharedPreference("TEST_DELEGATE_KEY")
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
		var testPreference by context.longSharedPreference("TEST_DELEGATE_KEY", 0L)
		// when:
		testPreference = 123_456_789_007L
		// then:
		assertEquals(123_456_789_007L, preferences.getLong("TEST_DELEGATE_KEY", 0L))
	}

	@Test
	@Throws(Exception::class)
	fun twoDelegatesShouldHaveTheSameValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		var testPreference1 by context.longSharedPreference("TEST_DELEGATE_KEY")
		val testPreference2 by context.longSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference1 = 123_456_789_037L
		// then:
		assertEquals(123_456_789_037L, testPreference2)
	}
}
