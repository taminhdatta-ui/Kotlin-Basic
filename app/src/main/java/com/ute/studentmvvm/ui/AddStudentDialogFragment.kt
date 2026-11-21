package com.ute.studentmvvm.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.ute.studentmvvm.databinding.DialogAddStudentBinding
import com.ute.studentmvvm.model.Student
import com.ute.studentmvvm.viewmodel.StudentViewModel

class AddStudentDialogFragment : DialogFragment() {

    private val viewModel: StudentViewModel by activityViewModels()

    private var studentToEdit: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val id = it.getInt(ARG_ID, -1)
            if (id != -1) {
                studentToEdit = Student(
                    id = id,
                    fullName = it.getString(ARG_NAME, ""),
                    studentCode = it.getString(ARG_CODE, ""),
                    email = it.getString(ARG_EMAIL, ""),
                    gpa = it.getDouble(ARG_GPA, 0.0),
                    major = it.getString(ARG_MAJOR, "Công Nghệ Thông Tin")
                )
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogAddStudentBinding.inflate(LayoutInflater.from(requireContext()))

        val isEdit = studentToEdit != null
        val title = if (isEdit) "Cập Nhật Sinh Viên" else "Thêm Sinh Viên Mới"
        val positiveButtonText = if (isEdit) "Cập nhật" else "Lưu vào Room"

        if (isEdit) {
            studentToEdit?.let {
                binding.etFullName.setText(it.fullName)
                binding.etStudentCode.setText(it.studentCode)
                binding.etEmail.setText(it.email)
                binding.etGpa.setText(it.gpa.toString())
            }
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setView(binding.root)
            .setPositiveButton(positiveButtonText) { _, _ ->
                val name = binding.etFullName.text.toString().trim()
                val code = binding.etStudentCode.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val gpa = binding.etGpa.text.toString().trim().replace(',', '.')
                    .toDoubleOrNull()?.coerceIn(0.0, 4.0) ?: 0.0

                if (name.isEmpty() || code.isEmpty()) {
                    Toast.makeText(requireContext(), "Họ tên và MSSV không được để trống!", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                if (isEdit) {
                    val updatedStudent = studentToEdit!!.copy(
                        fullName = name,
                        studentCode = code,
                        email = if (email.isNotEmpty()) email else "${code.lowercase()}@ute.udn.vn",
                        gpa = gpa
                    )
                    viewModel.update(updatedStudent)
                } else {
                    val student = Student(
                        fullName = name,
                        studentCode = code,
                        email = if (email.isNotEmpty()) email else "${code.lowercase()}@ute.udn.vn",
                        gpa = gpa
                    )
                    viewModel.insert(student)
                }
            }
            .setNegativeButton("Hủy", null)
            .create()
    }

    companion object {
        private const val ARG_ID = "arg_id"
        private const val ARG_NAME = "arg_name"
        private const val ARG_CODE = "arg_code"
        private const val ARG_EMAIL = "arg_email"
        private const val ARG_GPA = "arg_gpa"
        private const val ARG_MAJOR = "arg_major"

        fun newInstance(student: Student? = null): AddStudentDialogFragment {
            return AddStudentDialogFragment().apply {
                student?.let {
                    arguments = Bundle().apply {
                        putInt(ARG_ID, it.id)
                        putString(ARG_NAME, it.fullName)
                        putString(ARG_CODE, it.studentCode)
                        putString(ARG_EMAIL, it.email)
                        putDouble(ARG_GPA, it.gpa)
                        putString(ARG_MAJOR, it.major)
                    }
                }
            }
        }
    }
}
