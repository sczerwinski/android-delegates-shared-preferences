package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class FloatPreferenceDelegateCornerCasesTest {

	@Before
	@Throws(Exception::class)
	fun clearSharedPreferences() {
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit().clear().apply()
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnNaN() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", Float.NaN)
				.apply()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(Float.NaN, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetNaN() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Float.NaN
		// then:
		assertEquals(Float.NaN, preferences.getFloat("TEST_DELEGATE_KEY", 0f))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnPositiveInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", Float.POSITIVE_INFINITY)
				.apply()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(Float.POSITIVE_INFINITY, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPositiveInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Float.POSITIVE_INFINITY
		// then:
		assertEquals(Float.POSITIVE_INFINITY, preferences.getFloat("TEST_DELEGATE_KEY", 0f))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnNegativeInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", Float.NEGATIVE_INFINITY)
				.apply()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(Float.NEGATIVE_INFINITY, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetNegativeInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Float.NEGATIVE_INFINITY
		// then:
		assertEquals(Float.NEGATIVE_INFINITY, preferences.getFloat("TEST_DELEGATE_KEY", 0f))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnMinFloat() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", Float.MIN_VALUE)
				.apply()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(Float.MIN_VALUE, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetMinFloat() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Float.MIN_VALUE
		// then:
		assertEquals(Float.MIN_VALUE, preferences.getFloat("TEST_DELEGATE_KEY", 0f))
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnMaxFloat() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putFloat("TEST_DELEGATE_KEY", Float.MAX_VALUE)
				.apply()
		// when:
		val testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// then:
		assertEquals(Float.MAX_VALUE, testPreference)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetMaxFloat() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.floatSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Float.MAX_VALUE
		// then:
		assertEquals(Float.MAX_VALUE, preferences.getFloat("TEST_DELEGATE_KEY", 0f))
	}
}
