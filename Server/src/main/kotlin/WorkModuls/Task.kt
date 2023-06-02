package WorkModuls

import StudyGroupInformation.StudyGroup
import java.io.Serializable

data class Task(
    val describe: MutableList<String>,
    var studyGroup: StudyGroup? = null,
    var listOfCommands: MutableList<String>,
    var login: String = "",
    var token: String = "",
) : Serializable {
}