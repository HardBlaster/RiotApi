package hu.unideb.riotapi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import okhttp3.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var context: Context? = null
    private val apiKey = "RGAPI-5635151f-c5ec-4e47-a704-ed56dbf1828f"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        getDataButton.setOnClickListener {

            val summonerName = summonerNameIn.text.toString()
            val url =
                "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + summonerName + "?api_key=" + apiKey
            val request = Request.Builder().url(url).build()

            val receiver = OkHttpClient()
            receiver.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {

                    Toast.makeText(context, "Something went wrong...", Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call, response: Response) {

                    val data = response.body!!.string()

                    val gson = GsonBuilder().create()
                    val summoner: Summoner = gson.fromJson(data, Summoner::class.java)

                    Log.i("LEHET", data)

                    runOnUiThread {

                        ID.text = "ID:\n" + summoner.id
                        accountID.text = "Account ID:\n" + summoner.accountId
                        puuID.text = "PUUID:\n" + summoner.puuid
                        name.text = "Name:\n" + summoner.name
                        iconID.text = "Icon ID:\n" + summoner.profileIconId.toString()
                        revisionDate.text = "Revision Date:\n" + summoner.revisionDate.toString()
                        level.text = "Level:\n" + summoner.summonerLevel.toString()

                    }
                }
            })
        }
    }
}
