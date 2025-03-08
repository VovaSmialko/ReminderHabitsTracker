package com.smialko.reminderhabitstracker.data.model.user

data class User(
    val fullName: String,
    val userId: String,
    val email: String,
    val password: String
) {
    constructor() : this("", "", "", "")
}