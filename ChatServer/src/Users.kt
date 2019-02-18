//Users stores the user information and makes sure there are no duplicate users.
object Users {
    private val userList = mutableSetOf<String>()
    fun insert(user: String) {
        userList.add(user)
    }

    fun remove(user: String) {
        userList.remove(user)
    }

    fun userexists(user: String): Boolean {
        return userList.add(user)
    }

    override fun toString(): String {
        return userList.joinToString()
    }
}