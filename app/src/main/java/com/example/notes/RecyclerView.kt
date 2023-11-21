package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class RecyclerView(val context: Context, private val notaList: List<Nota>): RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Adapter {


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         val txtTitulo=itemView.findViewById<TextView>(R.id.txtTitulo)
        val txtNota=itemView.findViewById<TextView>(R.id.txtNota)
        val btnEliminar=itemView.findViewById<Button>(R.id.btnEliminarC)
        val btnEditar=itemView.findViewById<Button>(R.id.btnEditar)

        val btnCompletado=itemView.findViewById<Button>(R.id.btnCompleted)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_model, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ViewHolder) {
            val nota = notaList[position]


            holder.txtTitulo.text = nota.titulo
            holder.txtNota.text = nota.nota


            //boton para eliminar
            holder.btnEliminar.setOnClickListener{

                try{

                    val alertDialog=AlertDialog.Builder(context)
                        .setMessage("Alerta")
                        .setTitle("Error")





                    val sqlite=SQLite(context)

                    val db=sqlite.readableDatabase

                    var titulo=nota.titulo
                    db.delete("notas", "titulo=?", arrayOf(titulo.toString()))

                    sqlite.addDeleteNote(nota.titulo, nota.nota)

                    db.close()



                }catch (e:Exception){
                    val alertDialog=AlertDialog.Builder(context)
                        .setMessage(e.message)
                        .setTitle("Error")
                        .setPositiveButton("Ok"){dialog, it->}
                        .setCancelable(true)
                        .show()
                }


            }


            //boton para editar

            holder.btnEditar.setOnClickListener{

                var tituloAnterior=nota.titulo
                val inflater=LayoutInflater.from(context)
                val dialogView=inflater.inflate(R.layout.alert_model, null)

                var builder = AlertDialog.Builder(context)

                builder.setView(dialogView)

                val alertDialog=builder.create()
                alertDialog.show()

                dialogView.findViewById<ImageButton>(R.id.btnCancelar)
                    .setOnClickListener{
                        alertDialog.dismiss()
                    }

                dialogView.findViewById<Button>(R.id.btnActualizar)
                    .setOnClickListener{

                        val edtTAc=dialogView.findViewById<EditText>(R.id.edt_new_title)
                        val edtTextoNuevo=dialogView.findViewById<EditText>(R.id.edtTextoNuevo)

                        if(edtTAc.text.toString().isNotEmpty() && edtTextoNuevo.text.toString().isNotEmpty()){
                            val sqLite=SQLite(context)

                            sqLite.actualizarNota(edtTAc.text.toString(), edtTextoNuevo.text.toString(), tituloAnterior)

                            alertDialog.dismiss()
                        }else{
                            edtTAc.error="Rellene este campo"
                            edtTextoNuevo.error="Rellene este campo"

                        }


                    }

            }


            //boton completado

            holder.btnCompletado.setOnClickListener{

                try{

                    val sqLite=SQLite(context)
                    sqLite.addCompleteNote(nota.titulo, nota.nota)
                    Toast.makeText(context, "Nota completada", Toast.LENGTH_LONG).show()

                    val db=sqLite.writableDatabase

                    db.delete("notas", "titulo=?", arrayOf(nota.titulo))


                }catch (e:Exception){
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }


            }






        }
    }

    override fun getItemCount(): Int {
       return  notaList.size
    }


    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }


    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }
}