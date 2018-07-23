package libraryapp.kchen52.com.surreypubliclibraryclone.fragment


object MyFragmentManager {
    private lateinit var userFragment: UserFragment
    private lateinit var catalogFragment: CatalogFragment
    private lateinit var loginFragment: LoginFragment

    fun getUserFragment() : UserFragment {
        if (!this::userFragment.isInitialized) {
            userFragment = UserFragment()
        }
        return userFragment
    }

    fun getCatalogFragment() : CatalogFragment {
        if (!this::catalogFragment.isInitialized) {
            catalogFragment = CatalogFragment()
        }
        return catalogFragment
    }

    fun getLoginFragment() : LoginFragment {
        if (!this::loginFragment.isInitialized) {
            loginFragment = LoginFragment()
        }
        return loginFragment
    }
}