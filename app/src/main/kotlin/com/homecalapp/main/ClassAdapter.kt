package com.homecalapp.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.homecalapp.app.R
import com.homecalapp.toast

class ClassAdapter(private val context: Context, private var classList: List<ClassModel>) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder =
        ClassViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_main_class, parent, false)
        )

    override fun getItemCount(): Int = classList.size

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classlist = classList[position]
        holder.classitems.setImageResource(classlist.image)
        holder.image_description.text = classlist.name
        holder.itemView.setOnClickListener {
            context.toast(classlist.name)
        }
    }

    class ClassViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val classitems = itemView.findViewById<ImageView>(R.id.classitems)
        val image_description = itemView.findViewById<TextView>(R.id.image_description)
    }
}
