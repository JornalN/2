fun main(args: Array<String>) {
    var taskQuestion = true
    while (taskQuestion) {
        println("------------------------------------")
        println("Введите номер задачи (0 для завершения)")
        print("> ")
        var taskNumber: String = readln()
        println("------------------------------------")
        when (taskNumber.trim()) {
            "0" -> break
            "1" -> task1()
            "2" -> task2()
            else -> println("Неверный номер задачи")
        }
    }
}

fun task1() {
    print("Введите имя пользователя: ")
    val user_Name = readln()
    print("Введите количество секунд: ")
    val sec_Count = readln().toInt()
    println("$user_Name был(а) в сети "+agoToText(sec_Count))
}

var b: Boolean = true

fun task2() {
    print("Введите тип карты/счёта(MasterCard/Maestro, Visa/Мир, VK Pay): ")
    val card_Type = readln()
    print("Введите сумму предыдущих переводов в этом месяце(если нет введите 0): ")
    val amount_Previous = readln().toDouble()
    print("Введите сумму совершаемого перевода: ")
    val amount_Current = readln().toDouble()
    val commision = com(amount_Current,amount_Previous,card_Type.trim().lowercase())
    if(b) println("Комиссия составляет "+commision+" руб.")
    else println("Ошибка")
}

fun agoToText(a:Int): String{
    when (a){
        in 0..60 -> return "только что"
        in 61 .. 60*60 -> return minToText(a/60)
        in 60*60+1 .. 24*60*60-1 -> return hourToText(a/(60*60))
        in 24*60*60..2*24*60*60-1 -> return "вчера"
        in 2*24*60*60..3*24*60*60-1 -> return "позавчера"
        else -> return "давно"
    }
}

fun minToText(a: Int): String{
    when (a){
        in 11..14 -> return "$a минут назад"
        else -> when (a%10){
            1 -> return "$a минуту назад"
            in 2..4 -> return "$a минуты назад"
            else -> return "$a минут назад"
        }
    }

}

fun hourToText(a:Int): String{
    when (a){
        in 11..14 -> return "$a часов назад"
        else ->when (a%10){
            1 -> return "$a час назад"
            in 2..4 -> return "$a часа назад"
            else -> return "$a часов назад"
        }
    }
}

fun com(a_c:Double, a_p:Double, t:String):Double {
    when (t){
        "mastercard", "maestro" -> if(a_c + a_p > 75000.0) return (a_c*0.06)+20.0 else return 0.0
        "visa", "мир" -> if(a_c > 35.0) return (a_c*0.0075) else b = false
        "vk pay" -> if(a_c < 15000.0 && a_c+a_p < 40000.0) return 0.0
        else -> b = false
    }
    b = false
    return 0.0
}