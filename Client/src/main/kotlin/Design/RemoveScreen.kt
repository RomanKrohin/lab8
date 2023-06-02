package Design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class RemoveScreen : View() {
    private val input = SimpleStringProperty()

    override val root= form{
        fieldset {
            alignment = Pos.TOP_CENTER
            field("Enter name") {
                textfield(input).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
        }
        hbox(50, Pos.BOTTOM_RIGHT){
            button("Execute") {
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    MyApp.readerOfCommands.readCommand("remove -${input.value}")
                    input.value=""
                }
            }
        }
    }
}