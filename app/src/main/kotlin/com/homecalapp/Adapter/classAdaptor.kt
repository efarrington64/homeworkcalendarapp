package com.homecalapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.homecalapp.Model.ClassModel
import com.homecalapp.app.R

class classAdaptor(private val context: Context, private var classList: List<ClassModel>):
RecyclerView.Adapter<classAdaptor.ClassViewHolder>()
{



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
       val view =LayoutInflater.from(parent.context).inflate(R.layout.each_row, parent,false)
        return ClassViewHolder(view)
    }

    override fun getItemCount(): Int {
        return classList.size
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {

        val classlist = classList[position]
        holder.classitems.setImageResource(classlist.image)
        holder.image_description.setText(classlist.name)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, ""+classlist.name, Toast.LENGTH_SHORT).show()
        }
    }

    class ClassViewHolder(ItemView:View) :RecyclerView.ViewHolder(ItemView) {
     val classitems = itemView.findViewById<ImageView>(R.id.classitems)
        val image_description = itemView.findViewById<TextView>(R.id.image_description)
    }

}
