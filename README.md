link to respository : https://github.com/ST10494318/weather.git

updated code images :
![1](https://github.com/user-attachments/assets/34e849cd-55d4-469a-9688-872bcd488321)
![2](https://github.com/user-attachments/assets/b98333d1-c6b2-4471-9ec1-d2ecc385650b)
![3](https://github.com/user-attachments/assets/ee6af45c-3917-4b07-85bf-7d0944cd3e62)
![4](https://github.com/user-attachments/assets/1b3977b9-7ccc-486c-a6cd-ea9bc681fb9d)



WEATHER TEMP CODE  :

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

CODE FOR UPDATED TEMP :

package za.ac.iie.weather4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UpdateTempsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_temps)

        // temperatures from WeatherActivity
        val currentTemps = intent.getIntArrayExtra("currentTemps") ?: intArrayOf(55, 19, 72, 54, 44, 67, 61)

        // Initialize EditText
        val tempInputs = arrayOf(
            findViewById<EditText>(R.id.tempMon),
            findViewById<EditText>(R.id.tempTue),
            findViewById<EditText>(R.id.tempWed),
            findViewById<EditText>(R.id.tempThu),
            findViewById<EditText>(R.id.tempFri),
            findViewById<EditText>(R.id.tempSat),
            findViewById<EditText>(R.id.tempSun)
        )

        // Populate EditText fields with current temperatures
        for (i in tempInputs.indices) {
            tempInputs[i].setText(currentTemps[i].toString())
        }

        // Save button to update temperatures and return to WeatherActivity
        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            val updatedTemps = IntArray(7)
            for (i in tempInputs.indices) {
                updatedTemps[i] = tempInputs[i].text.toString().toIntOrNull() ?: currentTemps[i]
            }
            val intent = Intent(this, WeatherActivity::class.java)
            intent.putExtra("updatedTemps", updatedTemps)
            startActivity(intent)
            finish()
        }

        // Back button to return to WeatherActivity
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

