package libraryapp.kchen52.com.surreypubliclibraryclone.exception

class LoginFailedException(exceptionMessage : String) : Exception() {
    override var message = exceptionMessage
}