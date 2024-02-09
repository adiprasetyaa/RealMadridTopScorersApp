package com.example.myviewandviewgroup.myconstraintview.myrecycleview.realmadridtopscorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailPlayerActivity : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        val imgDetail :ImageView = findViewById(R.id.img_detail_photo)
        val tvDetailName : TextView = findViewById(R.id.tv_detail_name)
        val tvDetailGoals : TextView = findViewById(R.id.tv_detail_goals)
        val tvDetailTotalMatches: TextView = findViewById(R.id.tv_detail_total_matches)
        val tvDetailYears: TextView = findViewById(R.id.tv_detail_total_year)
        val tvDetailDescription : TextView = findViewById(R.id.tv_detail_description)

        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)

        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<Player>("DATA")
        if (data != null) {
            tvDetailName.text = data.name
            tvDetailGoals.text = data.total_goals
            tvDetailTotalMatches.text = data.total_matches
            tvDetailYears.text = data.years
            tvDetailDescription.text = data.description
            imgDetail.setImageResource(data.photo)
        }
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.action_share ->{
                @Suppress("DEPRECATION")
                val data = intent.getParcelableExtra<Player>("DATA")
                if(data != null) {
                    val text = "Player Name: ${data.name}\n" +
                            "Total Goals: ${data.total_goals}\n" +
                            "Total Matches: ${data.total_matches}\n" +
                            "Total Years: ${data.years}\n" +
                            "Description: ${data.description}"

                    val share = Intent(Intent.ACTION_SEND)
                    share.type = "text/plain"
                    share.putExtra(Intent.EXTRA_TEXT, text)
                    startActivity(Intent.createChooser(share, "Share via"))

                }
            }
        }
    }
}