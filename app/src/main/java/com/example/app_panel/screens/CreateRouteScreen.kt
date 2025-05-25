package com.example.app_panel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CreateRouteScreen(
    onSave: (String, List<Pair<String, Int>>, List<Pair<String, Int>>, Boolean) -> Unit
) {
    var routeName by rememberSaveable { mutableStateOf("") }

    var idaParaderos by remember { mutableStateOf(mutableListOf<Pair<String, Int>>()) }
    var vueltaParaderos by remember { mutableStateOf(mutableListOf<Pair<String, Int>>()) }

    var isMinutes by remember { mutableStateOf(true) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Crear Nueva Ruta", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = routeName,
            onValueChange = { routeName = it },
            label = { Text("Nombre de la Ruta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Paraderos de Ida", style = MaterialTheme.typography.titleMedium)
        DynamicParaderosList(paraderos = idaParaderos) { updated ->
            idaParaderos = updated.toMutableList()
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Paraderos de Vuelta", style = MaterialTheme.typography.titleMedium)
        DynamicParaderosList(paraderos = vueltaParaderos) { updated ->
            vueltaParaderos = updated.toMutableList()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text("Tiempo en: ")
            Spacer(modifier = Modifier.width(8.dp))
            Row {
                RadioButton(selected = isMinutes, onClick = { isMinutes = true })
                Text("Minutos")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row {
                RadioButton(selected = !isMinutes, onClick = { isMinutes = false })
                Text("Segundos")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onSave(routeName, idaParaderos, vueltaParaderos, isMinutes)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar y Enviar al Panel")
        }
    }
}

@Composable
fun DynamicParaderosList(
    paraderos: List<Pair<String, Int>>,
    onParaderosChange: (List<Pair<String, Int>>) -> Unit
) {
    val items = remember { paraderos.toMutableStateList() }

    Column {
        items.forEachIndexed { index, (nombre, tiempo) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = {
                        items[index] = it to items[index].second
                        onParaderosChange(items)
                    },
                    label = { Text("Paradero ${index + 1}") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(
                    value = if (items[index].second == 0) "" else items[index].second.toString(),
                    onValueChange = {
                        val time = it.toIntOrNull() ?: 0
                        items[index] = items[index].first to time
                        onParaderosChange(items)
                    },
                    label = { Text("Tiempo") },
                    modifier = Modifier.width(80.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {
            items.add("" to 0)
            onParaderosChange(items)
        }) {
            Text("Agregar Paradero")
        }
    }
}