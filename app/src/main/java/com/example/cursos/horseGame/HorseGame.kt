package com.example.cursos.horseGame

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import androidx.core.content.ContextCompat
import com.example.cursos.R
import com.example.cursos.databinding.ActivityHorseGameBinding

class HorseGame : AppCompatActivity() {
    private lateinit var binding: ActivityHorseGameBinding
    private var cellSelectedX = 0
    private var cellSelectedY = 0

    private var movesRequired = 4
    private var moves = 64
    private var options = 0
    private var bonus = 0

    private var nameColorBlack = "black_cell"
    private var nameColorWhite = "white_cell"

    private lateinit var board: Array<IntArray>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorseGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initScreenGame()
        resetBoard()
        setFirstPosition()
    }

    fun checkCellClicked(v: View) {
        val name = v.tag.toString()
        val x = name.subSequence(1, 2).toString().toInt()
        val y = name.subSequence(2, 3).toString().toInt()

        checkCell(x, y)
    }

    private fun checkCell(x: Int, y: Int) {
        val difX = x - cellSelectedX
        val difY = y - cellSelectedY

        var checkTrue = false

        if (difX == 1 && difY == 2) checkTrue = true
        if (difX == 1 && difY == -2) checkTrue = true
        if (difX == 2 && difY == 1) checkTrue = true
        if (difX == 2 && difY == -1) checkTrue = true
        if (difX == -1 && difY == 2) checkTrue = true
        if (difX == -1 && difY == -2) checkTrue = true
        if (difX == -2 && difY == 1) checkTrue = true
        if (difX == -2 && difY == -1) checkTrue = true

        if (board[x][y] == 1) checkTrue = false

        if (checkTrue) selectCell(x, y)
    }

    private fun resetBoard() {

        // 0 esta libre
        // 1 casilla marcada
        //2 es un bonus
        // 9 es una opcion del movimiento actual

        board = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0),
        )
    }

    private fun setFirstPosition() {
        val x: Int = (0..7).random()
        val y: Int = (0..7).random()

        cellSelectedX = x
        cellSelectedY = y
        selectCell(x, y)
    }

    private fun growProgressBonus() {

/*

        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics).toInt()
        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthBonus, resources.displayMetrics).toInt()

        v.setLayoutParams(TableRow.LayoutParams(width, height))

 */
    }

    private fun selectCell(x: Int, y: Int) {

        moves--
        binding.tvMovesData.text = moves.toString()

        growProgressBonus()
        if (board[x][y] == 2){
            bonus++
            binding.tvBonusData.text = " + $bonus"
        }

        board[x][y] = 1
        paintHorseCell(cellSelectedX, cellSelectedY, "previus_cell")

        cellSelectedX = x
        cellSelectedY = y

        clearOptions()

        paintHorseCell(x, y, "selected_cell")

        checkOptions(x, y)

        if (moves > 0){
            checkNewBonus()
          //  checkGameOver()
        }
        //else checkSucessfulEnd()
    }

    private fun checkNewBonus() {
        if (moves % movesRequired == 0){
            var bonusCellX = 0
            var bonusCellY = 0

            var bonusCell = false
            if (bonusCell == false){
                bonusCellX = (0..7).random()
                bonusCellY = (0..7).random()

                if (board[bonusCellX][bonusCellY] == 0) bonusCell = true
            }
            board[bonusCellX][bonusCellY] = 2
            paintBonusCell(bonusCellX, bonusCellY)
        }
    }

    private fun paintBonusCell(x: Int, y: Int) {
        var iv: ImageView = findViewById(resources.getIdentifier("c$x$y","id", packageName))
        iv.setImageResource(R.drawable.bonus)
    }


    private fun clearOptions() {
        for (i in 0..7) {
            for (j in 0..7) {
                if (board[i][j] == 9 || board[i][j] == 2) {
                    if (board[i][j] == 9 || board[i][j] == 0) {
                        clearOption(i, j)
                    }
                }
            }
        }
    }

    private fun clearOption(x: Int, y: Int) {

        val iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))

        if (checkColorCell(x, y) == "black") {
            iv.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    resources.getIdentifier(nameColorBlack, "color", packageName)
                )
            )
        } else {
            iv.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    resources.getIdentifier(nameColorWhite, "color", packageName)
                )
            )
        }

        if (board[x][y] == 1) iv.setBackgroundColor(
            ContextCompat.getColor(
                this,
                resources.getIdentifier("previus_cell", "color", packageName)
            )
        )
    }

    private fun checkOptions(x: Int, y: Int) {
        options = 0

        checkMove(x, y, 1, 2)
        checkMove(x, y, 2, 1)
        checkMove(x, y, 1, -2)
        checkMove(x, y, 2, -1)
        checkMove(x, y, -1, 2)
        checkMove(x, y, -2, 1)
        checkMove(x, y, -1, -2)
        checkMove(x, y, -2, -1)

        binding.tvOptionesData.text = options.toString()
    }

    private fun checkMove(x: Int, y: Int, movX: Int, movY: Int) {
        val optionX = x + movX
        val optionY = y + movY

        if (optionX < 8 && optionY < 8 && optionX >= 0 && optionY >= 0) {
            if (board[optionX][optionY] == 0
                || board[optionX][optionY] == 2) {
                options++
                paintOptions(optionX, optionY)

                board[optionX][optionY] = 9
            }
        }
    }

    private fun paintOptions(x: Int, y: Int) {
        val iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        if (checkColorCell(x, y) == "black") iv.setBackgroundResource(R.drawable.option_black)
        else iv.setBackgroundResource(R.drawable.option_white)
    }

    private fun checkColorCell(x: Int, y: Int): String {
        val color: String
        val blackColumnX = arrayOf(0, 2, 4, 6)
        val blackRowX = arrayOf(1, 3, 5, 7)

        color = if ((blackColumnX.contains(x) && blackColumnX.contains(y)) || (blackRowX.contains(x) && blackRowX.contains(
                y
            ))
        )
            "black"
        else "white"

        return color
    }

    private fun paintHorseCell(x: Int, y: Int, color: String) {
        val iv: ImageView = findViewById(resources.getIdentifier("c$x$y", "id", packageName))
        iv.setBackgroundColor(
            ContextCompat.getColor(
                this,
                resources.getIdentifier(color, "color", packageName)
            )
        )
        iv.setImageResource(R.drawable.hor)
    }

    private fun initScreenGame() {
        setSizeBoard()
        hideMessage()
    }

    private fun setSizeBoard() {
        var iv: ImageView

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        val widthDp = (width / resources.displayMetrics.density)
        val lateralMarginsDp = 0
        val widhtCell = (widthDp - lateralMarginsDp) / 8
        val heigthCell = widhtCell

        for (i in 0..7) {
            for (j in 0..7) {
                iv = findViewById(resources.getIdentifier("c$i$j", "id", packageName))

                val height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    heigthCell,
                    resources.displayMetrics
                ).toInt()
                val width = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    widhtCell,
                    resources.displayMetrics
                ).toInt()

                iv.layoutParams = TableRow.LayoutParams(width, height)
            }
        }
    }

    private fun hideMessage() {
        binding.lyMessage.visibility = View.INVISIBLE
    }


}