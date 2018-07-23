package libraryapp.kchen52.com.surreypubliclibraryclone

import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

class XMLUtil {
    companion object {
        fun convertStringToDocument(xml : String) : Document {
            var factory = DocumentBuilderFactory.newInstance()
            var builder = factory.newDocumentBuilder()
            return builder.parse(InputSource(StringReader(xml)))
        }
    }
}