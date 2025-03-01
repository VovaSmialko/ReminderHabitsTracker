package com.smialko.reminderhabitstracker.data.model.user

data class User(
    val firstName: String,
    val lastName: String,
    val userId: String,
    val email: String,
    val password: String
) {
    constructor() : this("", "", "", "", "")
}