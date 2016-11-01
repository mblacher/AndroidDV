package com.andorid.mblacher.safemode;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static com.andorid.mblacher.safemode.R.id.imageView;

public class MainActivity extends AppCompatActivity {

    public Spinner cmbMarcas;
    public Spinner cmbModelos;
    public TextView lblMensaje;
    public String varimg;
    public TextView txtBienvenida;
    public Button btnBajaUser;
    public Button btnModUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        lblMensaje = (TextView) findViewById(R.id.textView7);
        txtBienvenida = (TextView) findViewById(R.id.txtSaludoUser);
        btnBajaUser = (Button) findViewById(R.id.bajaUser);
        btnModUser = (Button) findViewById(R.id.ModifcarUser);

        SharedPreferences shared = getSharedPreferences("UsuarioPreferencia", Context.MODE_PRIVATE);
        txtBienvenida.setText("BIENVENIDO, " + shared.getString("mail", "mail").toUpperCase());

        cmbMarcas = (Spinner) findViewById(R.id.cmbMarcas);
        ArrayAdapter<CharSequence> cmbMarcasAdapter = ArrayAdapter.createFromResource(this, R.array.marcas, android.R.layout.simple_spinner_item);


        cmbModelos = (Spinner) findViewById(R.id.cmbModelo);
        ArrayAdapter<CharSequence> cmbModelosAdapter = ArrayAdapter.createFromResource(this, R.array.modelos, android.R.layout.simple_spinner_item);

        cmbMarcasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbMarcas.setAdapter(cmbMarcasAdapter);

        cmbModelosAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbModelos.setAdapter(cmbModelosAdapter);

        cmbMarcas.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        lblMensaje.setText("Seleccionado: " + parent.getItemAtPosition(position));
                        varimg = parent.getItemAtPosition(position).toString();

                    }


                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText("Seleccione un producto");
                    }
                });

        btnModUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                SQLiteHelper usdbh = new SQLiteHelper(MainActivity.this, "DBUsuarios", null, 1);
                SQLiteDatabase db = usdbh.getWritableDatabase();
                ContentValues valores = new ContentValues();

                valores.put("password", "1234");
                String[] args;
                args = new String[ ]{"mb" + ""};
                db.update("Usuarios", valores, "mail=?", args);
             }

        });
    }

}

