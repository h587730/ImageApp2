package com.rrtutors.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rrtutors.Person
import com.rrtutors.R
import org.jetbrains.anko.doAsync

class PersonAdapter(private val persons: List<Person>) : RecyclerView.Adapter<PersonAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = persons[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int {
        return persons.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val ivPic: ImageView = itemView.findViewById(R.id.ivImg)
        private val fabDelete: FloatingActionButton = itemView.findViewById(R.id.fabDelete)


        fun bind(person: Person) {
            tvName.text = person.name
            ivPic.setImageBitmap(person.image)

            fabDelete.setOnClickListener{
                doAsync {

                }
            }


        }
    }
}