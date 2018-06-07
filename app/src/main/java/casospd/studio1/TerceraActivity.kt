package casospd.studio1

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tercera.*

class TerceraActivity : AppCompatActivity() {
    private val PHONE_CODE=1234
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercera)
        //boton de la llamada
        Bllamada!!.setOnClickListener(object  : View.OnClickListener{
            override fun onClick(v: View?) {
            val phoneN = TPhoneNumber!!.text.toString()
                if(!phoneN.isEmpty()){
                    //verificar version antes o despues de marshmallow
                    if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                        //comprobar permiso
                        if(CheckPermisson(android.Manifest.permission.CALL_PHONE)){
                            // esta en el manifest!
                            val intentlisto = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+PHONE_CODE))
                            if(ActivityCompat.checkSelfPermission(this@TerceraActivity, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                                return
                            }
                            startActivity(intentlisto)
                        }else{
                            //preguntar el permiso
                            if(!shouldShowRequestPermissionRationale(android.Manifest.permission.CALL_PHONE)){
                                requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE),PHONE_CODE)
                            }else{
                                //si ya lo nego lo dirigimos a ajustes
                                Toast.makeText(this@TerceraActivity, "Habilitar permiso",Toast.LENGTH_SHORT)
                                val intentSettings = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                                intentSettings.addCategory(Intent.CATEGORY_DEFAULT)
                                intentSettings.data = Uri.parse("package:" + packageName)
                                intentSettings.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK))
                                intentSettings.addFlags((Intent.FLAG_ACTIVITY_NO_HISTORY))
                                intentSettings.addFlags((Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS))
                                startActivity(intentSettings)
                            }
                        }
                    }else { versionAntigua(phoneN)}
                } else{
                    Toast.makeText(this@TerceraActivity, "debes marcar un numero valido",Toast.LENGTH_SHORT)
                }
            }
            fun versionAntigua(phoneArithmo : String){
                val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneArithmo))
                if(CheckPermisson(android.Manifest.permission.CALL_PHONE)){
                    if(ActivityCompat.checkSelfPermission(this@TerceraActivity, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                        return
                    }
                    startActivity(intentCall)
                }
            }
        })
        // open web
        Bweb!!.setOnClickListener{
            val url = TWebPage.text.toString()
            val intentWeb = Intent()
            intentWeb.action = Intent.ACTION_VIEW
            intentWeb.data = Uri.parse("http://"+url)
        }
        Bmail!!.setOnClickListener(){
            val email ="santipachon4@gmail.com"
            val intentMail =Intent(Intent.ACTION_SEND, Uri.parse(email))
            intentMail.type = "plain/text"
            intentMail.putExtra(Intent.EXTRA_SUBJECT, "mensaje app 1")
            intentMail.putExtra(Intent.EXTRA_TEXT, "Mensaje de prueba de la aplicacion 1")
            intentMail.putExtra(Intent.EXTRA_EMAIL, arrayOf("santipachon4@gmail.com","pachonalberto@gmail.com","padi@alunideas.com"))
            startActivity(Intent.createChooser(intentMail,"elige el cliente de correo"))
        }
        //Llamada sin permisos
        Bllamada2!!.setOnClickListener(){
            val intentCall = Intent(Intent.ACTION_DIAL, Uri.parse("te:3213167575"))
            startActivity(intentCall)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            PHONE_CODE ->{
                val permisos =permissions[0]
                val resultado =grantResults[0]
                if(permisos == android.Manifest.permission.CALL_PHONE){
                    //Comprobar si ha sido aceptado o denegado la petición de permiso
                    if(resultado == PackageManager.PERMISSION_GRANTED){
                        //consedio permiso
                        val phoneNumber = TPhoneNumber!!.text.toString()
                        val intentCall = Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ phoneNumber))
                        //verficar explicitamente que exista el permiso en el manifest
                        //ya que se puede rechazar esta petición
                        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                            return
                        }
                            startActivity(intentCall)
                    }else {
                        Toast.makeText(this,"No tiene permisos para llamar", Toast.LENGTH_LONG)
                    }

                }
            }else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        }
    }
    //verificar el permiso
    fun CheckPermisson(permission: String):Boolean{
        val result = this.checkCallingOrSelfPermission(permission)
        return  result == PackageManager.PERMISSION_GRANTED
    }
}
