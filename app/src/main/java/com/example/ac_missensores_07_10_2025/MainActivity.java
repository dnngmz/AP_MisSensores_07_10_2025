package com.example.ac_missensores_07_10_2025;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listaSensores;
    private Button btnActualizar;
    //Administrador de sensores
    private SensorManager gestorSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSensores = findViewById(R.id.listaSensores);
        btnActualizar = findViewById(R.id.btnActualizar);

        // Llamar al servicio de sensores
        gestorSensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Método que carga la lista de sensores del dispositivo
        cargarSensores();

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarSensores();
                Toast.makeText(MainActivity.this, "Lista actualizada", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cargarSensores() {
        List<Sensor> lista = gestorSensores.getSensorList(Sensor.TYPE_ALL);
        List<String> nombresSensores = new ArrayList<>();

        for(Sensor sensor : lista) {
            nombresSensores.add(sensor.getName());
        }

        ArrayAdapter<String> adatador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                nombresSensores
        );

        listaSensores.setAdapter(adatador);
    }
}