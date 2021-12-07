package com.mykey.roomsample.view.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mykey.roomsample.R
import com.mykey.roomsample.service.model.GuestModel
import com.mykey.roomsample.view.listener.GuestListener

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    // Lista de convidados
    private var mGuestList: List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item, mListener)
    }


    override fun getItemCount(): Int {
        return mGuestList.count()
    }


    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }


    fun updateGuests(list: List<GuestModel>) {
        mGuestList = list
        notifyDataSetChanged()
    }

    /**
     * Eventos na listagem
     */
    fun attachListener(listener: GuestListener) {
        mListener = listener
    }

    inner class GuestViewHolder(itemView: View, private val listener: GuestListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(guest: GuestModel) {

            // Obtém os elementos de interface
            val textName = itemView.findViewById<TextView>(R.id.text_name)

            // Atribui valores
            textName.text = guest.name

            // Atribui eventos
            textName.setOnClickListener {
                listener.onClick(guest.id)
            }

            // Atribui eventos
            textName.setOnLongClickListener {
                AlertDialog.Builder(itemView.context)
                    .setTitle(R.string.remocao_convidado)
                    .setMessage(R.string.deseja_remover)
                    .setPositiveButton(R.string.remover) { dialog, which ->
                        listener.onDelete(guest.id)
                    }
                    .setNeutralButton(R.string.cancelar, null)
                    .show()

                true
            }

        }
    }

}