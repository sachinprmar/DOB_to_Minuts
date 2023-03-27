package com.example.dobcacl

//import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate :TextView? = null
    private var result:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker:Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.selectedDate)
        result = findViewById(R.id.resultText)

        btnDatePicker.setOnClickListener {
            clickdatePicker()
        }

    }

    private fun clickdatePicker(){

        val myCalandar = Calendar.getInstance()
        val year = myCalandar.get(Calendar.YEAR)
        val month = myCalandar.get(Calendar.MONTH)
        val day = myCalandar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
//            DatePickerDialog.OnDateSetListener -> Can be written or not no matter
            {_, selctedyear, selectedmonth, selecteddayofMonth ->
//                Toast.makeText(this,"$selecteddayofMonth / ${selectedmonth+1} / $selctedyear",
//                    Toast.LENGTH_LONG).show()
//
                val selDate = "$selecteddayofMonth/${selectedmonth+1}/$selctedyear"
                selectedDate?.text = selDate

                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate = sdf.parse(selDate)
                theDate?.let {
                    val selectedDateinMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                    val currentDateTime = currentDate.time / 60000
                    result?.text = (currentDateTime - selectedDateinMinutes).toString()
                    }
                }

            },
            year, month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()





    }
}