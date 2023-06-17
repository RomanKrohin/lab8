import Design.MyApp
import Design.RegistrationScreen
import Design.TableScreen
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import tornadofx.*
import java.util.*

class LoginScreen : View() {
    private val inputUsername = SimpleStringProperty()
    private val inputPassword = SimpleStringProperty()
    private val auth = SimpleStringProperty(MyApp.bundle.getString("auth"))
    private val login = SimpleStringProperty(MyApp.bundle.getString("login"))
    private val pass = SimpleStringProperty(MyApp.bundle.getString("pass"))
    private val reg = SimpleStringProperty(MyApp.bundle.getString("reg"))
    private val au = SimpleStringProperty(MyApp.bundle.getString("au"))
    private val readerOfCommands = MyApp.readerOfCommands
    private val answerText = SimpleStringProperty("")

    override val root = form {
        setPrefSize(400.0, 300.0)
        primaryStage.isResizable = false
        label(auth) {
            style {
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 30.px
                padding = box(10.px, 20.px)
            }
        }
        fieldset() {
            alignment = Pos.TOP_CENTER
            label(login).style {
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 20.px
                padding = box(10.px, 20.px)
            }
            field() {
                textfield(inputUsername).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
            label(pass).style {
                setAlignment(Pos.TOP_CENTER)
                fontFamily = "Small capital"
                fontSize = 20.px
                padding = box(10.px, 20.px)
            }
            field() {
                textfield(inputPassword).useMaxWidth
                style {
                    setMaxWidth(280.0)
                    fontFamily = "Small capital"
                }
            }
        }
        hbox(50, Pos.TOP_CENTER) {
            val LButton = button() {
                label(au).style {
                    setAlignment(Pos.CENTER)
                    setTextAlignment(TextAlignment.CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK

                }
                action {
                    MyApp.login.set(inputUsername.value)
                    val answer =
                        readerOfCommands.readCommand("auto-authentication ${inputUsername.value} ${inputPassword.value}").result
                    if (answer.split(" ").contains("+++")) {
                        MyApp.data.setLogin(inputUsername.value)
                        replaceWith<TableScreen>(sizeToScene = true)
                    } else {
                        answerText.set(MyApp.bundle.getString(answer))
                    }
                    inputUsername.value = ""
                    inputPassword.value = ""
                }
                style {
                    setTextAlignment(TextAlignment.CENTER)
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                minHeight = 50.0
            }
            val RButton = button() {
                label(reg).style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                }
                style {
                    setTextAlignment(TextAlignment.CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                minHeight = 50.0
                action {
                    replaceWith<RegistrationScreen>(sizeToScene = true)
                }
            }
        }
        label(answerText).style {
            setAlignment(Pos.TOP_CENTER)
            padding = box(30.px, 20.px)
        }
        hbox(30, Pos.BOTTOM_CENTER) {
            val toggle = togglegroup {
                togglebutton("ru") {
                    action {
                        MyApp.setBundle = ResourceBundle.getBundle("messages", Locale("ru"))
                        auth.set(MyApp.bundle.getString("auth"))
                        login.set(MyApp.bundle.getString("login"))
                        pass.set(MyApp.bundle.getString("pass"))
                        reg.set(MyApp.bundle.getString("reg"))
                        au.set(MyApp.bundle.getString("au"))
                    }
                }
                togglebutton("en") {
                    action {
                        MyApp.setBundle = ResourceBundle.getBundle("messages", Locale("en"))
                        auth.set(MyApp.bundle.getString("auth"))
                        login.set(MyApp.bundle.getString("login"))
                        pass.set(MyApp.bundle.getString("pass"))
                        reg.set(MyApp.bundle.getString("reg"))
                        au.set(MyApp.bundle.getString("au"))
                    }
                }
            }
        }
        onRefresh()
    }

}