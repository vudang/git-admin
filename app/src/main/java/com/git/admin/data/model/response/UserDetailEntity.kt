package com.git.admin.data.model.response

import com.google.gson.annotations.SerializedName
import com.git.admin.data.model.base.BaseEntity
import com.git.admin.domain.model.UserDetail

data class UserDetailEntity(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("country_code")
    val countryCode: Int? = null,
    @SerializedName("mobile_no")
    val mobileNo: String? = null,
    @SerializedName("profile_img_url")
    val profileImgUrl: String? = null,
    @SerializedName("address")
    val address: String? = null
): BaseEntity {
    override fun toModel(): UserDetail {
        return UserDetail(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            countryCode = countryCode,
            mobileNo = mobileNo,
            profileImgUrl = profileImgUrl,
            address = address
        )
    }
}