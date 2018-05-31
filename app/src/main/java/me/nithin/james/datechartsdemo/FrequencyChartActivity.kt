package me.nithin.james.datechartsdemo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback
import me.nithin.james.FrequencyDayChart
import me.nithin.james.utils.SampleDataUtils

class FrequencyChartActivity : AppCompatActivity() {

    internal lateinit var textColorImage: ImageView
    internal lateinit var gridColorImage: ImageView
    internal lateinit var markerColorImage: ImageView

    internal var textColor: Int = 0
    internal var gridColor: Int = 0
    internal var markerColor: Int = 0

    internal lateinit var frequencyDayChart: FrequencyDayChart
    internal lateinit var cp: ColorPicker

    internal lateinit var selectedColorOption: COLOR_OPTIONS

    enum class COLOR_OPTIONS {
        TEXT,
        GRID,
        MARKER
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frequency_chart)

        textColorImage = findViewById(R.id.img_text_color)
        gridColorImage = findViewById(R.id.img_grid_color)
        markerColorImage = findViewById(R.id.img_marker_color)

        textColor = Color.parseColor("#00ff00")
        markerColor = Color.parseColor("#0000ff")
        gridColor = Color.parseColor("#ff0000")

        textColorImage.setBackgroundColor(textColor)
        markerColorImage.setBackgroundColor(markerColor)
        gridColorImage.setBackgroundColor(gridColor)

        frequencyDayChart = findViewById(R.id.frequencyChart)
        val sampleDataUtils = SampleDataUtils()
        val timestampList = sampleDataUtils.sampleTimeStampData
        val map = sampleDataUtils.getWeekdayFrequency(timestampList)

        frequencyDayChart = findViewById(R.id.frequencyChart)
        frequencyDayChart.populateWithRandomData()
        frequencyDayChart.setFrequency(map)
        frequencyDayChart.setGridColor(gridColor)
        frequencyDayChart.setTextColor(textColor)
        frequencyDayChart.setMarkerColor(markerColor)

        val colorPickerCallback = ColorPickerCallback { color ->
            changeColor(color)
            cp.dismiss()
        }
        textColorImage.setOnClickListener {
            cp = ColorPicker(this@FrequencyChartActivity, Color.red(textColor), Color.green(textColor), Color.blue(textColor))
            selectedColorOption = COLOR_OPTIONS.TEXT
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
        markerColorImage.setOnClickListener {
            cp = ColorPicker(this@FrequencyChartActivity, Color.red(markerColor), Color.green(markerColor), Color.blue(markerColor))
            selectedColorOption = COLOR_OPTIONS.MARKER
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
        gridColorImage.setOnClickListener {
            cp = ColorPicker(this@FrequencyChartActivity, Color.red(gridColor), Color.green(gridColor), Color.blue(gridColor))
            selectedColorOption = COLOR_OPTIONS.GRID
            cp.setCallback(colorPickerCallback)
            cp.show()
        }
    }

    private fun changeColor(color: Int) {
        when (selectedColorOption) {
            FrequencyChartActivity.COLOR_OPTIONS.GRID -> {
                gridColor = color
                frequencyDayChart.setGridColor(color)
                gridColorImage.setBackgroundColor(color)
            }
            FrequencyChartActivity.COLOR_OPTIONS.TEXT -> {
                textColor = color
                frequencyDayChart.setTextColor(color)
                textColorImage.setBackgroundColor(color)
            }
            FrequencyChartActivity.COLOR_OPTIONS.MARKER -> {
                markerColor = color
                frequencyDayChart.setMarkerColor(color)
                markerColorImage.setBackgroundColor(color)
            }
        }
    }
}
