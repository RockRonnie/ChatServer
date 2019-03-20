import java.net.ServerSocket
/* ChatServer app
Created by: Roni Jumpponen */

//ChatServer accepts connections, runs the ci and creates new threads for each instance
class ChatServer {

    fun serve() {
        //Uses a random serversocket (0)
        val ss = ServerSocket(60000)
        ChatHistory.register(ChatConsole)
        ChatHistory.register(TopChatter)
        println("Our webaddress is " + ss.inetAddress.hostAddress + " on port " + ss.localPort)

        while (true) {
            val s = ss.accept()
            println("We have connection " + s.inetAddress.hostAddress + " " + s.port)
            val ci = CommandInterpreter(s)
            ChatHistory.register(ci)
            val t = Thread(ci)
            t.start()
        }
    }
}

//Observer and observable interfaces
interface ChatHistoryObserver {
    fun newMessage(message: ChatMessage)
}

interface ChatHistoryObservable {
    fun notifyObservers(message: ChatMessage)
    fun register(who: ChatHistoryObserver)
    fun deregister(who: ChatHistoryObserver)
}

fun main(args: Array<String>) {
    val chadServer = ChatServer()
    chadServer.serve()
}
