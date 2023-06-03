package Design

import StudyGroupInformation.*
import WorkModuls.CheckModule
import WorkModuls.Task
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class InsertScreen : View() {
    private val inputName = SimpleStringProperty()
    private val inputCX = SimpleStringProperty()
    private val inputCY = SimpleStringProperty()
    private val inputSC = SimpleStringProperty()
    private val inputSBE = SimpleStringProperty()
    private val inputAM = SimpleStringProperty()
    private val inputFOE = SimpleStringProperty()
    private val inputAName = SimpleStringProperty()
    private val inputAdminW = SimpleStringProperty()
    private val inputAdminColor = SimpleStringProperty()
    private val inputAdminCountry = SimpleStringProperty()
    private val answerText = SimpleStringProperty()


    override val root = form {
        setPrefSize(1000.0, 450.0)
        fieldset {
            field(MyApp.bundle.getString("Name")) {
                textfield(inputName).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Coordinate_X")) {
                textfield(inputCX).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Coordinate_Y")) {
                textfield(inputCY).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Students_count")) {
                textfield(inputSC).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Should_be_expelled")) {
                textfield(inputSBE).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Average_mark")) {
                textfield(inputAM).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field("${MyApp.bundle.getString("Form_of_education")}: \n[DISTANCE_EDUCATION,\nFULL_TIME_EDUCATION,\nEVENING_CLASSES] or null") {
                textfield(inputFOE).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Admin_name")) {
                textfield(inputAName).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field(MyApp.bundle.getString("Admin_weight")) {
                textfield(inputAdminW).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field("${MyApp.bundle.getString("Admin_color")}: ${StudyGroupInformation.Color.values().map { it.toString() }}") {
                textfield(inputAdminColor).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
            field("${MyApp.bundle.getString("Admin_country")}: ${Country.values().map { it.toString() }}") {
                textfield(inputAdminCountry).useMaxWidth
                style {
                    fontFamily = "Small capital"
                }
            }
        }.style {
            setMaxSize(750.0, 900.0)
            alignment = Pos.TOP_CENTER
        }
        hbox(50, Pos.BOTTOM_RIGHT) {
            button("Execute") {
                style {
                    setAlignment(Pos.TOP_CENTER)
                    textFill = Color.WHITE
                    backgroundColor += Color.BLACK
                    padding = box(10.px, 20.px)
                }
                action {
                    try {
                        val studyGroup = StudyGroup(
                            0,
                            name = inputName.value,
                            Coordinates(inputCX.value.toLong(), inputCY.value.toLong()),
                            inputSC.value.toLong(),
                            inputSBE.value.toInt(),
                            inputAM.value.toInt(),
                            FormOfEducation.valueOf(inputFOE.value),
                            Person(
                                inputAName.value,
                                inputAdminW.value.toInt(),
                                StudyGroupInformation.Color.valueOf(inputAdminColor.value),
                                Country.valueOf(inputAdminCountry.value)
                            )
                        )
                        studyGroup.setOwner(MyApp.data.getLogin())
                        if (CheckModule().check(studyGroup)) {
                            MyApp.readerOfCommands.readCommand(
                                Task(
                                    mutableListOf(
                                        "insert",
                                        "-${studyGroup.getName()}"
                                    ), studyGroup
                                )
                            )
                        }
                        else{
                            throw Exception()
                        }
                    } catch (e: Exception) {
                        answerText.set("Something wrong")
                    }
                }
            }
            label(answerText).style {
                setAlignment(Pos.TOP_CENTER)
                padding = box(30.px, 20.px)
            }
        }
    }

}