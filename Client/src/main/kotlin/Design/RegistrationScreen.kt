package Design

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.paint.Color
import tornadofx.*

class RegistrationScreen() : View() {
    private val inputUsername = SimpleStringProperty()
    private val inputPassword = SimpleStringProperty()
    private val readerOfCommands = MyApp.readerOfCommands
    private val answerText= SimpleStringProperty("")


    override val root = form {
        setPrefSize(400.0, 300.0)
        primaryStage.isResizable = false
        text("Registration") {
            style {
                textFill = Color.BLUE
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 30.px
            }
        }
        fieldset {
            alignment = Pos.TOP_CENTER
            field("Put your username") {
                textfield(inputUsername).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
            field("Put your password") {
                textfield(inputPassword).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
        }
        hbox(50, Pos.TOP_CENTER) {
            style {
            }
            button("Registrate") {
                action {
                    if (inputPassword.value.matches(Regex("[a-zA-Z0-9]{10,}")) && inputUsername.value.matches(Regex("[a-zA-Z0-9]{10,}"))){
                        val answer =
                            readerOfCommands.readCommand("registration ${inputUsername.value} ${inputPassword.value}").result
                        println(answer)
                        if (answer.split(" ").contains("+++")) {
                            replaceWith<TableScreen>(sizeToScene = true)
                        } else {
                            answerText.set(answer)
                        }
                        inputUsername.value = ""
                        inputPassword.value = ""
                    }
                    else{
                        scene.root.vbox {
                            style {
                                setAlignment(Pos.TOP_CENTER)
                                padding = box(30.px, 20.px)
                            }
                            answerText.set("Wrong password or login")
                        }
                    }
                }
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
            }
        }
        label(answerText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(30.px, 20.px)
        }
    }

}