package casospd.studio1

import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_tercera.*

class TerceraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercera)
        Bllamada.setOnClickListener(object  : View.OnClickListener{
            override fun onClick(v: View?) {
            val phoneN = TPhoneNumber!!.text.toString()
                if(!phoneN.isEmpty()){
                    if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){

                    }
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    fun CheckPermisson(permission: String):Boolean{
        val result = this.checkCallingOrSelfPermission(permission)
        return  result == PackageManager.PERMISSION_GRANTED
    }
}
