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