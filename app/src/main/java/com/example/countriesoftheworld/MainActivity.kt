package com.example.countriesoftheworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.countriesoftheworld.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val countryName = binding.countryNameEditText.text.toString()

            lifecycleScope.launch {
                try {
                    val countries = restCountriesService.getCountryByName(countryName)
                    val country = countries[0]

                    binding.countryNameTextView.text = country.name.common
                    try {
                        binding.capitalTextView.text = country.capital[0]
                    } catch (e: NullPointerException) {
                        binding.capitalTextView.text = "Нет столицы"
                    }
                    binding.populationTextView.text = formatNumber(country.population) + " чел."
                    binding.areaTextView.text = formatNumber(country.area) + " кв. км."
                    binding.langTextView.text = languagesToString(country.languages)

                    loadSvg(binding.imageView, country.flags.svg)

                    binding.resultLayout.visibility = View.VISIBLE
                    binding.statusLayout.visibility = View.INVISIBLE
                } catch (e: Exception) {
                    binding.imageView3.setImageResource(R.drawable.baseline_search_off_24)
                    binding.statusTextView.text = "Страна не найдена"
                    binding.resultLayout.visibility = View.INVISIBLE
                    binding.statusLayout.visibility = View.VISIBLE
                }
            }
        }
    }
}