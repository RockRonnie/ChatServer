import java.time.LocalDateTime

//Puts together the chatmessage with all the necessary information in it.
class ChatMessage(private val text: String, val user: String) {
    private val time = LocalDateTime.now()
    override fun toString(): String {
        return user + " sent " + time + " : " + text
    }
}