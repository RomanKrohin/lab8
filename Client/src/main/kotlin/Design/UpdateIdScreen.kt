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
            field(MyApp.bundle.getString("Enter_old_ID")) {
                textfield(inputOldID).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Enter_new_ID")) {
                textfield(inputNewID).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
        }
        hbox(50, Pos.BOTTOM_RIGHT){
            button(MyApp.bundle.getString("Execute")) {
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