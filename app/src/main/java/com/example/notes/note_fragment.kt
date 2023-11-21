package com.example.notes

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class note_fragment : Fragment() {

    lateinit var adapter: Adapter
    lateinit var sqLite:SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =inflater.inflate(R.layout.fragment_note_fragment, container, false)

        var recycler=view.findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager=LinearLayoutManager(requireContext())

        try {
            sqLite = SQLite(requireContext())

            val notaList = obtenerDatos()
            adapter = com.example.notes.RecyclerView(requireContext(), notaList)

            recycler.adapter = adapter as com.example.notes.RecyclerView

            if(notaList.size ==0){
                val alertDialog=AlertDialog.Builder(requireContext())
                    .setMessage("No hay notas por mostrar")
                    .setPositiveButton("Ok"){dialog, it->}
                    .setCancelable(true)
                    .show()
            }

        }catch (e:Exception){
           val alertDialog=AlertDialog.Builder(requireContext())
               .setMessage(e.message)
               .setTitle("Error")
               .setPositiveButton("Ok"){dialog, it->
               }
               .show()
        }







        return view
    }

    private fun obtenerDatos():List<Nota> {


        val notas= mutableListOf<Nota>()
        val db=sqLite.readableDatabase

        val cursor:Cursor=db.rawQuery("SELECT * FROM notas", null)

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