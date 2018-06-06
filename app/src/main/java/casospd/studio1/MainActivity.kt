package casospd.studio1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"hi i feel good",Toast.LENGTH_LONG).show()
        Bcalcular.setOnClickListener {
            val apuesta= dataText.text.toString().toInt()
            val precio= 2400
            val resultado: Int?
            textView2.text = "tu apuesta es ${apuesta + precio} en pesos."
        }
        Bnext.setOnClickListener(){
            nextActivity(this,segunda::class.java)
        }
    }
    fun nextActivity(activity: Activity, nextActivity: Class<*>){
        val intent = Intent(activity, nextActivity)
        intent.putExtra("sap_men","vamo a ver.")
        activity.startActivity(intent)
        activity.finish()
    }


}
