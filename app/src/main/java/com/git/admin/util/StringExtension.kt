package com.git.admin.util

fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

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
fun String.isValidPassword(): Boolean {
    val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$".toRegex()
    return passwordRegex.matches(this)
}

fun String.getRulePassword(): String {
    val rule = mutableListOf<String>()
    if (this.length < 8) {
        rule.add("Password must be at least 8 characters")
    }
    if (!this.contains(Regex("[0-9]"))) {
        rule.add("Password must contain at least 1 digit")
    }
    if (!this.contains(Regex("[a-z]"))) {
        rule.add("Password must contain at least 1 lowercase character")
    }
    if (!this.contains(Regex("[A-Z]"))) {
        rule.add("Password must contain at least 1 uppercase character")
    }
    if (!this.contains(Regex("[@#$%^&+=!]"))) {
        rule.add("Password must contain at least 1 special character")
    }
    if (this.contains(Regex("\\s"))) {
        rule.add("Password must not contain whitespace")
    }
    return rule.joinToString("\n")
}

fun String.isValidPhoneNumber(): Boolean {
    val phoneRegex = "^[0-9]{10}$".toRegex()
    return phoneRegex.matches(this)
}

fun String.toLongOrNull(): Long? {
    return try {
        this.toLong()
    } catch (e: NumberFormatException) {
        null
    }
}

// Write fun detect the file name from this format "offers/buyers/1721487728215-6WO0GDCZVh-contract-template-01.pdf"
fun String.detectFileName(): String {
    val fileName = this.substringAfterLast("/")
    val components = fileName.split("-").takeLast(3)
    val name = components.joinToString("-")
    return name
}