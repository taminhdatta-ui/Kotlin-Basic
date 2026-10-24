package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Nhận dữ liệu từ Intent
        val fullName = intent.getStringExtra("EXTRA_FULL_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: ""
        val studentId = intent.getStringExtra("EXTRA_STUDENT_ID") ?: ""
        val className = intent.getStringExtra("EXTRA_CLASS") ?: ""

        // Hiển thị lên UI
        findViewById<TextView>(R.id.tvFullName).text = fullName
        findViewById<TextView>(R.id.tvEmail).text = email
        findViewById<TextView>(R.id.tvPhone).text = phone
        findViewById<TextView>(R.id.tvStudentId).text = studentId
        findViewById<TextView>(R.id.tvClass).text = className

        // Nút quay lại màn hình Đăng ký
        findViewById<AppCompatButton>(R.id.btnBack).setOnClickListener {
            finish() // Quay lại Activity trước đó (MainActivity)
        }
    }
}
