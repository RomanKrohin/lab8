package Commands

import Collections.Collection
import StudyGroupInformation.StudyGroup
import WorkModuls.Answer
import WorkModuls.Task
import com.charleskorn.kaml.Yaml
import kotlinx.serialization.encodeToString
import java.util.stream.Collectors

/**
 * Класс команды, которая выводит объекты, сохраненные в коллекции, в текстовом формате
 */

class CommandShow(workCollection: Collection<String>) : Command() {
    var collection: Collection<String>

    init {
        collection = workCollection
    }

    /**
     *  Метод работы команды
     *  @param collection
     *  @param key
     */
    override fun commandDo(key: String, task: Task): Answer {
        val answer = Answer()
        return try {
            val listOfStudyGroup = collection.collection.values
            listOfStudyGroup.stream().sorted(Comparator.comparing(StudyGroup::getName)).collect(Collectors.toList())
                .forEach {
                    answer.result += "\n----------\n" + Yaml.default.encodeToString(
                        it
                    )
                }
            answer
        } catch (e: Exception) {
            answer.result = "Command exception 1"
            answer
        }
    }

}