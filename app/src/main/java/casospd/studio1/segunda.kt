package casospd.studio1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class segunda : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val textview = findViewById(R.id.ResultofMain) as TextView
        val bundle = intent.extras
        if(bundle != null && bundle.getString("sap_men") != null){
            textview.text = bundle.getString("sap_men")
        }else{
            Toast.makeText(this,"no mando el bundle",Toast.LENGTH_SHORT)
        }
    }
}
