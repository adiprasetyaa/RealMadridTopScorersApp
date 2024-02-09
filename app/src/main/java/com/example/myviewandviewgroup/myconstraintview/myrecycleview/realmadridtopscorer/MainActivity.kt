package com.example.myviewandviewgroup.myconstraintview.myrecycleview.realmadridtopscorer

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvPlayers : RecyclerView
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlayers = findViewById(R.id.rv_players)
        rvPlayers.setHasFixedSize(true)

        list.addAll(getListPlayers())
        showRecyclerList()

    }

    private fun getListPlayers():ArrayList<Player>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataGoals = resources.getStringArray(R.array.data_goals)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataMatches = resources.getStringArray(R.array.data_matches)
        val dataYears = resources.getStringArray(R.array.data_years)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listPlayer = ArrayList<Player>()
        for(i in dataName.indices){
            val player = Player(dataName[i], dataGoals[i],dataMatches[i],dataYears[i], dataPhoto.getResourceId(i,-1), dataDescription[i])
            listPlayer.add(player)
        }
        return listPlayer
    }

    private fun showRecyclerList(){
        rvPlayers.layoutManager = LinearLayoutManager(this)
        val listPlayerAdapter = ListPlayerAdapter(list)
        rvPlayers.adapter = listPlayerAdapter

        listPlayerAdapter.setOnItemClickCallback(object :ListPlayerAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Player) {
                val intentToDetail = Intent(this@MainActivity, DetailPlayerActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list->{
                rvPlayers.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid->{
                if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    rvPlayers.layoutManager = GridLayoutManager(this, 2)
                } else{
                    Toast.makeText(this, "Sorry, this feature only available in Landscape Mode!", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.action_about->{
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}