package libraryapp.kchen52.com.surreypubliclibraryclone.user

object UserManager {
    private lateinit var user : User

    fun setUser(newUser : User) {
        user = newUser
    }

    fun getUser() : User {
        return this.user
    }

    fun isUserAuthenticated() : Boolean {
        // TODO: Perform checks to see that the tokens haven't expired yet
        return this::user.isInitialized
    }
}