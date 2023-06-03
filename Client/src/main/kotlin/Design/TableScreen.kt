package Design

import LoginScreen
import StudyGroupInformation.Country
import StudyGroupInformation.StudyGroup
import com.sun.javafx.binding.BidirectionalBinding.bind
import javafx.animation.AnimationTimer
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import tornadofx.*
import tornadofx.Stylesheet.Companion.arrow
import tornadofx.Stylesheet.Companion.imageView
import tornadofx.Stylesheet.Companion.tab
import java.awt.Point

class TableScreen() : View() {
    private val tableData: ObservableList<StudyGroup> = mutableListOf<StudyGroup>().asObservable()
    private val login = SimpleStringProperty()
    private val spainC = SimpleStringProperty("")
    private val spainCW = SimpleStringProperty("")
    private val germanC = SimpleStringProperty("")
    private val indiaC = SimpleStringProperty("")

    override val root = tabpane {
        tab("Таблица") {
            borderpane() {
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
                            column(MyApp.bundle.getString("ID"), StudyGroup::getId)
                            column(MyApp.bundle.getString("Name"), StudyGroup::getName)
                            column("X", StudyGroup::getCoordinates).cellFormat {
                                text = it.getX().toString()
                            }
                            column("Y", StudyGroup::getCoordinates).cellFormat {
                                text = it.getY().toString()
                            }
                            column(MyApp.bundle.getString("Student_count"), StudyGroup::getStudentcount)
                            column(MyApp.bundle.getString("Should_be_expelled"), StudyGroup::getShouldBeExpelled)
                            column(MyApp.bundle.getString("Average_mark"), StudyGroup::getAverageMark)
                            column(MyApp.bundle.getString("Form_of_education"), StudyGroup::getFormOfEducation)
                            column(MyApp.bundle.getString("Admin_name"), StudyGroup::getAdmin).cellFormat {
                                text = it.getName()
                            }
                            column(MyApp.bundle.getString("Admin_weight"), StudyGroup::getAdmin).cellFormat {
                                text = it.getWeight().toString()
                            }
                            column(MyApp.bundle.getString("Admin_color"), StudyGroup::getAdmin).cellFormat {
                                text = it.getColor().toString()
                            }
                            column(MyApp.bundle.getString("Admin_country"), StudyGroup::getAdmin).cellFormat {
                                text = it.getCountry().toString()
                            }
                            column(MyApp.bundle.getString("Owner"), StudyGroup::getOwner)
                        }

                    }

                }
                left {
                    maxWidth = 240.0
                    vbox {
                        login.set(MyApp.login)
                        label(login).style {
                            setAlignment(Pos.TOP_CENTER)
                            fontFamily = "Small capital"
                            fontSize = 20.px
                            padding = box(10.px, 20.px)
                        }
                        style {
                            setAlignment(Pos.TOP_LEFT)
                            padding = box(30.px, 20.px)
                            setAlignment(Pos.TOP_CENTER)
                        }
                        button(MyApp.bundle.getString("Show")) {
                            style {
                                textFill = Color.WHITE
                                backgroundColor += Color.BLACK
                            }
                            minWidth = 100.0
                            minHeight = 50.0
                            action {
                                tableData.asObservable().removeAll()
                                val answer = MyApp.readerOfCommands.readCommand("show")
                                answer.collectionForTable.forEach {
                                    if (it.getAdmin().getCountry() == Country.GERMANY) {
                                        germanC.set(germanC.value + "\n" + it.getName())
                                    }
                                    if (it.getAdmin().getCountry() == Country.SPAIN) {
                                        spainC.set(spainC.value + "\n" + it.getName())
                                    }
                                    if (it.getAdmin().getCountry() == Country.INDIA) {
                                        indiaC.set(indiaC.value + "\n" + it.getName())
                                    }
                                }
                                tableData.setAll(answer.collectionForTable)
                            }
                        }
                        spacing = 10.0
                        button(MyApp.bundle.getString("Info")) {
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
                        button(MyApp.bundle.getString("Help")) {
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
                        button(MyApp.bundle.getString("Clear")) {
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
                        button(MyApp.bundle.getString("Save")) {
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
                        button(MyApp.bundle.getString("Remove\ngreater_key")) {
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
                        button(MyApp.bundle.getString("Remove\nlower_key")) {
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
                        button(MyApp.bundle.getString("Count_less\nthan_group\nadmin")) {
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
                        button(MyApp.bundle.getString("Remove")) {
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
                        button(MyApp.bundle.getString("Update_id")) {
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
                        button(MyApp.bundle.getString("Insert")) {
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
                        button(MyApp.bundle.getString("Log_out")) {
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
                        button(MyApp.bundle.getString("Exit")) {
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
                onRefresh()
            }
        }
        tab("Карта") {
            pane {
                val imageView = imageview("/map1.png") {
                    isPreserveRatio = true
                }
                imageView.fitHeight = 900.0
                imageView.fitWidth = 1800.0
                val circle1 = circle(radius = 10.0) {
                    layoutX = 790.0
                    layoutY = 335.0
                    tooltip() {
                        textProperty().bind(spainC)
                    }
                    visibleProperty().bind(spainC.isNotEmpty)
                }
                val arrow1 = polygon(0.0, 0.0, 20.0, 10.0, 0.0, 20.0) {
                    layoutX = circle1.layoutX + 5
                    layoutY = circle1.layoutY - 37
                    isVisible = false
                }
                circle1.setOnMouseEntered {
                    arrow1.isVisible = true
                }

                circle1.setOnMouseExited {
                    arrow1.isVisible = false
                }
                val circle2 = circle(radius = 10.0) {
                    layoutX = 835.0
                    layoutY = 287.0
                    tooltip() {
                        textProperty().bind(germanC)
                    }
                    visibleProperty().bind(germanC.isNotEmpty)
                }
                val arrow2 = polygon(0.0, 0.0, 20.0, 10.0, 0.0, 20.0) {
                    layoutX = circle2.layoutX + 5
                    layoutY = circle2.layoutY - 37
                    isVisible = false
                }
                circle2.setOnMouseEntered {
                    arrow2.isVisible = true
                }

                circle2.setOnMouseExited {
                    arrow2.isVisible = false
                }
                val circle3 = circle(radius = 10.0) {
                    layoutX = 1200.0
                    layoutY = 400.0
                    tooltip {
                        textProperty().bind(indiaC)
                    }
                    visibleProperty().bind(indiaC.isNotEmpty)
                }
                val arrow3 = polygon(0.0, 0.0, 20.0, 10.0, 0.0, 20.0) {
                    layoutX = circle3.layoutX + 5
                    layoutY = circle3.layoutY - 37
                    isVisible = false
                }
                circle3.setOnMouseEntered {
                    arrow3.isVisible = true
                }

                circle3.setOnMouseExited {
                    arrow3.isVisible = false
                }
            }
        }
    }
}