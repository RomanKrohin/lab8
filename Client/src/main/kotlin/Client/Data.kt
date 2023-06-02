package Client

data class Data(private var login: String, private var lang: String) {
    fun getLogin(): String {
        return login
    }

    fun getLang(): String {
        return lang
    }

    fun setLogin(workLogin: String) {
        login = workLogin
    }

    fun setLang(workLang: String) {
        lang = workLang
    }
}