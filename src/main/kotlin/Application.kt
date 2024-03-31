package application


import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

import plugins.configureMonitoring
import plugins.configureRouting
import plugins.configureSecurity
import plugins.configureSerialization


fun main(args: Array<String>) {

    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)

}

fun Application.module() {
    val dbName = "auth"
    val pw = System.getenv("pw")


    val db = KMongo.createClient(
        connectionString = "mongodb+srv://anastasizzzs10:$pw@cluster0.zi3srwn.mongodb.net/$dbName?retryWrites=true&w=majority&appName=Cluster0"
    ).coroutine.getDatabase(dbName)



    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
