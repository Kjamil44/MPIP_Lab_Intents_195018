package mk.ukim.finki.lab_intents

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ExplicitActivity : AppCompatActivity() {
    private lateinit var btnConfirm:Button
    private lateinit var btnClose:Button
    private lateinit var input_info:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        input_info = findViewById(R.id.input_info)
        btnConfirm = findViewById(R.id.btnConfirm)
        btnClose = findViewById(R.id.btnClose)

        btnConfirm.setOnClickListener{ _ ->
            Intent().let { intent ->
                intent.putExtra("result",input_info.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

        btnClose.setOnClickListener{ _ ->
            Intent().let { intent ->
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

    }
}