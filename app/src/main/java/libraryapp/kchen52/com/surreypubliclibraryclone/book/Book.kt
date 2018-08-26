package libraryapp.kchen52.com.surreypubliclibraryclone.book

import android.os.Parcel
import android.os.Parcelable
import libraryapp.kchen52.com.surreypubliclibraryclone.XMLUtil
import org.json.JSONObject
import org.w3c.dom.Document
import org.w3c.dom.Text

/*
Example book entry in xml:
<Bib>
        <Title>Dogfight</Title>
        <SubTitle>How Apple and Google Went to War and Started A Revolution</SubTitle>
        <LastPublicationDate>2013-02-01 00:00:00.000 -0500</LastPublicationDate>
        <Authors>
            <String>Vogelstein, Fred</String>
        </Authors>
        <Notes>
            <String>Includes bibliographical references, Internet addresses and index</String>
        </Notes>
        <Pages>260</Pages>
        <ISBNs>
            <String>9780670067190</String>
        </ISBNs>
        <PhysicalDescription>
            <String>260 pages</String>
        </PhysicalDescription>
        <StatementOfResponsibility>Fred Vogelstein</StatementOfResponsibility>
        <PrimaryLanguage>eng</PrimaryLanguage>
        <Audiences>
            <Audience>ADULT</Audience>
        </Audiences>
        <FictionType>NONFICTION</FictionType>
        <LibraryBibId>574697</LibraryBibId>
        <Description>This book documents the battle between Apple and Google, and the ways they have reshaped our world and redefined the distribution of technology and content. Beginning with the iPhone, Vogelstein traces the history of mobile communications over the past fifteen years, explores how the battle between Google and Apple is being fought today, and speculates as to who will ultimately control the content of our devices in the future.</Description>
        <Format>BK</Format>
        <BcId>574697071</BcId>
    </Bib>
 */
class Book(bookInformation : JSONObject) {
    val status = bookInformation.optString("Status", "???")
    // Trim the following since the time of day is irrelevant most of the time (:drumstick:)
    val dueDate = bookInformation.optString("DueDate", "1970-01-01").substring(0, 10)
    val timesRenewed = bookInformation.optString("TimesRenewed", "0")

    private var bib = bookInformation.optJSONObject("Bib")
    val description = bib.optString("Description", "Description not provided")
    val bcId = bib.optString("BcId", "-1")
    val lastPublicationDate = bib.optString("LastPublicationDate", "1970-01-01")
    val libraryBibId = bib.optString("LibraryBibId", "-1")
    val subtitle = bib.optString("SubTitle", "Subtitle not provided")
    val title = bib.optString("Title", "Title not provided(?!)")
    val edition = bib.optString("Edition", "1")
    val pages = bib.optString("Pages", "1")
    val fictionType = bib.optString("FictionType", "NONFICTION")
    val itemsCount = bib.optString("ItemsCount", "1")
    //val author = bib.optJSONObject("Authors").optString("String", "John Doe")

    // There appears to be some inconsistencies with getting the ISBN... handle its initialization in the init block
    /*lateinit var isbn : String

    init {
        var isbnObjects = bib.optJSONObject("ISBNs")
        try {
            isbn = (isbnObjects.optJSONArray("String").get(0) as JSONObject).optString("content", "404")
        } catch (e: NullPointerException) {
            continue
        }
        val isbn = (bib.optJSONObject("ISBNs").optJSONArray("String").get(0) as JSONObject).optString("content", "404")

    }*/
}
