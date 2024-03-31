package application.data.repository

import application.data.model.User

interface UserRepository {

    suspend fun addUser(user: User): Boolean
    suspend fun getUserByUsername(username: String): User?
}

