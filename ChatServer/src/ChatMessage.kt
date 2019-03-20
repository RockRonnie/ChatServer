import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
/* ChatServer app
Created by: Roni Jumpponen */


//Puts together the chatmessage with all the necessary information in it.
class ChatMessage(private val text: String, val user: String) {
    private val time = LocalDateTime.now()
    var format = DateTimeFormatter.ofPattern("HH:mm")
    var formattime = time.format(format)
    override fun toString(): String {
        return "$formattime ‹$user› $text"
    }
}