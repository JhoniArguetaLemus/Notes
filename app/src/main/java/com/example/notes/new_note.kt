package com.example.notes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


/**
 * A simple [Fragment] subclass.
 * Use the [new_note.newInstance] factory method to
 * create an instance of this fragment.
 */
class new_note : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_new_note, container, false)
        val edtText=view.findViewById<EditText>(R.id.edtNote)
        val edtTitulo=view.findViewById<EditText>(R.id.edtTitulo)


        view.findViewById<Button>(R.id.btnSave)
            .setOnClickListener{

               try{

                   if(edtTitulo.text.toString().isNullOrEmpty()){
                       edtTitulo.error="Debe rellenar este campo"

                   }else{

                       val sqlite=SQLite(requireContext())
                       sqlite.insertNote(edtTitulo.text.toString(),edtText.text.toString())

                       Toast.makeText(requireContext(), "Nota ingresada correctamente", Toast.LENGTH_SHORT).show()
                       edtTitulo.text.clear()
                       edtText.text.clear()
                   }

                }catch (e:Exception){

                    Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                }
               // val sqLite=SQLite(requireContext())
               // sqLite.crearTabla()

            }

        return view
    }





}