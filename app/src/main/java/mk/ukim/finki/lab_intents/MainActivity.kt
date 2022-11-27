package mk.ukim.finki.lab_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var txtField:TextView
    private lateinit var btnExplicitActivity:Button
    private lateinit var btnImplicitActivity:Button
    private lateinit var btnSend:Button
    private lateinit var btnSelectPhoto:Button

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            txtField.text = data?.getStringExtra("result")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txtField = findViewById(R.id.txtField)
        btnExplicitActivity = findViewById(R.id.btnExplicit)
        btnImplicitActivity = findViewById(R.id.btnImplicit)
        btnSend = findViewById(R.id.btnSend)
        btnSelectPhoto = findViewById(R.id.btnSelectPhoto)

        btnExplicitActivity.setOnClickListener {
            Intent(this, ExplicitActivity::class.java).let { i ->
                i.putExtra("result", txtField.text.toString())
                resultLauncher.launch(i)
            }
        }

        btnImplicitActivity.setOnClickListener{ _ ->
            Intent().apply {
                action = "mk.ukim.finki.lab_intents.IMPLICIT_ACTION"
                type = "text/plain"
            }.let { intent ->
                startActivity(Intent.createChooser(intent,"Choose the app for your intent"))
            }
        }

        btnSend.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND).let { emailIntent ->
                emailIntent.type = "text/plain"
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity")
                startActivity(emailIntent)
            }
        }

        btnSelectPhoto.setOnClickListener {
            val photoIntent = Intent(Intent.ACTION_SEND).let { photoIntent ->
                photoIntent.type = "image/jpg"
                startActivity(photoIntent)
            }
        }
    }
}