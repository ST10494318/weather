package za.ac.iie.weather4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WeatherActivity : AppCompatActivity() {

    private var tempValues = intArrayOf(55, 19, 72, 54, 44, 67, 61)
    private val days = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val conditions = arrayOf("Sunny", "Rainy", "Cloudy", "Sunny", "Rainy", "Cloudy", "Sunny")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        // User Interface components
        val weatherTextView: TextView = findViewById(R.id.weatherData)
        val averageTempTextView: TextView = findViewById(R.id.averageTemp)
        val backButton: Button = findViewById(R.id.backButton)
        val updateTempsButton: Button = findViewById(R.id.updateTempsButton)

        // Checking if updated temperatures were passed back from UpdateTempsActivity
        intent.getIntArrayExtra("updatedTemps")?.let {
            tempValues = it // Assign only if not null
        }

        // Displaying days, temperatures, and conditions using a loop
        val weatherStringBuilder = StringBuilder()
        for (i in days.indices) {
            weatherStringBuilder.append("${days[i]}: ${tempValues[i]}°C, ${conditions[i]}\n")
        }
        weatherTextView.text = weatherStringBuilder.toString()

        // Average temperature using a loop
        var sum = 0
        for (t in tempValues) {
            sum += t
        }
        val average = sum.toDouble() / tempValues.size
        averageTempTextView.text = "Average Max Temperature: %.1f°C".format(average)

        // Back button to return to MainActivity
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Button to navigate to UpdateTempsActivity
        updateTempsButton.setOnClickListener {
            val intent = Intent(this, UpdateTempsActivity::class.java)
            intent.putExtra("currentTemps", tempValues)
            startActivity(intent)
        }
    }
}