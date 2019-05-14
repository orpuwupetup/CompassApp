package com.orpuwupetup.compassapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

// Number conversion
fun Float.convertAzimuthFromRadiansToDegrees() : Float =
    when {
        this > 0 -> this.radiansToDegrees()
        this < 0 -> 360 + this.radiansToDegrees()
        else -> 0f
    }

private fun Float.radiansToDegrees() : Float =
    ((this/Math.PI) * 180).toFloat()

// end Number conversion

// custom listeners
fun EditText.afterTextChanged(doAfterTextChanged: (newText: String) -> Unit) {
    this.addTextChangedListener(getTextWatcher(doAfterTextChanged))
}

private fun getTextWatcher(doAfterTextChanged: (newText: String) -> Unit) =
        object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                doAfterTextChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // do nothing
            }
        }
// end custom listeners