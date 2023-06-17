package Design

import Client.Client
import Client.Data
import LoginScreen
import WorkModuls.ReaderOfCommands
import javafx.beans.property.SimpleStringProperty
import javafx.scene.text.FontWeight
import tornadofx.*
import java.util.*

class MyApp : App(LoginScreen::class) {
    companion object{
        val readerOfCommands= ReaderOfCommands()
        val data= Data()
        var bundle= ResourceBundle.getBundle("messages", Locale("ru"))
        var setBundle: ResourceBundle
            get() = bundle
            set(value) {
                bundle = value
            }
        var login=SimpleStringProperty()
    }



}