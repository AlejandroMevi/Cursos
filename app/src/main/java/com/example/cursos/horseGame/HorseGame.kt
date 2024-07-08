package com.example.cursos.horseGame

import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.cursos.Manifest
import com.example.cursos.R
import com.example.cursos.databinding.ActivityHorseGameBinding
import kotlinx.coroutines.Runnable
import java.util.concurrent.TimeUnit

class HorseGame : AppCompatActivity() {
    private lateinit var binding: ActivityHorseGameBinding

    private var bitmap: Bitmap? = null

    private var mHandler : Handler? = null
    private var timeInSeconds: Long = 0
    private var gaming = true

    private var widht_bonus = 0

    private var cellSelectedX = 0
    private var cellSelectedY = 0

    private var levesMoves = 64
    private var movesRequired = 4
    private var moves = 64
    private var options = 0
    private var bonus = 0

    private var checkMovement = true

    private var nameColorBlack = "black_cell"
    private var nameColorWhite = "white_cell"

    private lateinit var board: Array<IntArray>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorseGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initScreenGame()
        startGame()
    }

    fun checkCellClicked(v: View) {
        val name = v.tag.toString()
        val x = name.subSequence(1, 2).toString().toInt()
        val y = name.subSequence(2, 3).toString().toInt()

        checkCell(x, y)
    }

    private fun checkCell(x: Int, y: Int) {
        var checkTrue = true

        if (checkMovement){
        val difX = x - cellSelectedX
        val difY = y - cellSelectedY

            checkTrue = false
        if (difX == 1 && difY == 2) checkTrue = true
        if (difX == 1 && difY == -2) checkTrue = true
        if (difX == 2 && difY == 1) checkTrue = true
        if (difX == 2 && difY == -1) checkTrue = true
        if (difX == -1 && difY == 2) checkTrue = true
        if (difX == -1 && difY == -2) checkTrue = true
        if (difX == -2 && difY == 1) checkTrue = true
        if (difX == -2 && difY == -1) checkTrue = true
        }
        else{
            if (board[x][y] != 1){
                bonus--
                binding.tvBonusData.text = " + $bonus"
                if (bonus == 0) binding.tvBonusData.text = ""
            }
        }
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
        var moves_done = levesMoves - moves
        var bonus_done = moves_done / movesRequired
        var moves_rest = movesRequired * (bonus_done)
        var bonus_grow = moves_done - moves_rest
        var widthBonus = ((widht_bonus/movesRequired) * bonus_grow).toFloat()
        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0f, resources.displayMetrics).toInt()
        val width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthBonus, resources.displayMetrics).toInt()

        binding.vnNewBonus.setLayoutParams(TableRow.LayoutParams(width, height))
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
        checkMovement = true
        checkOptions(x, y)

        if (moves > 0){
            checkNewBonus()
            checkGameOver(x, y)
        }
        else  showMessage("You win", "Next leve", false)
    }

    private fun checkGameOver(x: Int, y: Int) {
        if (options == 0){
            if (bonus > 0) {
                checkMovement = false
                paintAllOptions()
            }
            else{
                showMessage("Game over", "Try again", true)
            }
        }
    }

    private fun paintAllOptions() {
        for (i in 0..7){
            for (j in 0..7){
                if(board[i][j] != 1) paintOptions(i,j)
                if (board[i][j] == 0) board[i][j] = 9
            }
        }
    }

    private fun showMessage(title: String, action: String, gameOver: Boolean) {
        gaming = false
        binding.lyMessage.visibility = View.VISIBLE
        binding.tvIntroLevel.text = title
        val score: String = if (gameOver){
            "Score $levesMoves/$levesMoves"
        }else{
            binding.tvTimeData.toString()
        }
        binding.tvIntroLives.text = score
        binding.tvAction.text = action
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

                if ( board[optionX][optionY] == 0) board[optionX][optionY] = 9
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

        widht_bonus = 2 * widhtCell.toInt()
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
    private fun resetTime(){
        mHandler?.removeCallbacks(chronometer)
        timeInSeconds = 0
        binding.tvTimeData.text = "00:00"
    }
    private fun startTime(){
        mHandler = Handler(Looper.getMainLooper())
        chronometer.run()
    }
    private var chronometer: Runnable = object: Runnable{
        override fun run() {
            try {
                if (gaming){
                    timeInSeconds++
                    updateStopWachView(timeInSeconds)
                }
            }finally {
                mHandler!!.postDelayed(this, 1000)
            }
        }
    }
    private fun updateStopWachView(timeInSeconds:Long){
        val formattedTime = getFormattedStopWatch((timeInSeconds * 1000))
        binding.tvTimeData.text = formattedTime
    }
    private fun getFormattedStopWatch(ms : Long): String{
        var milliseconds = ms
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
        return "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"
    }
    private fun startGame(){
        resetBoard()
        clearBoard()
        setFirstPosition()
        resetTime()
        startTime()
    }

    private fun clearBoard() {
        var iv: ImageView
        var colorBlack = ContextCompat.getColor(this, resources.getIdentifier(nameColorBlack, "color", packageName))
        var colorWhite = ContextCompat.getColor(this, resources.getIdentifier(nameColorWhite, "color", packageName))

        for (i in 0..7){
            for (j in 0..7){
                iv = findViewById(resources.getIdentifier("c$i$j","id", packageName))
                iv.setImageResource(R.drawable.hor)
                iv.setImageResource(0)

                if (checkColorCell(i, j) == "black") iv.setBackgroundColor(colorBlack)
                else iv.setBackgroundColor(colorWhite)
            }
        }
    }

    private fun shareGame(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        //val ssc : ScreenCapture = capture(this)
        //bitmap = ssc.getBitmap()
    }
}