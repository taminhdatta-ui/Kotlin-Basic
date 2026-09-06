fun printDeveloperInfo(){
    println("Tac gia: Ta Minh Dat" )
    println("Ma sinh vien: 2415.053.122.107" ); println()
}

fun printNumber1To10(){
    for (i in 1..10)
        print("$i ")
}

fun sum1To100(): Int{
    var sum = 0
    for (i in 1..100)
        sum += i
    return sum
}


fun eventNumber1To20_UseFor(){
    for (i in 1..20){
        if(i % 2 == 0)
            print("$i ")
    }
}

fun eventNumber1To20_UseWhile(){
    var i = 2
    while (i <= 20){
        print("$i ")
        i += 2
    }
}

fun displayResult(){
    println("Bai1: In cac so tu 1 den 10:")
    printNumber1To10(); println("\n")
    println("Bai2: Tinh tong cua cac so tu 1 den 100 la: ${sum1To100()}"); println()
    println("Bai3.C1: In cac so chan tu 1 den 20 su dung vong lap for:")
    eventNumber1To20_UseFor(); println("\n")
    println("Bai3.C2: In cac so chan tu 1 den 20 su dung vong lap while:")
    eventNumber1To20_UseWhile(); println("\n")
}

fun main(){
    displayResult()
    printDeveloperInfo()
}