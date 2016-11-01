package com.andorid.mblacher.safemode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public Button btnIngresar;
    public EditText txtUser;
    public EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btnIngresar  = (Button) findViewById(R.id.btnLogin);
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                String User = txtUser.getText().toString();
                String Password = txtPassword.getText().toString();

                if (User.equals("") || Password.equals("")) {
                    Toast toastUsuarioNull = Toast.makeText(getApplicationContext(), "Debe ingresar un usuario y/o contraseña.", Toast.LENGTH_SHORT);
                    toastUsuarioNull.show();
                } else {
                    SQLiteHelper usdbh = new SQLiteHelper(LoginActivity.this, "DBUsuarios", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    //Chequeo que el mail no exista
                    try {
                        String[] campos = new String[]{"mail"};
                        String[] args = new String[]{User, Password};
                        Cursor c = db.query("Usuarios", campos, "mail=? AND password=?" , args, null, null, null);


                        if (c.moveToFirst()) {

                            SharedPreferences prefs = getSharedPreferences("UsuarioPreferencia", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("mail", c.getString(0));

                            editor.commit();

                            Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
                            LoginActivity.this.startActivity(mainIntent);


                        }else{
                            Toast toastUsuarioNull = Toast.makeText(getApplicationContext(), "El usuario ingresado es inexistente.", Toast.LENGTH_SHORT);
                            toastUsuarioNull.show();
                        }
                    } catch (Exception ex) {
                        Toast toastErrorBuscar = Toast.makeText(getApplicationContext(), "Error al comprobar mail: " + ex, Toast.LENGTH_SHORT);
                        toastErrorBuscar.show();
                    }

                }
            }
        });
    }
}