package com.example.mybmiapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import com.example.mybmiapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var radioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        radioGroup = binding.radioGroup

        binding.weightPicker.minValue = 30
        binding.weightPicker.maxValue = 150

        binding.heightPicker.minValue = 100
        binding.heightPicker.maxValue = 250

        binding.calculate.setOnClickListener() {
            calculateBMI()
        }
        binding.reset.setOnClickListener() {
            noValue()
        }


    }


    private fun noValue() {
        binding.resultDisplay.text = ""
        binding.healthyDisplay.text = ""
        binding.radioGroup.clearCheck()
        binding.heightPicker.value = 100
        binding.weightPicker.value = 30
        binding.inputAge.setText("")
    }

//    private fun result() {
//        binding.weightPicker.setOnValueChangedListener{_,_,_->
//            calculateBMI()
//        }
//        binding.heightPicker.setOnValueChangedListener{_,_,_->
//            calculateBMI()
//        }
//    }

    private fun calculateBMI() {
        val group=binding.radioGroup
        val m=binding.maleB
        val f=binding.femaleB

        val intAge = binding.inputAge
        val x = intAge.text.toString()

        if (x.isBlank() || group.checkedRadioButtonId==-1) {
            if(x.isBlank()){
                Toast.makeText(this, "Please provide age", Toast.LENGTH_SHORT).show()
            }
            else if(group.checkedRadioButtonId==-1){
                Toast.makeText(this, "Please provide gender", Toast.LENGTH_SHORT).show()
            }

//
        } else {
            val height = binding.heightPicker.value
            val doubleHeight = height.toDouble() / 100

            val weight = binding.weightPicker.value
            val doubleWeight = weight.toDouble()

            val bmi = doubleWeight / (doubleHeight * doubleHeight)
            if(m.isChecked){

                binding.resultDisplay.text = String.format("Considering Male ,your BMI is %.2f", bmi)
                binding.healthyDisplay.text = String.format("Note: %s ", healthyMessage(bmi))
            }
            else if(f.isChecked){

                binding.resultDisplay.text = String.format("Considering Female ,your BMI is %.2f", bmi)
                binding.healthyDisplay.text = String.format("Note: %s ", healthyMessage(bmi))
            }

        }


    }

    private fun healthyMessage(bmi: Double): String {
        if (bmi < 18.5) {
            return "You are underWeight"
        }
        if (bmi < 25.0) {
            return "You are Healthy"
        }
        if (bmi < 30) {
            return "You are Over-weight"
        }
        return "You need doctor"
    }
}