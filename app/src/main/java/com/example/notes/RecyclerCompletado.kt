package com.example.notes

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class RecyclerCompletado(val context: Context, private  val list:List<Nota>):RecyclerView.Adapter<RecyclerCompletado.ViewHolder>(),
    Adapter {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val txtTitulo=itemView.findViewById<TextView>(R.id.txtTitulo)
        val txtNota=itemView.findViewById<TextView>(R.id.txtNota)
        val btnEliminar=itemView.findViewById<Button>(R.id.btnEliminarC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.model_completado, parent,  false)

        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        if(holder is ViewHolder){

            val nota=list[position]

            holder.txtNota.text=nota.nota
            holder.txtTitulo.text=nota.titulo

            holder.btnEliminar.setOnClickListener{


                try{

                    val sqlite=SQLite(context)

                    val db=sqlite.readableDatabase

                    var titulo=nota.titulo

                    db.delete("completado", "titulo=?", arrayOf(titulo.toString()))

                    sqlite.addDeleteNote(nota.titulo, nota.nota)

                    db.close()



                }catch (e:Exception){
                    val alertDialog= AlertDialog.Builder(context)
                        .setMessage(e.message)
                        .setTitle("Error")
                        .setPositiveButton("Ok"){dialog, it->}
                        .setCancelable(true)
                        .show()
                }


            }


        }





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