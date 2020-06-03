import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Scanner;

public class SAXReader {
    public static void read(){

        System.out.println("Enter GraphML filename");
        Scanner scan = new Scanner(System.in);

        String filename=scan.nextLine();

        try {
            XMLReader processor = XMLReaderFactory.createXMLReader();
            processor.setContentHandler(new SAXController());
            processor.parse(new InputSource(filename));
        } catch (SAXException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
