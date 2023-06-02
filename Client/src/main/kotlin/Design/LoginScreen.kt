import Design.MyApp
import Design.RegistrationScreen
import Design.TableScreen
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class LoginScreen : View() {
    private val inputUsername = SimpleStringProperty()
    private val inputPassword = SimpleStringProperty()
    private val readerOfCommands = MyApp.readerOfCommands
    private val answerText= SimpleStringProperty("")


    override val root = form {
        setPrefSize(400.0, 300.0)
        primaryStage.isResizable = false
        text("Authorization") {
            style {
                textFill = Color.BLUE
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 30.px
            }
        }
        fieldset {
            alignment = Pos.TOP_CENTER
            field("Username") {
                textfield(inputUsername).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
            field("Password") {
                textfield(inputPassword).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
        }
        hbox(50, Pos.TOP_CENTER) {
            button("Authorize") {
                action {
                    val answer =
                        readerOfCommands.readCommand("auto-authentication ${inputUsername.value} ${inputPassword.value}").result
                    if (answer.split(" ").contains("+++")) {
                        replaceWith<TableScreen>(sizeToScene = true)
                    }
                    else{
                        answerText.set(answer)
                    }
                    inputUsername.value = ""
                    inputPassword.value = ""
                }
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
            }
            button("Registrate") {
                style {
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    replaceWith<RegistrationScreen>(sizeToScene = true)
                }
            }
        }
        label(answerText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(30.px, 20.px)
        }
    }
}