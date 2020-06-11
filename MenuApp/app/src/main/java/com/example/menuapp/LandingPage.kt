package com.example.menuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_landing_page.*

class LandingPage : AppCompatActivity() {
    lateinit var providers : List<AuthUI.IdpConfig>
    val MY_REQUEST_CODE: Int = 7117
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        var btn_sign_out = findViewById<View>(R.id.btn_sign_out) as Button
        var cw = findViewById<View>(R.id.card_view)
        var cw2 = findViewById<View>(R.id.cw2)
        var cw3 = findViewById<View>(R.id.cw3)
        var cw4 = findViewById<View>(R.id.cw4)

        cw.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,CovidStatus::class.java)
            startActivity(intent)
        })

        cw2.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,DoDont::class.java)
            startActivity(intent)
        })
        cw3.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Guidelines::class.java)
            startActivity(intent)
        })
        cw4.setOnClickListener(View.OnClickListener{
            val intent = Intent(this,Helpline::class.java)
            startActivity(intent)
        })

        btn_sign_out.setOnClickListener {
            AuthUI.getInstance().signOut(this@LandingPage)
                .addOnCompleteListener {
                    btn_sign_out.isEnabled=false
                    showSignInOptions()
                }
                .addOnFailureListener {
                        e-> Toast.makeText(this@LandingPage,e.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
    private fun showSignInOptions() {
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(),MY_REQUEST_CODE)
    }
}
