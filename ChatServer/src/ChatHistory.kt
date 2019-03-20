/* ChatServer app
Created by: Roni Jumpponen */

//ChatHistory acts as a hub for storing chat information and is also responsible for publishing it to users
object ChatHistory : ChatHistoryObservable {
    val history = mutableListOf<ChatMessage>()
    private val observers = mutableListOf<ChatHistoryObserver>()

    fun insert(message: ChatMessage) {
        history.add(message)
        notifyObservers(message)
    }

    override fun notifyObservers(message: ChatMessage) {
        observers.forEach { it.newMessage(message) }
    }

    override fun toString(): String {
        return history.joinToString()
    }

    override fun register(who: ChatHistoryObserver) {
        observers.add(who)
    }

    override fun deregister(who: ChatHistoryObserver) {
        observers.remove(who)
    }
}