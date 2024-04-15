package com.example.commerceapp.data.remote.model

import com.example.commerceapp.domain.model.user.Addressee
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddresseeDto(
    val uid: String,
    val addressName: String = "",
    val receiverName: String,
    val zipCode: String,
    val baseAddress: String,
    val detailAddress: String,
    val roadNameYn: String,
    val tel: String
) {
    fun mapToEntity(): Addressee {
        return Addressee(
            uid,
            addressName,
            receiverName,
            zipCode,
            baseAddress,
            detailAddress,
            roadNameYn,
            tel
        )
    }
}
