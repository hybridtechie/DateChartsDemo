package me.nithin.james.datechartsdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback
import me.nithin.james.DateRangeChart
import me.nithin.james.utils.SampleDataUtils

class DateRangeChartActivity : AppCompatActivity() {

    private lateinit var labeltextColorImage: ImageView
    private lateinit var valueTextColorImage: ImageView
    private lateinit var dateRangeColorImage: ImageView

    private var labelTextColor: Int = 0
    private var valueTextColor: Int = 0
    private var dateRangeColor: Int = 0

    private lateinit var dateRangeChart: DateRangeChart
    private lateinit var cp: ColorPicker

    private lateinit var selectedColorOption: COLOR_OPTIONS

    enum class COLOR_OPTIONS {
        VALUE_TEXT,
        LABEL_TEXT,
        DATERANGE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_range_chart)

        labeltextColorImage = findViewById(R.id.img_text_color)
        valueTextColorImage = findViewById(R.id.img_grid_color)
        dateRangeColorImage = findViewById(R.id.img_marker_color)

        labelTextColor = Color.parseColor("#00ff00")
        dateRangeColor = Color.parseColor("#0000ff")
        valueTextColor = Color.parseColor("#ff0000")

        labeltextColorImage.setBackgroundColor(labelTextColor)
        dateRangeColorImage.setBackgroundColor(dateRangeColor)
        valueTextColorImage.setBackgroundColor(valueTextColor)

        dateRangeChart = findViewById(R.id.streakChart)

        val sampleDataUtils = SampleDataUtils()
        val dateRangeChart = findViewById<DateRangeChart>(R.id.streakChart)
        dateRangeChart.refreshDrawableState()
        dateRangeChart.setColor(dateRangeColor)
        dateRangeChart.setLabelColor(labelTextColor)
        dateRangeChart.setValueTextColor(valueTextColor)
        val timestampList = sampleDataUtils.sampleTimeStampDataForStreakChart
        dateRangeChart.populateWithTimeStampData(timestampList)

        val colorPickerCallback = ColorPickerCallback { color ->
            changeColor(color)
            cp.dismiss()
        }
        labeltextColorImage.setOnClickListener {
            cp = ColorPicker(this@DateRangeChartActivity, Color.red(labelTextColor), Color.green(labelTextColor), Color.blue(labelTextColor))
            selectedColorOption = COLOR_OPTIONS.LABEL_TEXT
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
        dateRangeColorImage.setOnClickListener {
            cp = ColorPicker(this@DateRangeChartActivity, Color.red(dateRangeColor), Color.green(dateRangeColor), Color.blue(dateRangeColor))
            selectedColorOption = COLOR_OPTIONS.DATERANGE
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
        valueTextColorImage.setOnClickListener {
            cp = ColorPicker(this@DateRangeChartActivity, Color.red(valueTextColor), Color.green(valueTextColor), Color.blue(valueTextColor))
            selectedColorOption = COLOR_OPTIONS.VALUE_TEXT
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
    }

    private fun changeColor(color: Int) {
        when (selectedColorOption) {
            DateRangeChartActivity.COLOR_OPTIONS.LABEL_TEXT -> {
                labelTextColor = color
                dateRangeChart.setLabelColor(color)
                labeltextColorImage.setBackgroundColor(color)
            }
            DateRangeChartActivity.COLOR_OPTIONS.VALUE_TEXT -> {
                valueTextColor = color
                dateRangeChart.setValueTextColor(color)
                valueTextColorImage.setBackgroundColor(color)
            }
            DateRangeChartActivity.COLOR_OPTIONS.DATERANGE -> {
                dateRangeColor = color
                dateRangeChart.setColor(color)
                dateRangeColorImage.setBackgroundColor(color)
            }
        }
    }
}
