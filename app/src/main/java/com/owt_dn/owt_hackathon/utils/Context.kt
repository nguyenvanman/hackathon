package com.owt_dn.owt_hackathon.utils

import android.app.DatePickerDialog
import android.content.Context
import java.util.*

fun Context.showDatePicker(
    default: Date = Date(),
    onDateSet: (Date) -> Unit,
    onFuture: Boolean = false
) {
    val now = Calendar.getInstance()
    now.time = default
    val year = now.get(Calendar.YEAR)
    val month = now.get(Calendar.MONTH)
    val date = now.get(Calendar.DATE)
    DatePickerDialog(
        this, { _, pickedYear, pickedMonth, pickedDate ->
            val calendar = Calendar.getInstance().apply { set(pickedYear, pickedMonth, pickedDate) }
            onDateSet.invoke(calendar.time)
        }, year, month, date
    ).apply {
        if (onFuture) {
            this.datePicker.minDate = now.time.time
        } else {
            this.datePicker.maxDate = now.time.time
        }
        show()
    }
}