package Design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class UpdateIdScreen : View() {
    private val inputOldID = SimpleStringProperty()
    private val inputNewID = SimpleStringProperty()

    override val root= form{
        fieldset {
            alignment = Pos.TOP_CENTER
            field("Enter old ID") {
                textfield(inputOldID).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
            field("Enter new ID") {
                textfield(inputNewID).useMaxWidth
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
                    val answer=MyApp.readerOfCommands.readCommand("update id ${inputOldID.value} ${inputNewID.value}")
                    inputOldID.value=""
                    inputNewID.value=""
                }
            }
        }
    }
}