package com.example.basiccalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basiccalculator.ui.theme.BasicCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCalculatorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val border = BorderStroke(1.dp, color = Color.Gray)
    val mContext = LocalContext.current
    val style = TextStyle(
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    )
    val result = remember {
        mutableStateOf(0.0)
    }
    val num1 = remember {
        mutableStateOf("")
    }

    val num2 = remember {
        mutableStateOf("")
    }

    fun isEmpty() {
        if (num1.value.isEmpty() || num2.value.isEmpty())
            Toast.makeText(mContext, "Please enter a value",
                Toast.LENGTH_SHORT).show()
        return
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {

        Text(
            text = result.value.toString(), fontSize = 35.sp,
            fontWeight = FontWeight.W700
        )
        Spacer(modifier = Modifier.height(15.dp))
        TextField(
            textStyle = TextStyle(fontSize = 18.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Number
            ),

            modifier = Modifier.fillMaxWidth(),
            value = num1.value, onValueChange = {
                num1.value = it
            }, placeholder = {
                Text(text = "0", style = style)
            })
        Spacer(modifier = Modifier.height(10.dp))

        TextField(textStyle = TextStyle(fontSize = 18.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            value = num2.value, onValueChange = {
                num2.value = it
            }, placeholder = {
                Text(text = "0", style = style)
            })
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(border = border,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                isEmpty()
                result.value = num1.value.toDouble() + num2.value.toDouble()
                println(result.value)
            }) {
            Text(text = "Add", style = style)
        }
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            border = border,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                isEmpty()
                result.value = num1.value.toDouble() - num2.value.toDouble()

            }) {
            Text(text = "Minus", style = style)
        }
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(border = border,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                isEmpty()
                result.value = num1.value.toDouble() * num2.value.toDouble()

            }) {
            Text(text = "Multiply", style = style)
        }
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(border = border,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                isEmpty()
                result.value = num1.value.toDouble() / num2.value.toDouble()

            }) {
            Text(text = "Divide", style = style)
        }

    }
}