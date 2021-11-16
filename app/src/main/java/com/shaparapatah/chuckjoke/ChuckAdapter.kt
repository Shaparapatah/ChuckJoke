package com.shaparapatah.chuckjoke

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChuckAdapter : RecyclerView.Adapter<ChuckAdapter.ChuckViewHolder>() {

    private var chuckData: List<OneJoke> = listOf()
    fun setJokes(data: List<ServerResponse>) {
        chuckData = data[0].value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChuckAdapter.ChuckViewHolder {
        val holder = ChuckViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false)
        )
        return holder
    }

    override fun onBindViewHolder(holder: ChuckAdapter.ChuckViewHolder, position: Int) {
        holder.render(chuckData[position])
    }

    override fun getItemCount() = chuckData.size


    inner class ChuckViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun render(oneJoke: OneJoke) {
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                oneJoke.joke
        }
    }
}
