package com.example.menuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class Guidelines : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guidelines)

        val linkTextView = findViewById<TextView>(R.id.textViewLink)
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        val linkTextView1 = findViewById<TextView>(R.id.textViewLink3)
        linkTextView1.setMovementMethod(LinkMovementMethod.getInstance());
        val linkTextView2 = findViewById<TextView>(R.id.textViewLink2)
        linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        val linkTextView3 = findViewById<TextView>(R.id.textViewLink1)
        linkTextView3.setMovementMethod(LinkMovementMethod.getInstance());

    }

        }
