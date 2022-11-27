package mk.ukim.finki.lab_intents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ImplicitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

        val pkgAppsList = packageManager.queryIntentActivities(mainIntent, 0)
        var activityNames = arrayOf<String>()
        for (i in pkgAppsList.indices) {
            activityNames += pkgAppsList[i].activityInfo.name
        }

        val arrayAdapter: ArrayAdapter<*>

        var mListView = findViewById<ListView>(R.id.list_item)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, activityNames)
        mListView.adapter = arrayAdapter
    }
}