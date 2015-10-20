import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX {
	public Map<String, String> file2() {
		final Map<String, String> map1 = new HashMap<String, String>();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser1 = null;
		try {
			saxParser1 = factory.newSAXParser();
		} catch (ParserConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SAXException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DefaultHandler handler1 = new DefaultHandler() {

			public void startElement(String uri, String localName, String row,
					Attributes attributes) throws SAXException {

				if ("row".equals(row)) {

					String id = attributes.getValue("Id");
					String name = attributes.getValue("DisplayName");
					map1.put(id, name);

				}

			}

			public void endElement(String uri, String localName, String qName)
					throws SAXException {

			}
			public void endDocument()
			{
				
			}

		};
		try {
			saxParser1.parse("/Users/uditi_000/Downloads/users.xml", handler1);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		return map1;

	}

}
