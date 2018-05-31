package me.nithin.james.datechartsdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback
import me.nithin.james.HistoryChart

class HistoryChartActivity : AppCompatActivity() {

    private lateinit var labeltextColorImage: ImageView
    private lateinit var valueTextColorImage: ImageView
    private lateinit var squareColorImage: ImageView

    private var labelTextColor: Int = 0
    private var valueTextColor: Int = 0
    private var squareColor: Int = 0

    private lateinit var historyChart: HistoryChart
    private lateinit var cp: ColorPicker

    private lateinit var selectedColorOption: COLOR_OPTIONS

    enum class COLOR_OPTIONS {
        VALUE_TEXT,
        LABEL_TEXT,
        DATERANGE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_chart)

        labeltextColorImage = findViewById(R.id.img_text_color)
        valueTextColorImage = findViewById(R.id.img_grid_color)
        squareColorImage = findViewById(R.id.img_marker_color)

        labelTextColor = Color.parseColor("#00ff00")
        squareColor = Color.parseColor("#0000ff")
        valueTextColor = Color.parseColor("#ff0000")

        labeltextColorImage.setBackgroundColor(labelTextColor)
        squareColorImage.setBackgroundColor(squareColor)
        valueTextColorImage.setBackgroundColor(valueTextColor)

        historyChart = findViewById(R.id.historyChart)

        historyChart = findViewById(R.id.historyChart)

        historyChart.setColor(squareColor)
        historyChart.setTextColor(labelTextColor)
        historyChart.setValueTextColor(valueTextColor)
        historyChart.populateWithRandomData()

        val colorPickerCallback = ColorPickerCallback { color ->
            changeColor(color)
            cp.dismiss()
        }
        labeltextColorImage.setOnClickListener {
            cp = ColorPicker(this@HistoryChartActivity, Color.red(labelTextColor), Color.green(labelTextColor), Color.blue(labelTextColor))
            selectedColorOption = COLOR_OPTIONS.LABEL_TEXT
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
        squareColorImage.setOnClickListener {
            cp = ColorPicker(this@HistoryChartActivity, Color.red(squareColor), Color.green(squareColor), Color.blue(squareColor))
            selectedColorOption = COLOR_OPTIONS.DATERANGE
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
        valueTextColorImage.setOnClickListener {
            cp = ColorPicker(this@HistoryChartActivity, Color.red(valueTextColor), Color.green(valueTextColor), Color.blue(valueTextColor))
            selectedColorOption = COLOR_OPTIONS.VALUE_TEXT
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
    }

    private fun changeColor(color: Int) {
        when (selectedColorOption) {
            HistoryChartActivity.COLOR_OPTIONS.LABEL_TEXT -> {
                labelTextColor = color
                historyChart.setTextColor(color)
                labeltextColorImage.setBackgroundColor(color)
            }
            HistoryChartActivity.COLOR_OPTIONS.VALUE_TEXT -> {
                valueTextColor = color
                historyChart.setValueTextColor(color)
                valueTextColorImage.setBackgroundColor(color)
            }
            HistoryChartActivity.COLOR_OPTIONS.DATERANGE -> {
                squareColor = color
                historyChart.setColor(color)
                squareColorImage.setBackgroundColor(color)
            }
        }
    }
}
