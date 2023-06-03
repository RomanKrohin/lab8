package Design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class CountLessThenAdmin : View() {
    private val answerText= SimpleStringProperty("")
    private val input = SimpleStringProperty()


    override val root= form{
        fieldset {
            alignment = Pos.TOP_CENTER
            field(MyApp.bundle.getString("Enter_name")) {
                textfield(input).useMaxWidth
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
                    val answer= MyApp.readerOfCommands.readCommand("count_less_than_group_admin -${input.value}")
                    answerText.set(answer.result)
                    input.value=""
                }
            }
        }
        label(answerText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(30.px, 20.px)
        }
    }
}