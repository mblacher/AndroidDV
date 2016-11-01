package com.andorid.mblacher.safemode;

/**
 * Created by mblacher on 19-Oct-16.
 */


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends AppCompatActivity {

    public Button btnReg;
    public EditText txtUser;
    public EditText txtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        btnReg  = (Button) findViewById(R.id.btnReg);
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
        String User = txtUser.getText().toString();
        String Password = txtPassword.getText().toString();

                if(User.equals("") || Password.equals("")){
                    Toast toastUsuarioNull = Toast.makeText(getApplicationContext(), "Debe ingresar un usuario y/o contraseña.", Toast.LENGTH_SHORT);
                    toastUsuarioNull.show();
                }
                else{
                    SQLiteHelper usdbh = new SQLiteHelper(RegActivity.this, "DBUsuarios", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    //Creo el contenedor de datos
                    ContentValues Usuario = new ContentValues();

                    //Busco el ultimo Id de la tabla.
                   // Cursor cursor = db.rawQuery("SELECT seq FROM sqlite_sequence WHERE name=?", new String[]{"Usuarios"});
                    Usuario.put("mail", User);
                    Usuario.put("password", Password);



                    try {
                        //Insertamos el registro en la base de datos
                        db.insert("Usuarios", null, Usuario);
                        Intent loginIntent = new Intent(RegActivity.this, LoginActivity.class);
                        RegActivity.this.startActivity(loginIntent);


                    } catch (Exception ex) {
                        Toast toastErrorInsert = Toast.makeText(getApplicationContext(), "Error al insertar usuario: " + ex, Toast.LENGTH_SHORT);
                        toastErrorInsert.show();
                    }

                    //Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                    //startActivity(intent);
                }

            }
        });


    }
}