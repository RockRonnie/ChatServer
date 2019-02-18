//TopChatter counts the sent messages by each user and makes a sorted top 4 list based on who has sent the most.
object TopChatter : ChatHistoryObserver {

    private val topChatter = mutableMapOf<String, Int>()

    override fun newMessage(message: ChatMessage) {
        val username = message.user
        if (topChatter.containsKey(username)) {
            var value = topChatter.getValue(username)
            value += 1
            topChatter.set(username, value)
        } else {
            topChatter.put(username, 1)
        }
        printTopChatters()
    }

    private fun printTopChatters() {
        val sorted = topChatter.toList().sortedByDescending { (_, value) -> value }
        sorted.take(4).forEach { n -> println(n) }
    }
}