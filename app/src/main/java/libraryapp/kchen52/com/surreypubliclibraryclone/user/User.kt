package libraryapp.kchen52.com.surreypubliclibraryclone.user

import libraryapp.kchen52.com.surreypubliclibraryclone.XMLUtil
import libraryapp.kchen52.com.surreypubliclibraryclone.book.Book
import org.w3c.dom.Document
import org.w3c.dom.Text
import java.io.Serializable

// Given the user information (in the form of XML sadly) parse and extract the information
class User(userInformation: String) {
    // TODO: Convert the following to JSON
    val informationAsXML : Document = XMLUtil.convertStringToDocument(userInformation)
    val firstName : String = (informationAsXML.getElementsByTagName("FirstName").item(0).firstChild as Text).wholeText
    val lastName : String = (informationAsXML.getElementsByTagName("LastName").item(0).firstChild as Text).wholeText
    val borrowerId : String = (informationAsXML.getElementsByTagName("BorrowerId").item(0).firstChild as Text).wholeText
    val borrowerType : String = (informationAsXML.getElementsByTagName("BorrowerType").item(0).firstChild as Text).wholeText
    val phoneNumber : String = (informationAsXML.getElementsByTagName("PhoneNumber").item(0).firstChild as Text).wholeText
    val expiryDate : String = (informationAsXML.getElementsByTagName("ExpiryDate").item(0).firstChild as Text).wholeText
    val retrieveRecentlyReturned : String = (informationAsXML.getElementsByTagName("RetrieveRecentlyReturned").item(0).firstChild as Text).wholeText
    val birthDate : String = (informationAsXML.getElementsByTagName("BirthDate").item(0).firstChild as Text).wholeText
    val displayName : String = (informationAsXML.getElementsByTagName("DisplayName").item(0).firstChild as Text).wholeText
    val firstPreferredBranchCode : String = (informationAsXML.getElementsByTagName("FirstPreferredBranchCode").item(0).firstChild as Text).wholeText
    val freeHoldRequests : String = (informationAsXML.getElementsByTagName("FreeHoldRequests").item(0).firstChild as Text).wholeText
    val remainingFreeHolds : String = (informationAsXML.getElementsByTagName("RemainingFreeHolds").item(0).firstChild as Text).wholeText
    val barcode : String = (informationAsXML.getElementsByTagName("Barcode").item(0).firstChild as Text).wholeText
    val email : String = (informationAsXML.getElementsByTagName("Email").item(0).firstChild as Text).wholeText
    val sessionId : String = (informationAsXML.getElementsByTagName("SessionId").item(0).firstChild as Text).wholeText
    val bcId : String = (informationAsXML.getElementsByTagName("BcId").item(0).firstChild as Text).wholeText

    var checkedOutBooks : ArrayList<Book> = ArrayList()

    override fun toString() : String {
        return "firstName: $firstName\n" +
            "lastName: $lastName\n" +
            "borrowerId: $borrowerId\n" +
            "borrowerType: $borrowerType\n" +
            "phoneNumber: $phoneNumber\n" +
            "expiryDate: $expiryDate\n" +
            "retrieveRecentlyReturned: $retrieveRecentlyReturned\n" +
            "birthDate: $birthDate\n" +
            "displayName: $displayName\n" +
            "firstPreferredBranchCode: $firstPreferredBranchCode\n" +
            "freeHoldRequests: $freeHoldRequests\n" +
            "remainingFreeHolds: $remainingFreeHolds\n" +
            "barcode: $barcode\n" +
            "email: $email\n" +
            "sessionId: $sessionId\n" +
            "bcId: $bcId"
    }
}
