package com.example.mayor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun App() {
    // Estados para almacenar los tres números y el resultado
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var numero3 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

    Column {
        // Campos de texto para los números
        TextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = numero3,
            onValueChange = { numero3 = it },
            label = { Text("Número 3") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Botón para calcular el número menor y mayor
        Button(onClick = {
            resultado = calcularMenorYMayor(numero1, numero2, numero3)
        }) {
            Text("Calcular Menor y Mayor")
        }
        // Mostrar el resultado
        resultado?.let {
            Text(it)
        }
    }
}

fun calcularMenorYMayor(n1: String, n2: String, n3: String): String {
    val numeros = listOf(n1.toDoubleOrNull(), n2.toDoubleOrNull(), n3.toDoubleOrNull()).filterNotNull()
    if (numeros.size < 3) {
        return "Por favor, ingrese tres números válidos."
    }
    val menor = numeros.minOrNull() ?: return "Error calculando el menor número."
    val mayor = numeros.maxOrNull() ?: return "Error calculando el mayor número."
    return "El número menor es $menor y el mayor es $mayor."
}
