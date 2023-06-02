package WorkModuls

import StudyGroupInformation.StudyGroup
import java.io.Serializable
import tornadofx.*


data class Task(
    val describe: MutableList<String>,
    var studyGroup: StudyGroup? = null,
    var listOfCommands: MutableList<String>? = null,
    var login: String = "",
    var token: String = "",
) : Serializable {
}