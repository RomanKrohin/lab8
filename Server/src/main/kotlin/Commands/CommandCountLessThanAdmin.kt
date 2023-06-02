package Commands

import Collections.Collection
import StudyGroupInformation.StudyGroup
import WorkModuls.Answer
import WorkModuls.Task
import com.charleskorn.kaml.Yaml
import kotlinx.serialization.encodeToString
import java.lang.RuntimeException
import java.util.*
import java.util.stream.Collectors

/**
 * Класс команды, которая выводит объекты значение поля group admin меньше чем у заданного
 */
class CommandCountLessThanAdmin(workCollection: Collection<String>) : Command() {
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
        var count=0
        return try {
            collection.collection.values.stream().collect(Collectors.toList())
                .filter { it -> it.getAdmin().hashCode() < collection.collection.get(key).hashCode() }
                .forEach { count++ }
            answer.result=count.toString()
            answer
        } catch (e: RuntimeException) {
            answer.result = "Command exception 14"
            answer
        }
    }

}