package WorkModuls

import StudyGroupInformation.StudyGroup
import java.io.Serializable

/**
 * Класс ответа, в его экземплярах хранятся результаты выполнения команд
 * @param checkError
 * @param nameError
 * @param result
 */
data class Answer(
    var result: String = "Success\n----------\n",
) : Serializable {

    val listOfNewCommand = mutableListOf<String>()
    var collectionForTable= mutableListOf<StudyGroup>()

    fun setNewCommands(list: List<String>) {
        listOfNewCommand.addAll(list)
    }
}