package com.git.admin

import com.git.admin.util.isValidEmail
import com.git.admin.util.isValidPassword
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // Write a test for the fun String.validateEmail() function
    @Test
    fun testEmailValidation() {
        val email = "ngocvu.cse09@gmail.com"
        assertTrue(email.isValidEmail())
    }

    // write a test for the fun String.isValidPassword() function with a valid password
    @Test
    fun testPasswordValidation() {
        val password = "Tony@1234"
        assertTrue(password.isValidPassword())
    }

    // Write a test for the fun String.isValidPassword() function with cover all cases
    //
    /**
     * This function checks if the password is valid or not.
     * A valid password must have:
     * 1. At least 8 characters
     * 2. At least 1 digit
     * 3. At least 1 lowercase character
     * 4. At least 1 uppercase character
     * 5. At least 1 special character
     * 6. No whitespace
     * @return true if the password is valid, false otherwise
     */
    @Test
    fun testPasswordValidationAllCases() {
        val password = "Tony@1234"
        assertTrue(password.isValidPassword())
    }
}