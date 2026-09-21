package com.ute.studentprofile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ute.studentprofile.databinding.ActivityMainBinding
import com.ute.studentprofile.model.Student
import com.ute.studentprofile.untils.toAcademicRanking
import com.ute.studentprofile.untils.toast
import android.content.Intent
import android.net.Uri
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentStudent = Student(
        id = "2415053122107",
        name = "Tạ Minh Đạt",
        className = "24T1",
        email = "tmdat@ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
// Gán dữ liệu ban đầu lên các Views
        bindStudentData(currentStudent)
        // ── Xử lý sự kiện khi người dùng bấm nút Cập Nhật ────────
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()
            if (newGpa == null || newGpa !in 0.0..4.0) {
// Báo lỗi nếu nhập sai định dạng hoặc ngoài khoảng 0.0 - 4.0
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }
            // Cập nhật sinh viên bằng hàm copy()
            currentStudent = currentStudent.copy(gpa = newGpa)
            bindStudentData(currentStudent) // Vẽ lại dữ liệu mới lên Views
            toast("Cập nhật điểm thành công!")
        }
        val btnCall = findViewById<Button>(R.id.btnCall)

        btnCall.setOnClickListener {
            val phoneNumber = "0912345678"
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} • Lớp: ${student.className}"
            tvGpaBadge.text =
                "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText (student.gpa.toString())
        }
    }
    private fun showConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("Xóa hồ sơ")
            .setMessage("Bạn có chắc chắn muốn xóa hồ sơ không?")
            .setPositiveButton("Xóa") { _, _ ->
                // Xử lý xóa hồ sơ
            }
            .setNegativeButton("Hủy", null)
            .show()
    }
}