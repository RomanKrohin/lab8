package WorkModuls

import Client.Client
import javafx.scene.Parent
import tornadofx.Stylesheet.Companion.label
import tornadofx.View
import tornadofx.hbox
import tornadofx.label
import java.security.MessageDigest
import kotlin.system.exitProcess
import tornadofx.*


/**
 * Класс для чтения, выборки и вывода результатов команд
 */
class ReaderOfCommands {

    val listOfCommands =
        mutableListOf("show", "info", "help", "history", "save", "clear", "exit", "auto-authentication", "registration")
    val client = Client()
    val tokens = CommandComponentsManager()

    /**
     * Класс для чтения, выборки и вывода результатов команд
     * @param collection
     * @param path
     */
    fun readCommand(GUIcommand: String): Answer {
        val commandComponents = tokens.returnCommandCommand(GUIcommand)
        val task =
            Task(commandComponents, listOfCommands = listOfCommands)
        specialActions(task)
        listOfCommands.addAll(client.returnNewCommands())
        client.clearNewCommands()
        return client.sendTask(task)
    }

    fun readCommand(task: Task): Answer {
        task.listOfCommands=listOfCommands
        specialActions(task)
        listOfCommands.addAll(client.returnNewCommands())
        client.clearNewCommands()
        return client.sendTask(task)
    }

    fun specialActions(task: Task) {
        if (task.describe[0] == "exit") {
            client.sendTask(Task(mutableListOf("log_out"), listOfCommands = listOfCommands))
            exitProcess(0)
        }
        if (task.describe[0] == "registration" || task.describe[0] == "auto-authentication") {
            task.describe[1] = "${task.describe[1]} ${sha384(task.describe[2])}"
            task.describe.removeAt(2)
        }
    }

    fun sha384(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-384")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

}