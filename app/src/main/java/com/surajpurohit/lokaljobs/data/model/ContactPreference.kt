package com.surajpurohit.lokaljobs.data.model

data class ContactPreference(
    val preference: Int,
    val whatsappLink: String,
    val preferredCallStartTime: String,
    val preferredCallEndTime: String
)