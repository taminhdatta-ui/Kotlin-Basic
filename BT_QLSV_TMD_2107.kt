fun printDeveloperInfo(){
    println("Tac gia: Ta Minh Dat" )
    println("Ma sinh vien: 2415.053.122.107" ); println()
}
data class Student(
    val studentId: String,
    var fullName: String,
    var age: Int,
    var major: String,
    var gpa: Double
)

fun addStudent(students: MutableList<Student>) {
    val id = "SV%03d".format(students.size + 1)

    println("Student ID: $id")

    print("Enter Full Name: ")
    val name = readln()

    print("Enter Age: ")
    val age = readln().toInt()

    print("Enter Major: ")
    val major = readln()

    print("Enter GPA: ")
    val gpa = readln().toDouble()

    val student = Student(id, name, age, major, gpa)
    students.add(student)

    println("Student added successfully.")
}

fun displayAllStudents(students: List<Student>) {
    if (students.isEmpty()) {
        println("No students found.")
        return
    }

    println("\n========== ALL STUDENTS ==========")

    for (student in students) {
        println(student)
    }
}

fun searchStudent(students: List<Student>) {
    print("Enter name or Student ID to search: ")
    val keyword = readln()

    val result = students.filter {
        it.studentId.contains(keyword, ignoreCase = true) ||
        it.fullName.contains(keyword, ignoreCase = true)
    }

    if (result.isEmpty()) {
        println("No student found.")
    } else {
        println("\n========== SEARCH RESULT ==========")

        for (student in result) {
            println(student)
        }
    }
}

fun calculateAverageGPA(students: List<Student>) {
    if (students.isEmpty()) {
        println("No students found.")
        return
    }

    print("Enter major: ")
    val major = readln()

    val majorStudents = students.filter {
        it.major.equals(major, ignoreCase = true)
    }

    if (majorStudents.isEmpty()) {
        println("No students found in this major.")
        return
    }

    val averageGPA = majorStudents.map { it.gpa }.average()

    println("Average GPA of $major: %.2f".format(averageGPA))
}

fun findHighestGPA(students: List<Student>) {
    if (students.isEmpty()) {
        println("No students found.")
        return
    }

    val highestGPA = students.maxByOrNull { it.gpa }

    println("\n========== HIGHEST GPA ==========")
    println(highestGPA)
}

fun findOldestStudent(students: List<Student>) {
    if (students.isEmpty()) {
        println("No students found.")
        return
    }

    val oldestStudent = students.maxByOrNull { it.age }

    println("\n========== OLDEST STUDENT ==========")
    println(oldestStudent)
}

fun findStudentsByGPARange(students: List<Student>) {
    val result = students.filter {
        it.gpa in 7.0..8.5
    }

    if (result.isEmpty()) {
        println("No students have GPA from 7.0 to 8.5.")
        return
    }

    println("\n========== GPA FROM 7.0 TO 8.5 ==========")

    for (student in result) {
        println(student)
    }
}

fun findStudentsByMajor(students: List<Student>) {
    print("Enter major: ")
    val major = readln()

    val result = students.filter {
        it.major.equals(major, ignoreCase = true)
    }

    if (result.isEmpty()) {
        println("No students found in this major.")
        return
    }

    println("\n========== STUDENTS BY MAJOR ==========")

    for (student in result) {
        println(student)
    }
}

fun findStudentsByName(students: List<Student>) {
    print("Enter part of name: ")
    val keyword = readln()

    val result = students.filter {
        it.fullName.contains(keyword, ignoreCase = true)
    }

    if (result.isEmpty()) {
        println("No students found.")
        return
    }

    println("\n========== STUDENTS BY NAME ==========")

    for (student in result) {
        println(student)
    }
}

fun countStudentsByGPA(students: List<Student>) {
    val goodStudents = students.count { it.gpa >= 8.0 }
    val weakStudents = students.count { it.gpa < 5.0 }

    println("\n========== GPA STATISTICS ==========")
    println("Students with GPA >= 8.0: $goodStudents")
    println("Students with GPA < 5.0: $weakStudents")
}

fun sortByGPADescending(students: MutableList<Student>) {
    students.sortByDescending { it.gpa }

    println("\n========== SORT BY GPA DESCENDING ==========")

    for (student in students) {
        println(student)
    }
}

fun displayTop3Students(students: List<Student>) {
    if (students.isEmpty()) {
        println("No students found.")
        return
    }

    val top3 = students.sortedByDescending { it.gpa }.take(3)

    println("\n========== TOP 3 GPA ==========")

    for (student in top3) {
        println(student)
    }
}

fun sortByAge(students: MutableList<Student>) {
    students.sortBy { it.age }

    println("\n========== SORT BY AGE ==========")

    for (student in students) {
        println(student)
    }
}

fun sortByName(students: MutableList<Student>) {
    students.sortBy { it.fullName }

    println("\n========== SORT BY NAME ==========")

    for (student in students) {
        println(student)
    }
}

fun removeStudent(students: MutableList<Student>) {
    print("Enter Student ID to remove: ")
    val id = readln()

    val student = students.find {
        it.studentId == id
    }

    if (student == null) {
        println("Student not found.")
    } else {
        students.remove(student)
        println("Student removed successfully.")
    }
}

fun main() {

    val students = mutableListOf(
        Student("SV001", "Nguyen Minh Anh", 20, "Information Technology", 8.7),
        Student("SV002", "Tran Hoang Nam", 21, "Computer Science", 7.5),
        Student("SV003", "Le Gia Bao", 22, "Information Technology", 9.1),
        Student("SV004", "Pham Thu Ha", 20, "Business Administration", 4.6),
        Student("SV005", "Vo Minh Khang", 23, "Information Technology", 6.8)
    )

    var choice: Int

    do {
        println()
        println("========== STUDENT MANAGEMENT ==========")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("7. Count students by GPA")
        println("8. Find oldest student")
        println("9. Find students with GPA 7.0 - 8.5")
        println("10. Find students by major")
        println("11. Find students by part of name")
        println("12. Sort students by GPA descending")
        println("13. Display top 3 students")
        println("14. Sort students by age")
        println("15. Sort students by name")
        println("16. Display author information")
        println("0. Exit")
        println("========================================")
        print("Choose: ")

        choice = readln().toInt()

        when (choice) {
            1 -> addStudent(students)

            2 -> displayAllStudents(students)

            3 -> searchStudent(students)

            4 -> calculateAverageGPA(students)

            5 -> findHighestGPA(students)

            6 -> removeStudent(students)

            7 -> countStudentsByGPA(students)

            8 -> findOldestStudent(students)

            9 -> findStudentsByGPARange(students)

            10 -> findStudentsByMajor(students)

            11 -> findStudentsByName(students)

            12 -> sortByGPADescending(students)

            13 -> displayTop3Students(students)

            14 -> sortByAge(students)

            15 -> sortByName(students)

            16 -> printDeveloperInfo()
            
            0 -> println("Program ended.")

            else -> println("Invalid choice. Please choose again.")
        }

    } while (choice != 0)
}