package an.kurosaki.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)

        }
    }

    fun clickDatePicker(view : View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


       val dpd =  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
             //   Toast.makeText(this,"Date Picker works", Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.setText(selectedDate)
//
//                var sfd = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH)
//                var dateSFD = LocalDate.parse(selectedDate, sfd)
//              val selectedDateInMinutes = dateSFD.atTime()/100

//                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
//                val theDate = formatter.parse(selectedDate)
//                val selectedDateInMinutes = theDate!!.time/60000
//                val currentdate = formatter.parse(formatter.format(System.currentTimeMillis()))
//                val currentDateInMinutes = currentdate!!.time
                // val diffInMinutes = (currentDateInMinutes - selectedDateInMinutes)
                val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selected = formatter.parse(selectedDate)
                val todayDate = Calendar.getInstance().time
                val formatter2 = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
                val formatedDate = formatter2.format(todayDate)
                val numberOfDays = getUnitBetweenDates(selected, todayDate, TimeUnit.DAYS)
                tvSelectedDateInMinutes.setText(numberOfDays.toString())
             Toast.makeText(this,"Date Picker works $selected", Toast.LENGTH_LONG).show()
            },
            year,
            month,
            day
        )

        dpd.datePicker.setMaxDate(Date().time - 86400000) // 86400000 is milliseconds in 1 day
        dpd.show()

    }

    fun getUnitBetweenDates(startDate: Date, endDate: Date, unit: TimeUnit): Long {
        val timeDiff = endDate.getTime() - startDate.getTime()
        return unit.convert(timeDiff, TimeUnit.MILLISECONDS)
    }
}