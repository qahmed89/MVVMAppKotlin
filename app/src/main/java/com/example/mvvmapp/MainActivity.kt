package com.example.mvvmapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmapp.ui.HomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
@Inject
lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  println("DEBUG: ${ExampleSingleton.singletonUser.hashCode()}")
        (application as BaseApplication).getSharedComponent().inject(this)
        bt_api.setOnClickListener {
            if (api_edittext.text.isBlank()){
                Snackbar.make(constraint,"Enter Api KEy ",Snackbar.LENGTH_LONG).show()
            }else {
                val editor = sharedPreferences.edit()
                editor.putString("APIKEY",api_edittext.text.toString()).apply()
                editor.apply()
                val intent = Intent ( this , HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
