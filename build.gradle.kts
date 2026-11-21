// Top-level build file: khai báo phiên bản plugin cho toàn bộ project.
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    // KSP phải khớp phiên bản Kotlin (1.9.24)
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
}
