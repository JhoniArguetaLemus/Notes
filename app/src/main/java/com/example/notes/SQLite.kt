package com.example.notes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.core.content.contentValuesOf

class SQLite( val context:Context):SQLiteOpenHelper(context, NAME, null , VERSION ) {
    companion object{
        private  val NAME="notas.db"
        private  val VERSION=1


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_NOTES_TABLE = """
        CREATE TABLE notas (
            id INTEGER PRIMARY KEY AUTOINCREMENT ,
            titulo TEXT,
            texto TEXT
        )
    """.trimIndent()


        val createTableCompleted="""
            CREATE TABLE completado(
            id INTEGER PRIMARY KEY AUTOINCREMENT, 
            titulo TEXT, 
            texto TEXT
            
            
            )
            
        """.trimIndent()





        db?.execSQL(CREATE_NOTES_TABLE)

        db?.execSQL(createTableCompleted)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
          val sql="DROP TABLE IF EXISTS notas"
          db!!.execSQL(sql)
    }

    fun insertNote( titulo: String, texto:String){
        val datos=ContentValues()
        datos.put("titulo", titulo)
        datos.put("texto", texto)
        val db=this.writableDatabase
        db.insert("notas", null, datos)

    }



    fun deleteNote(id: Int, titulo: String) {
        val db = this.writableDatabase
        val args = arrayOf( titulo)

        val sql = "DELETE FROM notas WHERE  titulo = ?"

        db.execSQL(sql, args)
        db.close()
    }

    fun borrarTabla(){
        val db=this.writableDatabase
        val sql="DROP TABLE IF EXISTS notas"

        db.execSQL(sql)

        Toast.makeText(context, "Tabla borrada correctamente", Toast.LENGTH_SHORT).show()


    }


    fun crearTabla(){
        val db=this.writableDatabase
        val CREATE_COMPLETADO_TABLE = """
        CREATE TABLE eliminado (
            id INTEGER PRIMARY KEY AUTOINCREMENT ,
            titulo TEXT,
            texto TEXT
        )
    """.trimIndent()


        db.execSQL(CREATE_COMPLETADO_TABLE)


    }


    fun eliminarNota(itemId:Int){
        val db=this.writableDatabase

        db.delete("notas", "id=?", arrayOf(itemId.toString()))
        db.close()
    }

    fun actualizarNota(titulo: String, texto: String, clave:String){
        val db=this.writableDatabase
        val datos=ContentValues()
        datos.put("titulo", titulo)
        datos.put("texto", texto)

        db.update("notas", datos, "titulo=?", arrayOf(clave.toString()))

    }

    fun addCompleteNote(titulo: String, texto: String){
        val db=this.writableDatabase

        val datos=ContentValues()
        datos.put("titulo", titulo)
        datos.put("texto", texto)
        db.insert("completado", null, datos)
        db.close()

    }

    fun addDeleteNote(titulo: String, texto: String){
        val db=this.writableDatabase

        val datos=ContentValues()
        datos.put("titulo", titulo)
        datos.put("texto", texto)
        db.insert("eliminado", null, datos)
        db.close()
    }







}