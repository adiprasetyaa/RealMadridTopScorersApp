package com.example.myviewandviewgroup.myconstraintview.myrecycleview.realmadridtopscorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPlayerAdapter(private val listPlayer: ArrayList<Player>): RecyclerView.Adapter<ListPlayerAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvGoals: TextView = itemView.findViewById(R.id.tv_item_goals)
        val tvMatches: TextView = itemView.findViewById(R.id.tv_item_matches)
        val tvYears: TextView = itemView.findViewById(R.id.tv_item_years)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_player, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, total_goals, total_matches, years, photo) = listPlayer[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvGoals.text = total_goals
        holder.tvMatches.text = total_matches
        holder.tvYears.text = years

        holder.itemView.setOnClickListener{ onItemClickCallback.onItemClicked(listPlayer[holder.adapterPosition])}
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: Player)
    }

    override fun getItemCount(): Int = listPlayer.size
}