package Design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*

class HelpScreen: View() {
    private val answerText= SimpleStringProperty("")

    override val root= vbox {
        setPrefSize(950.0, 300.0)
        answerText.set(MyApp.readerOfCommands.readCommand("help").result)
        label(answerText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(30.px, 20.px)
        }
    }
}