package com.example.noteappcomplete.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappcomplete.Moduls.Note
import com.example.noteappcomplete.R
import kotlin.coroutines.coroutineContext
import kotlin.random.Random


class NotesAdapter (private val context: Context,val listener: NotesClickedListener): RecyclerView.Adapter <NotesAdapter.NoteViewHoldder>(){

    private val NoteList = ArrayList <Note> ()
    private val fullList = ArrayList <Note> ()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHoldder {
       return NoteViewHoldder(
           LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
       )
    }


    override fun onBindViewHolder(holder: NoteViewHoldder, position: Int) {
      val currentNote = NoteList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected =true

        holder.note.text = currentNote.note
        holder.date.text = currentNote.date
        holder.title.isSelected =true


        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(NoteList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(NoteList[holder.adapterPosition],holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return NoteList.size
    }

    fun updateList(newList : List <Note>){
        fullList.clear()
        fullList.addAll(newList)

        NoteList.clear()
        NoteList.addAll(fullList)

        notifyDataSetChanged()
    }

     fun filterList(search : String){
         NoteList.clear()
         for(item in fullList){
             if(item.title?.lowercase()?.contains(search.lowercase())==true ||
                     item.note?.lowercase()?.contains(search.lowercase()) == true) {

                 NoteList.add(item)
             }
         }

         notifyDataSetChanged()
     }


    fun randomColor() : Int{
      val list =ArrayList<Int>()
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list [randomIndex]
    }

    inner class NoteViewHoldder(itemView: View): RecyclerView.ViewHolder(itemView){

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)


    }

    interface NotesClickedListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }



}