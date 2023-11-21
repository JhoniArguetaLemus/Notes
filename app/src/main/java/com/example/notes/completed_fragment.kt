package com.example.notes

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class completed_fragment : Fragment() {
    lateinit var adapter:Adapter
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_completed_fragment, container, false)

        val recycler=view.findViewById<RecyclerView>(R.id.recycler_completado)
        recycler.layoutManager=LinearLayoutManager(requireContext())

        try {

            val sqLite=SQLite(requireContext())

            val notaList=obtenerDatos()

            adapter=RecyclerCompletado(requireContext(), notaList)

            recycler.adapter= adapter as RecyclerCompletado

            if(notaList.size ==0){
                val alertDialog=AlertDialog.Builder(requireContext())
                    .setMessage("No hay notas completadas por mostrar")
                    .setPositiveButton("Ok"){dialog, it->}
                    .setCancelable(true)
                    .show()
            }


        }catch (e:Exception){
            val alertDialog=AlertDialog.Builder(requireContext())
                .setMessage(e.message)
                .setTitle("Error")
                .setPositiveButton("Aceptar"){dialog, it->}
                .show()
        }




        return  view
    }



    private fun obtenerDatos():List<Nota> {

        val sqLite=SQLite(requireContext())
        val notas= mutableListOf<Nota>()
        val db=sqLite.readableDatabase

        val cursor: Cursor =db.rawQuery("SELECT * FROM completado", null)

        while(cursor.moveToNext()){
            val titulo=cursor.getString(1)
            val nota=cursor.getString(2)

            notas.add(Nota( titulo, nota))

        }

        cursor.close()

        db.close()

        return  notas


    }


}