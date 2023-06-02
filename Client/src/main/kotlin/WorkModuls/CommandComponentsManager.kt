package WorkModuls

import java.util.stream.Collectors
import tornadofx.*


/**
 * Класс для токенизации команд
 */
class CommandComponentsManager {

    /**
     * Метод для токенизации команд
     * @param command
     * @param path
     * @param history
     * @return MutableList<String>
     */
    fun returnCommandCommand(command: String): MutableList<String> {
        val commandComponent1 = command.trim().split(" ").toMutableList()
        val commandComponent2=commandComponent1.stream().filter{it!=""}.collect(Collectors.toList())
        if (commandComponent2.size == 3 && commandComponent2[0] != "registration" && commandComponent2[0] != "auto-authentication") {
            commandComponent2[0] = commandComponent2[0] + " " + commandComponent2[1]
            commandComponent2[1] = commandComponent2[2]
            commandComponent2.removeAt(2)
        }
        if (commandComponent2.size == 4){
            commandComponent2[0] = commandComponent2[0] + " " + commandComponent2[1]
            commandComponent2[1] = commandComponent2[2]
            commandComponent2[2]=commandComponent2[3]
            commandComponent2.removeAt(3)
        }
        return commandComponent2

    }


}
