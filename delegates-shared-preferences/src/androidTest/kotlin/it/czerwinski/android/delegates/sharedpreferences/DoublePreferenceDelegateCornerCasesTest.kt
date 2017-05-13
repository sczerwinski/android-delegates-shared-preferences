package it.czerwinski.android.delegates.sharedpreferences

import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DoublePreferenceDelegateCornerCasesTest {

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
				.putDouble("TEST_DELEGATE_KEY", Double.NaN)
				.apply()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// then:
		assertEquals(Double.NaN, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetNaN() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// when:
		testPreference = Double.NaN
		// then:
		assertEquals(Double.NaN, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnPositiveInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putDouble("TEST_DELEGATE_KEY", Double.POSITIVE_INFINITY)
				.apply()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// then:
		assertEquals(Double.POSITIVE_INFINITY, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetPositiveInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Double.POSITIVE_INFINITY
		// then:
		assertEquals(Double.POSITIVE_INFINITY, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnNegativeInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putDouble("TEST_DELEGATE_KEY", Double.NEGATIVE_INFINITY)
				.apply()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// then:
		assertEquals(Double.NEGATIVE_INFINITY, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetNegativeInfinity() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Double.NEGATIVE_INFINITY
		// then:
		assertEquals(Double.NEGATIVE_INFINITY, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnMinDouble() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putDouble("TEST_DELEGATE_KEY", Double.MIN_VALUE)
				.apply()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// then:
		assertEquals(Double.MIN_VALUE, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetMinDouble() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Double.MIN_VALUE
		// then:
		assertEquals(Double.MIN_VALUE, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldReturnMaxDouble() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		preferences.edit()
				.putDouble("TEST_DELEGATE_KEY", Double.MAX_VALUE)
				.apply()
		// when:
		val testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY", 0.0)
		// then:
		assertEquals(Double.MAX_VALUE, testPreference, 0.0)
	}

	@Test
	@Throws(Exception::class)
	fun delegateShouldSetMaxDouble() {
		// given:
		val context = InstrumentationRegistry.getTargetContext()
		val preferences = PreferenceManager.getDefaultSharedPreferences(context)
		var testPreference by context.doubleSharedPreference("TEST_DELEGATE_KEY")
		// when:
		testPreference = Double.MAX_VALUE
		// then:
		assertEquals(Double.MAX_VALUE, preferences.getDouble("TEST_DELEGATE_KEY", 0.0), 0.0)
	}
}
