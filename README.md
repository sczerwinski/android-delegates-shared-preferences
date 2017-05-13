[![Build Status](https://travis-ci.org/sczerwinski/android-delegates-shared-preferences.svg?branch=master)](https://travis-ci.org/sczerwinski/android-delegates-shared-preferences)
[![Download](https://api.bintray.com/packages/sczerwinski/android/delegates-shared-preferences/images/download.svg)](https://bintray.com/sczerwinski/android/delegates-shared-preferences/_latestVersion)

# Kotlin Delegates for Android SharedPreferences

A set of Kotlin delegates whose values are stored in Android `SharedPreferences`.

## Gradle Build Configuration

To use the library, add another line to `build.gradle` in your Android project:
```gradle
dependencies {
    compile 'it.czerwinski.android:delegates-shared-preferences:0.1'
}
```

## Usage

This library uses the concept of
[Delegated Properties](https://kotlinlang.org/docs/reference/delegated-properties.html) in Kotlin.

```kotlin
class MainActivity : Activity() {

    // Delegate a property in your Activity to a preference with the desired name:
    val username by stringSharedPreference("DEFAULT_USERNAME")

    // If you need to change the value stored in the preference, simply use "var" instead of "val":
    var fontSize by intSharedPreference("SETTINGS_FONT_SIZE")

    // If you don't want nullable types, provide the default value:
    val parentalControl by booleanSharedPreference("SETTINGS_PARENTAL_CONTROL", defaultValue = true)

    // [...]
}
```
