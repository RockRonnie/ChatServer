import java.io.PrintStream
import java.net.Socket
import java.util.*

//CommandInterpreter handles inputs coming from the socket and has most of the user functionalities
class CommandInterpreter(s: Socket) : Runnable, ChatHistoryObserver {

    private val reader = Scanner(s.getInputStream())
    private val printit = PrintStream(s.getOutputStream())
    private var newusername = ""
    private var oldusername = ""
    private val commandlist = listOf("!user <name>", "!users", "!commands", "!history")

    //observer method
    override fun newMessage(message: ChatMessage) {
        printit.println(message)
    }

    override fun run() {
        //Try phase starts from here. so if someone closes their chatwindow without quitting first this causes an exception.
        try {
            printit.println("Welcome to the chat server of your dreams! Everything is allowed here because nothing works!")
            do {
                val commandline = reader.nextLine()
                //changing username
                if (commandline.startsWith("!user ")) {
                    newusername = commandline.substringAfter("!user ")
                    if (newusername != oldusername) {
                        //Checks if username is available
                        if (Users.userexists(newusername)) {
                            Users.remove(oldusername)
                            printit.println("Your username has been changed to " + newusername)
                            Users.insert(newusername)
                            oldusername = newusername
                        } else {
                            printit.println("Invalid username. That username is already in use")
                        }
                    }
                    //If you are already using that exact username
                    else {
                        printit.println("Your username is already " + oldusername)
                    }
                    //userlist
                } else if (commandline.startsWith("!users")) {
                    printit.println("List of current users")
                    printit.println(Users)
                    //list of available commands
                } else if (commandline.startsWith("!commands")) {
                    printit.println("List of commands")
                    commandlist.forEach { m: String -> printit.println(m) }
                    //oommand for returning the chathistory
                } else if (commandline.startsWith("!history")) {
                    ChatHistory.history.forEach { printit.println(it) }
                    //Check for empty commandline
                } else if (commandline.isBlank()) {
                    printit.println("Empty lines wont go through. If you need help on how to chat refer to !commands")
                    //and finally everything else is registered as a chat message
                } else {
                    if (oldusername != "") {
                        val message = ChatMessage(commandline, oldusername)
                        ChatHistory.insert(message)
                    } else
                        printit.println("Please pick a username by using-> !user <name> , command!")
                }
            } while (commandline != "!Quit")
            //Try ending and catch beginning.
        } catch (e: Exception) {
            println(oldusername + " has fallen")
        } finally {
            Users.remove(oldusername)
            reader.close()
            printit.close()
        }
    }

}
