const val PASSING_SCORE = 5.0

fun printDeveloperInfo(){
    println("Tac gia: Ta Minh Dat" )
    println("Ma sinh vien: 2415.053.122.107" ); println()
}

data class Score(
    val math: Double,
    val programming: Double,
    val database: Double
)

fun inputScore(): Score {
    print("Math: ")
    val math = readln().toDouble()

    print("Programming: ")
    val programming = readln().toDouble()

    print("Database: ")
    val database = readln().toDouble()

    return Score(math, programming, database)
}

fun sum(score: Score): Double {
    return score.math + score.programming + score.database
}

fun average(score: Score): Double{
    return sum(score) / 3
}

fun maxScore(score: Score): Double {
    return maxOf(score.math, score.programming, score.database)
}

fun checkGPA(avg: Double){
    if(avg >= PASSING_SCORE)
        println("4. GPA: PASS")
    else
        println("4. GPA: FAIL")
}

fun displayResult(score: Score) {
    val total = sum(score)
    val avg = average(score)
    val max = maxScore(score)

    println("1. Tong diem: $total")
    println("2. Diem trung binh: %.2f".format(avg))
    println("3. Diem cao nhat: $max")
    checkGPA(avg)
}

fun main(){

    println("Nhap diem cua sinh vien:")
    val score = inputScore(); println()

    println("Ket qua cua sinh vien:")
    displayResult(score); println()

    printDeveloperInfo()
}
