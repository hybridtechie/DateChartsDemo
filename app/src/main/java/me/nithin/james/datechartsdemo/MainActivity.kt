package me.nithin.james.datechartsdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frequencyChartButton = findViewById<Button>(R.id.freqBtn)
        val dateRangeChartButton = findViewById<Button>(R.id.btnDateRange)
        val historyChartButton = findViewById<Button>(R.id.btnHistoryChart)

        frequencyChartButton.setOnClickListener { startActivity(Intent(this@MainActivity, FrequencyChartActivity::class.java)) }
        dateRangeChartButton.setOnClickListener { startActivity(Intent(this@MainActivity, DateRangeChartActivity::class.java)) }
        historyChartButton.setOnClickListener { startActivity(Intent(this@MainActivity, HistoryChartActivity::class.java)) }
    }
}
