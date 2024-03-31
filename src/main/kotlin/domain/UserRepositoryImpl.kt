package application.domain

import application.data.model.User
import application.data.repository.UserRepository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepositoryImpl(
    private val db: CoroutineDatabase
): UserRepository {

    val users = db.getCollection<User>()
    override suspend fun addUser(user: User): Boolean {
       return users.insertOne(user).wasAcknowledged()
    }

    override suspend fun getUserByUsername(username: String): User? {
        return users.findOne(User::username eq(username))
    }
}