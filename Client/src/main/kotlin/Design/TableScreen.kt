package Design

import LoginScreen
import StudyGroupInformation.Coordinates
import StudyGroupInformation.StudyGroup
import javafx.beans.property.SimpleListProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.paint.Color
import tornadofx.*
import tornadofx.Stylesheet.Companion.cell
import tornadofx.Stylesheet.Companion.fitToHeight
import tornadofx.Stylesheet.Companion.fitToWidth
import javax.swing.text.StyleConstants.setAlignment

class TableScreen() : View() {
    private val tableData: ObservableList<StudyGroup> = mutableListOf<StudyGroup>().asObservable()

    override val root = borderpane() {
        primaryStage.isResizable = false
        right {
            scrollpane {
                style {
                    fitToHeight = true
                    fitToWidth = true
                }
                tableview(tableData) {
                    setPrefSize(1500.0, 900.0)
                    style {
                    }
                    isEditable = false
                    column("ID", StudyGroup::getId)
                    column("Name", StudyGroup::getName)
                    column("X", StudyGroup::getCoordinates).cellFormat {
                        text = it.getX().toString()
                    }
                    column("Y", StudyGroup::getCoordinates).cellFormat {
                        text = it.getY().toString()
                    }
                    column("Student_count", StudyGroup::getStudentcount)
                    column("Should_be_expelled", StudyGroup::getShouldBeExpelled)
                    column("Average_mark", StudyGroup::getAverageMark)
                    column("Form_of_education", StudyGroup::getFormOfEducation)
                    column("Admin_name", StudyGroup::getAdmin).cellFormat {
                        text = it.getName()
                    }
                    column("Admin_weight", StudyGroup::getAdmin).cellFormat {
                        text = it.getWeight().toString()
                    }
                    column("Admin_color", StudyGroup::getAdmin).cellFormat {
                        text = it.getColor().toString()
                    }
                    column("Admin_country", StudyGroup::getAdmin).cellFormat {
                        text = it.getCountry().toString()
                    }
                    column("Owner", StudyGroup::getOwner)
                }

            }

        }
        left {
            maxWidth = 240.0
            vbox {
                style {
                    setAlignment(Pos.TOP_LEFT)
                    padding = box(30.px, 20.px)
                }
                button("Show") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        tableData.asObservable().removeAll()
                        val answer= MyApp.readerOfCommands.readCommand("show")
                        tableData.setAll(answer.collectionForTable)
                    }
                }
                spacing = 10.0
                button("Info") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        openInternalWindow(InfoScreen::class)
                    }
                }
                button("Help") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        openInternalWindow(HelpScreen::class)
                    }
                }
                button("Clear") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        MyApp.readerOfCommands.readCommand("clear")
                    }
                }
                button("Save") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        MyApp.readerOfCommands.readCommand("save")
                    }
                }
                button("Remove\ngreater key") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        openInternalWindow(RemoveByGreaterKeyScreen::class)
                    }
                }
                button("Remove\nlower key") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        openInternalWindow(RemoveByLowerKeyScreen::class)
                    }
                }
                button("Count less\nthan group\nadmin") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 70.0
                    action {
                        openInternalWindow(CountLessThenAdmin::class)
                    }
                }
                button("Remove") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        openInternalWindow(RemoveScreen::class)
                    }
                }
                button("Update id") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        openInternalWindow(UpdateIdScreen::class)
                    }
                }
                button("Insert") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        openInternalWindow(InsertScreen::class)
                    }
                }
                button("Log out") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        MyApp.readerOfCommands.readCommand("log_out")
                        replaceWith<LoginScreen>(sizeToScene = true)
                    }
                }
                button("Exit") {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += Color.BLACK
                        padding = box(10.px, 5.px)
                    }
                    minWidth = 100.0
                    minHeight = 50.0
                    action {
                        MyApp.readerOfCommands.readCommand("exit")
                    }
                }
            }
        }
    }

}