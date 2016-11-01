package com.andorid.mblacher.safemode;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreateUsuarios = "CREATE TABLE Usuarios (mail TEXT, password TEXT)";
    String sqlCreateMarcas = "CREATE TABLE Marcas (id INTEGER, marca TEXT, descripcion TEXT)";
    String sqlCreateModelos = "CREATE TABLE Modelos (id INTEGER, modelo TEXT, precio INTEGER)";

    public SQLiteHelper(Context contexto, String nombre,
                        CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateUsuarios);
        db.execSQL(sqlCreateMarcas);
        db.execSQL(sqlCreateModelos);

        db.execSQL("INSERT INTO Marcas (id, marca, descripcion) VALUES (0, 'Apple', 'Texto')");
        db.execSQL("INSERT INTO Marcas (id, marca, descripcion) VALUES (1, 'Motorola', 'Texto')");
        db.execSQL("INSERT INTO Marcas (id, marca, descripcion) VALUES (2, 'Samsung', 'Texto')");
        db.execSQL("INSERT INTO Marcas (id, marca, descripcion) VALUES (3, 'Sony', 'Texto')");
        db.execSQL("INSERT INTO Marcas (id, marca, descripcion) VALUES (4, 'Nokia', 'Texto')");

        db.execSQL("INSERT INTO Modelos (id, modelo, precio) VALUES (0, 'iPhone6', 10000)");
        db.execSQL("INSERT INTO Modelos (id, modelo, precio) VALUES (1, '4ta Generacion', 6000)");
        db.execSQL("INSERT INTO Modelos (id, modelo, precio) VALUES (2, 'S7 Edge', 9000)");
        db.execSQL("INSERT INTO Modelos (id, modelo, precio) VALUES (3, 'M5', 4000)");
        db.execSQL("INSERT INTO Modelos (id, modelo, precio) VALUES (4, 'Lumia', 5000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior,
                          int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
        //      la opción de eliminar la tabla anterior y crearla de nuevo
        //      vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la
        //      tabla antigua a la nueva, por lo que este método debería
        //      ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS Marcas");
        db.execSQL("DROP TABLE IF EXISTS Modelos");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateUsuarios);
        db.execSQL(sqlCreateMarcas);
        db.execSQL(sqlCreateModelos);
    }


}

