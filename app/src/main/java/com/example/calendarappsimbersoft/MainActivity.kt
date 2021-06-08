package com.example.calendarappsimbersoft

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calendarappsimbersoft.databinding.ActivityMainBinding
import com.example.calendarappsimbersoft.presentation.calendar.CalendarFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    binding.fragmentContainer.id,
                    CalendarFragment.newInstance(),
                    CalendarFragment.TAG
                )
                .commit()
        }
    }
}