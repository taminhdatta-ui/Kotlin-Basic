package com.ute.studentmvvm.network

import com.google.gson.annotations.SerializedName
import com.ute.studentmvvm.model.Student

/**
 * Data Transfer Object cho JSON trả về từ REST API.
 * Bài thực hành dùng API công khai https://jsonplaceholder.typicode.com/users
 * và ánh xạ mỗi "user" thành một sinh viên.
 */
data class StudentDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String?
)

/**
 * Chuyển DTO (tầng mạng) thành Entity (tầng CSDL).
 *
 * Id cục bộ = 1000 + id từ máy chủ. Nhờ id cố định, mỗi lần đồng bộ lại,
 * OnConflictStrategy.REPLACE sẽ GHI ĐÈ bản ghi cũ thay vì tạo dòng trùng lặp,
 * và không bao giờ đụng độ với id tự tăng của sinh viên thêm thủ công.
 */
fun StudentDto.toEntity(): Student {
    val code = "21110" + id.toString().padStart(3, '0')
    return Student(
        id = 1000 + id,
        fullName = name,
        studentCode = code,
        email = email?.takeIf { it.isNotBlank() } ?: "$code@ute.udn.vn",
        gpa = 2.5 + (id % 15) / 10.0, // GPA mô phỏng trong khoảng 2.5 - 3.9
        major = "Công Nghệ Thông Tin"
    )
}
