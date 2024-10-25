package com.git.admin.domain.model

data class UserDetail(
    val id: Int? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val address: String? = null,
    val countryCode: Int? = null,
    val mobileNo: String? = null,
    val profileImgUrl: String? = null,
): BaseModel {
    fun getFullName(): String {
        val name = listOfNotNull(firstName, lastName).joinToString(" ")
        return name
    }
}