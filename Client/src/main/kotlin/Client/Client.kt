package Client

import WorkModuls.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel
import tornadofx.*

class Client {

    val listOfNewCommands = mutableListOf<String>()
    var login = ""
    var token = ""

    fun connection(): SocketChannel {
        return try {
            val clientSocket = SocketChannel.open()
            clientSocket.socket().connect(InetSocketAddress("localhost", 8000))
            clientSocket
        } catch (e: RuntimeException) {
            println("Bad connection")
            SocketChannel.open(InetSocketAddress("localhost", 8000))
            throw e
        }
    }

    fun sendTask(task: Task): Answer {
        try {
            val clientSocket = connection()
            if (clientSocket.isConnected) {
                val objectOutputStream = ObjectOutputStream(clientSocket.socket().getOutputStream())
                if (task.describe[0] == "registration" || task.describe[0] == "auto-authentication") putLoginAndPassword(
                    task
                )
                if (task.describe[0] == "log_out") {
                    task.describe.add(token)
                }
                task.login = login
                task.token = token
                objectOutputStream.writeObject(task)
                return getAnswer(clientSocket)
            }
            else{
                throw Exception()
            }
        } catch (e: Exception) {
            val answer= Answer()
            answer.result="Bad output"
            return (answer)
        }
    }

    fun getAnswer(clientSocket: SocketChannel): Answer {
        val objectInputStream = ObjectInputStream(clientSocket.socket().getInputStream())
        val answer = objectInputStream.readObject() as Answer
        listOfNewCommands.addAll(answer.listOfNewCommand)
        if (answer.result.split(" ").contains("token")) {
            token = answer.result.split(" ").last()
        }
        return answer
        clientSocket.close()
    }

    fun returnNewCommands(): MutableList<String> {
        return listOfNewCommands
    }

    fun clearNewCommands() {
        listOfNewCommands.clear()
    }

    fun putLoginAndPassword(task: Task) {
        val components = task.describe[1].trim().split(" ")
        login = components[0]
        token = components[1]
    }
}