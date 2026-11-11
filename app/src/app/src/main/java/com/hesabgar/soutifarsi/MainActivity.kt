package com.hesabgar.soutifarsi

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

private lateinit var expression: EditText
private lateinit var result: TextView

override fun onCreate(savedInstanceState: Bundle?) {
super.onCreate(savedInstanceState)

val page = LinearLayout(this).apply {
orientation = LinearLayout.VERTICAL
gravity = Gravity.CENTER
setPadding(24, 32, 24, 32)
layoutDirection = ViewGroup.LAYOUT_DIRECTION_RTL
setBackgroundColor(Color.rgb(245, 247, 250))
}

val title = TextView(this).apply {
text = "حسابگر صوتی فارسی"
textSize = 26f
setTextColor(Color.rgb(25, 70, 120))
gravity = Gravity.CENTER
}

expression = EditText(this).apply {
hint = "مثلاً ۱۲۵ + ۷۵"
textSize = 20f
gravity = Gravity.CENTER
singleLine = true
}

val calculate = Button(this).apply {
text = "محاسبه"
textSize = 18f
setOnClickListener { calculateResult() }
}

result = TextView(this).apply {
text = "نتیجه اینجا نمایش داده می‌شود"
textSize = 22f
gravity = Gravity.CENTER
setTextColor(Color.rgb(20, 110, 70))
}

page.addView(title)
page.addView(expression)
page.addView(calculate)
page.addView(result)

setContentView(page)
}

private fun calculateResult() {
val text = expression.text.toString()
.replace(" ", "")
.replace('۰', '0')
.replace('۱', '1')
.replace('۲', '2')
.replace('۳', '3')
.replace('۴', '4')
.replace('۵', '5')
.replace('۶', '6')
.replace('۷', '7')
.replace('۸', '8')
.replace('۹', '9')
.replace('٫', '.')

val pattern = Regex("""^(-?\d+(?:\".\d+)"?)([+\-×÷*/])(-?\d+(?:\".\d+)"?)$""")
val match = pattern.matchEntire(text)

if (match == null) {
result.text = "لطفاً یک عبارت ساده مثل ۱۲ + ۳ وارد کن"
return
}

val a = match.groupValues[1].toDouble()
val operator = match.groupValues[2]
val b = match.groupValues[3].toDouble()

val answer = when (operator) {
"+" -> a + b
"-" -> a - b
"×", "*" -> a * b
"÷", "/" -> {
if (b == 0.0) {
result.text = "تقسیم بر صفر ممکن نیست"
return
}
a / b
}
else -> return
}

result.text = "نتیجه: $answer"
}
}
