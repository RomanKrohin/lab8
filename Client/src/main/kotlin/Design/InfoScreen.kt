package Design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.Parent
import tornadofx.*

class InfoScreen: View() {
    private val answerText= SimpleStringProperty("")

    override val root= vbox {
        setPrefSize(600.0, 300.0)
        answerText.set(MyApp.readerOfCommands.readCommand("info").result)
        label(answerText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(30.px, 20.px)
        }
    }
}