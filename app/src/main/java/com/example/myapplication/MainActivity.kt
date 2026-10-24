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
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etStudentId: EditText
    private lateinit var etClass: EditText
    private lateinit var btnRegister: AppCompatButton
    private lateinit var tvDetail: TextView

    // Cờ theo dõi trạng thái tương tác của từng trường
    private var isFullNameTouched = false
    private var isEmailTouched = false
    private var isPhoneTouched = false
    private var isStudentIdTouched = false
    private var isClassTouched = false

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
        // Real-time validation bằng doAfterTextChanged
        etFullName.doAfterTextChanged {
            isFullNameTouched = true
            validateFullName()
        }
        etEmail.doAfterTextChanged {
            isEmailTouched = true
            validateEmail()
        }
        etPhone.doAfterTextChanged {
            isPhoneTouched = true
            validatePhone()
        }
        etStudentId.doAfterTextChanged {
            isStudentIdTouched = true
            validateStudentId()
        }
        etClass.doAfterTextChanged {
            isClassTouched = true
            validateClass()
        }

        // Nút Đăng ký
        btnRegister.setOnClickListener {
            if (validateAll()) {
                navigateToDetail()
            }
        }

        // Nút Xem trang chi tiết
        tvDetail.setOnClickListener {
            if (validateAll()) {
                navigateToDetail()
            }
        }
    }

    private fun validateFullName(): Boolean {
        val text = etFullName.text.toString().trim()
        if (text.isEmpty()) {
            if (isFullNameTouched) {
                etFullName.error = "Họ và tên không được để trống"
            } else {
                etFullName.error = null
            }
            return false
        }
        etFullName.error = null
        return true
    }

    private fun validateEmail(): Boolean {
        val text = etEmail.text.toString().trim()
        if (text.isEmpty()) {
            if (isEmailTouched) {
                etEmail.error = "Email không được để trống"
            } else {
                etEmail.error = null
            }
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            if (isEmailTouched) {
                etEmail.error = "Email không đúng định dạng (VD: example@gmail.com)"
            }
            return false
        }
        etEmail.error = null
        return true
    }

    private fun validatePhone(): Boolean {
        val text = etPhone.text.toString().trim()
        val phoneRegex = "^0\\d{9}$".toRegex()
        if (text.isEmpty()) {
            if (isPhoneTouched) {
                etPhone.error = "Số điện thoại không được để trống"
            } else {
                etPhone.error = null
            }
            return false
        }
        if (!text.matches(phoneRegex)) {
            if (isPhoneTouched) {
                etPhone.error = "Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0"
            }
            return false
        }
        etPhone.error = null
        return true
    }

    private fun validateStudentId(): Boolean {
        val text = etStudentId.text.toString().trim()
        if (text.isEmpty()) {
            if (isStudentIdTouched) {
                etStudentId.error = "Mã sinh viên không được để trống"
            } else {
                etStudentId.error = null
            }
            return false
        }
        etStudentId.error = null
        return true
    }

    private fun validateClass(): Boolean {
        val text = etClass.text.toString().trim()
        if (text.isEmpty()) {
            if (isClassTouched) {
                etClass.error = "Lớp không được để trống"
            } else {
                etClass.error = null
            }
            return false
        }
        etClass.error = null
        return true
    }

    private fun validateAll(): Boolean {
        // Đánh dấu tất cả đã được tương tác khi bấm Đăng ký
        isFullNameTouched = true
        isEmailTouched = true
        isPhoneTouched = true
        isStudentIdTouched = true
        isClassTouched = true

        val v1 = validateFullName()
        if (!v1) {
            etFullName.requestFocus()
            return false
        }
        val v2 = validateEmail()
        if (!v2) {
            etEmail.requestFocus()
            return false
        }
        val v3 = validatePhone()
        if (!v3) {
            etPhone.requestFocus()
            return false
        }
        val v4 = validateStudentId()
        if (!v4) {
            etStudentId.requestFocus()
            return false
        }
        val v5 = validateClass()
        if (!v5) {
            etClass.requestFocus()
            return false
        }
        return true
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
}
