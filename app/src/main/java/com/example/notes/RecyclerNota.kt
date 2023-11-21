package com.example.notes

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerNota(private val context: Context, private val list:List<Nota>):RecyclerView.Adapter<RecyclerNota.ViewHolder>(),
    Adapter {



    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

        val txtTitulo=itemView.findViewById<TextView>(R.id.txtTitulo)
        val txtNota=itemView.findViewById<TextView>(R.id.txtNota)
        val btnCompleted=itemView.findViewById<Button>(R.id.btnCompleted)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView=LayoutInflater.from(parent.context).inflate(R.layout.nota_model, parent, false )

        return  ViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(holder is ViewHolder){

            val nota=list[position]

            holder.txtTitulo.text=nota.titulo
            holder.txtNota.text=nota.nota






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