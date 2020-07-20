package com.example.nocuscountries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class CountryRecyclerAdapter(private val context: Context,
                             private val countries: ArrayList<CountryInfo>) :
    RecyclerView.Adapter<CountryRecyclerAdapter.ViewHolder>(){
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.country_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryInfo = countries[position]
        holder.countryNameText?.text = countryInfo.name
        holder.briefDetailsText?.text = "${countryInfo.alpha2Code}\t|\tPopulation: ${countryInfo.population}"
        holder.countryName = countryInfo.name
        setFlagImg(holder, countryInfo.alpha2Code)
    }

    fun setFlagImg(holder: ViewHolder, alpha2Code: String){

        /*
         * examples:
         * "https://www.countryflags.io/be/flat/64.png"
         * "https://www.countryflags.io/be/shiny/64.png"
         *
         * template:
         * "https://www.countryflags.io/:country_code/:style/:size.png"
         */

        Picasso
            .get()
            .load("https://www.countryflags.io/$alpha2Code/shiny/64.png")
            .placeholder(R.drawable.ic_baseline_emoji_flags_24)
            .error(R.drawable.ic_baseline_emoji_flags_24)
            .into(holder.flagImgView)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var countryName = ""
        init {
            itemView?.setOnClickListener {
                Snackbar.make(it, countryName, Snackbar.LENGTH_LONG).show()
            }
        }

        val countryNameText = itemView?.findViewById<TextView?>(R.id.countryNameText)
        val briefDetailsText = itemView?.findViewById<TextView?>(R.id.briefDetailsText)
        val flagImgView = itemView?.findViewById<ImageView?>(R.id.imageView)
    }
}