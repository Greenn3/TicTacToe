import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseOptions
import dev.gitlive.firebase.initialize
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import kotlin.js.Date
import kotlin.random.Random


open class Field {
    var value: Char by mutableStateOf(' ')
    var clicked: Boolean by mutableStateOf(false)

}

val scope = MainScope()

fun main() {


    val firebase = Firebase.initialize(options = FirebaseOptions(

        apiKey = "AIzaSyBlC7b0GEiWbMpF475QY6o4EjPCK3KK07c",
        authDomain = "tictactoemultiplayer-ee7e9.firebaseapp.com",
        projectId = "tictactoemultiplayer-ee7e9",
        storageBucket = "tictactoemultiplayer-ee7e9.appspot.com",
        gcmSenderId = "731398405007",
        applicationId = "1:731398405007:web:fffe73939dfac892ab29fa",

    ))

    document.body?.style?.backgroundColor = "grey"

    val list: MutableList<MutableList<Field>> = mutableStateListOf(
        mutableStateListOf(Field(), Field(), Field()),
        mutableStateListOf(Field(), Field(), Field()),
        mutableStateListOf(Field(), Field(), Field())
    )



   // list[2][2]!!.value = 'h'

    val listRow1 = mutableStateListOf(list[0][0], list[0][1], list[0][2])
    val listRow2 = mutableStateListOf(list[1][0], list[1][1], list[1][2])
    val listRow3 = mutableStateListOf(list[2][0], list[2][1], list[2][2])
    val listCol1 = mutableStateListOf(list[0][0], list[1][0], list[2][0])
    val listCol2 = mutableStateListOf(list[0][1], list[1][1], list[2][1])
    val listCol3 = mutableStateListOf(list[0][2], list[1][2], list[2][2])
    val listDiag1 = mutableStateListOf(list[0][0], list[1][1], list[2][2])
    val listDiag2 = mutableStateListOf(list[2][0], list[1][1], list[0][2])

    val bigList = listOf(listCol1, listCol2, listCol3, listDiag1, listDiag2, listRow1, listRow2, listRow3)

    fun generateId() : String{
        var time : Double = Date.now()
        return time.toString()

    }

  var id by mutableStateOf("")

    var isComputersTurn by mutableStateOf(false)
//true = multi ; false = single
    var singleOrMulti: Boolean by mutableStateOf(false)
    //false = O ; true = X
    var XorO: Boolean by mutableStateOf(false)

    var choiceXY: Boolean by mutableStateOf(false)
    fun setValue(rowIndex: Int, colIndex: Int) {
        if (XorO) {
            list[rowIndex][colIndex].value = 'X'
            XorO = false
        } else {
            list[rowIndex][colIndex].value = 'O'
            XorO = true

        }
    }

    fun setValue2(rowIndex: Int, colIndex: Int) {
        if (XorO) {
            list[rowIndex][colIndex].value = 'X'

        } else {
            list[rowIndex][colIndex].value = 'O'


        }
    }


    fun checkEnd(list : List<Field>) : Boolean {
        if (list[0].value == list[1].value && list[1].value == list[2].value) {
            if (list[1].value == 'X') {
                window.setTimeout({
                    window.alert("X wins")
                }, 200)
                return true
            }
            else if(list[1].value == 'O'){
                window.setTimeout({
                    window.alert("O wins")
                }, 200)
            return true
            }
        }
        return false
    }

fun endLock(){
    for(row in 0..2){
        for(col in 0..2){
            list[row][col].clicked = true
        }
    }
}
    fun checkEnd() : Boolean {
        if(bigList.any{
            checkEnd(it)
            }){
            endLock()
            return true
        }
        var setCount: Int by mutableStateOf(0)
        for (row in 0..2) {
            for (col in 0..2) {
                if (list[row][col].value != ' ') {
                    setCount++
                }
            }
        }
        if (setCount >= 9) {
            window.setTimeout({
                window.alert("It's a draw")
            }, 200)
            endLock()
            return true
        }
        return false
    }


    fun singlePlayerGameplay() {

    }

    fun multiPlayerGameplay() {

    }

    fun computersChoice() {
        var empties: MutableList<Field> = mutableStateListOf()
        for (row in 0..2) {
            for (col in 0..2) {
                if (list[row][col].value == ' ') {
                    empties.add(list[row][col])
                }

            }
        }

        var randomIndex: Int = Random.nextInt(0, empties.size)
        empties[randomIndex].value = 'X'
        // empties[randomIndex].clicked = true
        empties.clear()
    }


    var playPressed: Boolean by mutableStateOf(false)


    fun reset(){
        playPressed = false
        for(row in 0..2){
            for(col in 0..2){
                list[row][col].clicked = false
                list[row][col].value = ' '
                XorO = false
            }
        }

    }


//var code : String by mutableStateOf("")

    fun convertBack(data : StoreData){
       list[0][0].value = data.list[0][0]
        list[0][1].value = data.list[1][0]
        list[0][2].value = data.list[2][0]
        list[1][0].value = data.list[3][0]
        list[1][1].value = data.list[4][0]
        list[1][2].value = data.list[5][0]
        list[2][0].value = data.list[6][0]
        list[2][1].value = data.list[7][0]
        list[2][2].value = data.list[8][0]


    }



    renderComposable(rootElementId = "root") {
        Table({
            style {
                width(700.px)
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
                        id = generateId()
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
                    onClick {
                        reset()
                    }
                }){
                    Text("Reset")
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
                        height(40.percent)
                        fontSize(20.px)
                        backgroundColor(Color.indianred)
                    }
                    onClick {
                        console.log(id)
                        scope.launch {
                            convertBack(read(id))
                        }
                    }
                }){
                    Text("Submit")
                }
                TextInput(id){
                    onInput { id = it.value }
                }
                Label { Text(id) }
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
                            if (!list[row][col].clicked) {
                                onClick {
                                    if (!isComputersTurn) {
                                        if (singleOrMulti) {
                                            setValue(row, col)

                                            list[row][col].clicked = true
                                            checkEnd()
                                            scope.launch { write(generateId(), StoreData(
                                                list.flatMap {
                                                    it.map {
                                                        it.value.toString()
                                                    }
                                                }, if(XorO) "X" else "O"
                                            )) }
                                        } else {
                                            setValue2(row, col)

                                            isComputersTurn = true
                                            checkEnd()
                                            scope.launch {
                                                delay(1000)
                                                computersChoice()
                                                isComputersTurn = false
                                                checkEnd()
                                            }
                                        }
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
                            Text(list[row][col].value.toString())
                        }
                    }
                }
            }
        }
    }
}













