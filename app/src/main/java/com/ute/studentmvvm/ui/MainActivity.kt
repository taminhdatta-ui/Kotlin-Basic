package com.ute.studentmvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ute.studentmvvm.R
import com.ute.studentmvvm.data.AppDatabase
import com.ute.studentmvvm.repository.StudentRepository
import com.ute.studentmvvm.viewmodel.StudentViewModelFactory

class MainActivity : AppCompatActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { StudentRepository(database.studentDao()) }

    /**
     * Đặt Factory mặc định cho Activity. Các Fragment gọi activityViewModels()
     * sẽ dùng chính Factory này nên luôn nhận được StudentViewModel kèm Repository.
     *
     * (Nếu chỉ khai báo `by viewModels { factory }` mà không truy cập tới nó, biến lazy
     * sẽ không chạy, và activityViewModels() trong Fragment sẽ tạo ViewModel bằng
     * Factory mặc định -> văng lỗi "Cannot create an instance of StudentViewModel".)
     */
    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = StudentViewModelFactory(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, StudentListFragment())
                .commit()
        }
    }
}
