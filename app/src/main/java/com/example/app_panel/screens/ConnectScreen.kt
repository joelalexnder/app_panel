package com.example.app_panel.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ConnectScreen(navController: NavController) {
    val fakeDevices = listOf("Panel-Ruta-001", "Panel-Ruta-002", "Panel-Bus-ABC")
    var selectedDevice by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Buscar Panel Disponible", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        fakeDevices.forEach { device ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedDevice = device }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedDevice == device,
                    onClick = { selectedDevice = device }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(device)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (selectedDevice != null) {
                    navController.navigate("createRoute")
                }
            },
            enabled = selectedDevice != null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Conectar")
        }
    }
}