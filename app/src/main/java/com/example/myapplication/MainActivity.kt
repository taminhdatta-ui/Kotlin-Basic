package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etStudentId: EditText
    private lateinit var etClass: EditText
    private lateinit var btnRegister: AppCompatButton
    private lateinit var tvDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Xử lý WindowInsets cho Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        setupListeners()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etStudentId = findViewById(R.id.etStudentId)
        etClass = findViewById(R.id.etClass)
        btnRegister = findViewById(R.id.btnRegister)
        tvDetail = findViewById(R.id.tvDetail)
    }

    private fun setupListeners() {
        // Nút Đăng ký
        btnRegister.setOnClickListener {
            if (validateInputs()) {
                navigateToDetail()
            }
        }

        // Nút Xem trang chi tiết
        tvDetail.setOnClickListener {
            if (validateInputs()) {
                navigateToDetail()
            }
        }
    }

    private fun navigateToDetail() {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("EXTRA_FULL_NAME", etFullName.text.toString().trim())
            putExtra("EXTRA_EMAIL", etEmail.text.toString().trim())
            putExtra("EXTRA_PHONE", etPhone.text.toString().trim())
            putExtra("EXTRA_STUDENT_ID", etStudentId.text.toString().trim())
            putExtra("EXTRA_CLASS", etClass.text.toString().trim())
        }
        startActivity(intent)
    }

    private fun validateInputs(): Boolean {
        val fullName = etFullName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val phone = etPhone.text.toString().trim()
        val studentId = etStudentId.text.toString().trim()
        val className = etClass.text.toString().trim()

        // 1. Kiểm tra Họ và tên
        if (fullName.isEmpty()) {
            etFullName.error = "Họ và tên không được để trống"
            etFullName.requestFocus()
            return false
        }

        // 2. Kiểm tra Email
        if (email.isEmpty()) {
            etEmail.error = "Email không được để trống"
            etEmail.requestFocus()
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Email không đúng định dạng (VD: example@gmail.com)"
            etEmail.requestFocus()
            return false
        }

        // 3. Kiểm tra Số điện thoại (10 chữ số, bắt đầu bằng số 0)
        val phoneRegex = "^0\\d{9}$".toRegex()
        if (phone.isEmpty()) {
            etPhone.error = "Số điện thoại không được để trống"
            etPhone.requestFocus()
            return false
        }
        if (!phone.matches(phoneRegex)) {
            etPhone.error = "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0"
            etPhone.requestFocus()
            return false
        }

        // 4. Kiểm tra Mã sinh viên (MSV)
        if (studentId.isEmpty()) {
            etStudentId.error = "Mã sinh viên không được để trống"
            etStudentId.requestFocus()
            return false
        }

        // 5. Kiểm tra Lớp
        if (className.isEmpty()) {
            etClass.error = "Lớp không được để trống"
            etClass.requestFocus()
            return false
        }

        return true
    }
}
