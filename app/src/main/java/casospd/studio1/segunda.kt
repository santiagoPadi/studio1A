package casospd.studio1

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_segunda.*

class segunda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        //regresar 2
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val textview = findViewById(R.id.ResultofMain) as TextView
        val bundle = intent.extras
        if(bundle != null && bundle.getString("sap_men") != null){
            textview.text = bundle.getString("sap_men")
        }else{
            Toast.makeText(this,"no mando el bundle",Toast.LENGTH_SHORT)
        }
        BTerceraActividad.setOnClickListener{
            startActivity(this,TerceraActivity:: class.java)
        }
        BBack.setOnClickListener(){
            startActivity(this, MainActivity:: class.java)
        }
    }
    fun startActivity(activity: Activity, nextActivity: Class<*>){
        val intent = Intent(activity, nextActivity)
        activity.startActivity(intent)
        activity.finish()

    }
}
