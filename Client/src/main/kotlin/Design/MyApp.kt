package Design

import Client.Client
import LoginScreen
import WorkModuls.ReaderOfCommands
import javafx.scene.text.FontWeight
import tornadofx.*

class MyApp : App(LoginScreen::class) {
    companion object{
        val readerOfCommands= ReaderOfCommands()
    }
}