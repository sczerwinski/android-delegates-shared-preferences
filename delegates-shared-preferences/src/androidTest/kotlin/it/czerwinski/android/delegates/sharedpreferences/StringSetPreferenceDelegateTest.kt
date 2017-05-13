package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class StringSetPreferenceDelegateTest {

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
		val testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(null, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateForUninitializedPreferenceShouldReturnDefaultValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY", setOf("abc", "def"))
		// then:
		assertEquals(setOf("abc", "def"), testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateWithDefaultValueShouldReturnNonNullStringSet() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		// when:
		val testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY", setOf("abc", "def"))
		// then compiles:
		val set: Set<String> = testPreference
	}

	@Test
	@Throws(Exception::class)
	fun delegateForInitializedPreferenceShouldReturnValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putStringSet("TEST_DELEGATE_KEY", setOf("xyz", "abc"))
				.apply()
		// when:
		val testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(setOf("xyz", "abc"), testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateDefaultValueShouldNotOverwriteInitializedPreference() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putStringSet("TEST_DELEGATE_KEY", setOf("xyz", "abc"))
				.apply()
		// when:
		val testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY", emptySet())
		// then:
		assertEquals(setOf("xyz", "abc"), testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPreferenceValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = setOf("xyz", "abc")
		// then:
		assertEquals(setOf("xyz", "abc"), preferences.getStringSet("TEST_DELEGATE_KEY", null))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldRemovePreferenceIfSetToNull() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putStringSet("TEST_DELEGATE_KEY", emptySet())
				.apply()
		var testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY")
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
		var testPreference by context.stringSetSharedPreference("TEST_DELEGATE_KEY", setOf("abc", "def"))
		// when:
		testPreference = setOf("xyz", "abc")
		// then:
		assertEquals(setOf("xyz", "abc"), preferences.getStringSet("TEST_DELEGATE_KEY", null))
	}

	@Test
	@Throws(Exception::class)
	fun twoDelegatesShouldHaveTheSameValue() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		var testPreference1 by context.stringSetSharedPreference("TEST_DELEGATE_KEY")
		val testPreference2 by context.stringSetSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference1 = setOf("xyz", "abc")
		// then:
		assertEquals(setOf("xyz", "abc"), testPreference2)
	}
}
