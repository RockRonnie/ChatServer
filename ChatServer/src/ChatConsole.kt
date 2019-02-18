//acts as the observer for following the chat on IDE
object ChatConsole : ChatHistoryObserver {
    override fun newMessage(message: ChatMessage) {
        println(message)
    }
}