import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import kotlin.random.Random


open class Field {
    var value: Char by mutableStateOf(' ')
    var clicked: Boolean by mutableStateOf(false)

}


fun main() {
    document.body?.style?.backgroundColor = "grey"

    val list: MutableList<MutableList<Field?>> = mutableStateListOf(
        mutableStateListOf(null, null, null),
        mutableStateListOf(null, null, null),
        mutableStateListOf(null, null, null)
    )

    for (row in 0..2) {
        for (col in 0..2) {
            //var field : Field()
            list[row][col] = Field()
        }
    }

    val listRow1: MutableList<Field?> = mutableStateListOf(list[0][0], list[0][1], list[0][2])
    val listRow2: MutableList<Field?> = mutableStateListOf(list[0][0], list[0][1], list[0][2])
    val listRow3: MutableList<Field?> = mutableStateListOf(list[2][0], list[2][1], list[2][2])
    val listCol1: MutableList<Field?> = mutableStateListOf(list[0][0], list[1][0], list[2][0])
    val listCol2: MutableList<Field?> = mutableStateListOf(list[0][1], list[1][1], list[2][1])
    val listCol3: MutableList<Field?> = mutableStateListOf(list[0][2], list[1][2], list[2][2])
    val listDiag1: MutableList<Field?> = mutableStateListOf(list[0][0], list[1][1], list[2][2])
    val listDiag2: MutableList<Field?> = mutableStateListOf(list[2][0], list[1][1], list[0][2])

//true = multi ; false = single
    var singleOrMulti: Boolean by mutableStateOf(false)
    //false = O ; true = X
    var XorO: Boolean by mutableStateOf(false)

    var choiceXY: Boolean by mutableStateOf(false)
    fun setValue(rowIndex: Int, colIndex: Int) {
        if (XorO) {
            list[rowIndex][colIndex]!!.value = 'X'
            XorO = false
        } else {
            list[rowIndex][colIndex]!!.value = 'O'
            XorO = true

        }
    }

    fun setValue2(rowIndex: Int, colIndex: Int) {
        if (XorO) {
            list[rowIndex][colIndex]!!.value = 'X'

        } else {
            list[rowIndex][colIndex]!!.value = 'O'


        }
    }

    fun checkEnd() {
        if (listCol1[0]!!.value == listCol1[1]!!.value && listCol1[1]!!.value == listCol1[2]!!.value) {
            if (listCol1[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listCol1[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }

        } else if (listCol2[0]!!.value == listCol2[1]!!.value && listCol2[1]!!.value == listCol2[2]!!.value) {
            if (listCol2[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listCol2[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }

        } else if (listCol3[0]!!.value == listCol3[1]!!.value && listCol3[1]!!.value == listCol3[2]!!.value) {
            if (listCol3[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listCol3[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }

        } else if (listRow1[0]!!.value == listRow1[1]!!.value && listRow1[1]!!.value == listRow1[2]!!.value) {
            if (listRow1[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listRow1[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }

        } else if (listRow2[0]!!.value == listRow2[1]!!.value && listRow2[1]!!.value == listRow2[2]!!.value) {
            if (listRow2[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listRow2[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }

        } else if (listRow3[0]!!.value == listRow3[1]!!.value && listRow3[1]!!.value == listRow3[2]!!.value) {
            if (listRow3[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listRow3[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }

        } else if (listDiag1[0]!!.value == listDiag1[1]!!.value && listDiag1[1]!!.value == listDiag1[2]!!.value) {
            if (listDiag1[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listDiag1[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }

        } else if (listDiag2[0]!!.value == listDiag2[1]!!.value && listDiag2[1]!!.value == listDiag2[2]!!.value) {
            if (listDiag2[1]!!.value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
            } else if (listDiag2[1]!!.value == 'O') {
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            }
        }
        var setCount: Int by mutableStateOf(0)
        for (row in 0..2) {
            for (col in 0..2) {
                if (list[row][col]!!.value != ' ') {
                    setCount++
                }
            }
        }
        if (setCount >= 9) {
            window.setTimeout({
                window.alert("It's a draw")
            }, 200)
        }
    }


    fun singlePlayerGameplay() {

    }

    fun multiPlayerGameplay() {

    }

    fun computersChoice() {
        var empties: MutableList<Field> = mutableStateListOf()
        for (row in 0..2) {
            for (col in 0..2) {
                if (list[row][col]!!.value == ' ') {
                    empties.add(list[row][col]!!)
                }

            }
        }

        var randomIndex: Int = Random.nextInt(0, empties.size)
        empties[randomIndex].value = 'X'
        // empties[randomIndex].clicked = true
        empties.clear()
    }


    var playPressed: Boolean by mutableStateOf(false)



    renderComposable(rootElementId = "root") {
        Table({
            style {
                width(650.px)
                height(150.px)
                border(3.px, LineStyle.Solid, Color.black)
                property("border-spacing", "0px")
                textAlign("center")
                property("vertical-align", "middle")

            }

        }) {
            Td({
                style {
                    width(100.px)
                    height(150.px)
                    fontSize(25.px)
                    border(3.px, LineStyle.Solid, Color.black)
                    property("border-spacing", "0px")
                    textAlign("center")
                    property("vertical-align", "middle")
                    if (singleOrMulti) {
                        backgroundColor(Color.darkgoldenrod)
                    }
                }

            }) {

                Button({

                    style {
                        width(100.px)
                        height(50.px)
                    }
                    onClick {   singleOrMulti = true  }

                })
                {
                    Text("Play with your friend")
                }
                Button({

                    style {
                        width(100.px)
                        height(50.px)
                    }
                    onClick {   singleOrMulti = false  }

                })
                {
                    Text("Play with the computer")
                }

            }

            Td({
                style {
                    width(100.px)
                    height(150.px)
                    fontSize(50.px)
                    border(3.px, LineStyle.Solid, Color.black)
                    property("border-spacing", "0px")
                    textAlign("center")
                    property("vertical-align", "middle")
                    if (choiceXY) {
                        backgroundColor(Color.darkgoldenrod)
                    }
                }

            }) {
                Button({
                    style{
                        width(100.px)
                        height(50.px)
                    }
                    onClick {  choiceXY = true }
                }){
                    Text("X")
                }
                Button({
                    style{
                        width(100.px)
                        height(50.px)
                    }
                    onClick {  choiceXY = false }
                }){
                    Text("0")
                }
            }
            Td({
                style {
                    width(100.px)
                    height(150.px)
                    fontSize(25.px)
                    property("border-spacing", "0px")
                    textAlign("center")
                    property("vertical-align", "middle")
                    //backgroundColor(Color.green)
                }

            })
            {
                Text("Difficulty")
                Button({
                    style {
                        width(100.px)
                        height(30.px)
                    }
                }) {
                    Text("Easy")
                }
                Button({
                    style {
                        width(100.px)
                        height(30.px)
                    }
                }) {
                    Text("Normal")
                }

            }
            Td({
                style {
                    width(100.px)
                    height(150.px)
                   // fontSize(40.px)
                 //   property("border-spacing", "0px")
                  //  textAlign("center")
                   // property("vertical-align", "middle")

                }


            }) {

                Button({
                    style{
                     /*   if (!playPressed) {
                            border(5.px, LineStyle.Outset)
                        } else {
                            border(5.px, LineStyle.Inset)
                        }

                    */
                        fontSize(40.px)
                        width(85.percent)
                        height(85.percent)
                        backgroundColor(Color.green)
                    }
                    onClick {
                        playPressed = true
                    }
                }){
                    Text("▶ \n Play")
                }
            }
            Td({
                style {
                    width(100.px)
                    height(150.px)
                  //  border(3.px, LineStyle.Solid, Color.black)
                    property("border-spacing", "0px")

                }


            }) {
                Button({
                    style{
                        width(85.percent)
                        height(85.percent)
                        fontSize(40.px)
                        backgroundColor(Color.indianred)
                    }
                    onClick {  }
                }){
                    Text("Reset")
                }
            }
        }
        //Main table
        Table({
            style {
              //  property("border-spacing", "0px")
                if (!playPressed) {
                    display(DisplayStyle.None)
                } else {
                    display(DisplayStyle.Contents)
                }

            }
        }) {
            for (row in 0..2) {
                Tr {
                    for (col in 0..2) {
                        Td({
                            if (!list[row][col]!!.clicked) {

                                onClick {
                                    if (singleOrMulti) {
                                        setValue(row, col)
                                        checkEnd()
                                        list[row][col]!!.clicked = true
                                    } else {
                                        setValue2(row, col)
                                        checkEnd()
                                        for (n in 0..1000) {
                                            for (m in 0..1000) {
                                                2 + 2
                                            }
                                        }
                                        computersChoice()
                                        checkEnd()

                                    }
                                }
                            }

                            style {
                                border(5.px, LineStyle.Solid, Color.black)
                                if (row == 0) {
                                    property("border-top-color", "transparent")
                                }
                                if (row == 2) {
                                    property("border-bottom-color", "transparent")
                                }
                                if (col == 0) {
                                    property("border-left-color", "transparent")
                                }
                                if (col == 2) {
                                    property("border-right-color", "transparent")
                                }
                                //property("border-width", "5px")
                                //  property("border-style", "solid")
                                width(200.px)
                                height(200.px)
                                fontSize(100.px)
                                textAlign("center")

                            }
                        }) {
                            Text(list[row][col]!!.value.toString())
                        }
                    }
                }
            }
        }
    }
}










