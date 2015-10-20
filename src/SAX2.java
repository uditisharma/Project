import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX2 {
	void file2() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser2 = null;

		final SAX sr = new SAX();
		try {
			saxParser2 = factory.newSAXParser();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultHandler handler2 = new DefaultHandler() {

			List<Entry<String, Integer>> greatestA = null;
			Map<String, Integer> map = new HashMap<String, Integer>();
			Map<String, Integer> test = new HashMap<String, Integer>();
			List<Entry<String, Integer>> greatestQ = null;
			Map<String, String> finalAnswer = new HashMap<String, String>();
			Map<String, String> finalQuestions = new HashMap<String, String>();
			Map<String, String> mp = new HashMap<String, String>();

			public void startElement(String uri, String localName, String row,
					Attributes attributes) throws SAXException {
				if (row.equals("row") ) {

					String post = attributes.getValue("PostTypeId");
					String owner1 = attributes.getValue("OwnerUserId");
					
						if (owner1 != "") {
							if (post.equals("1")) {
								if (map.containsKey(owner1)) {

									map.put(owner1, map.get(owner1) + 1);
								} else {
									map.put(owner1, 1);
								}
							}

							else if (post.equals("2")) {
								if (test.containsKey(owner1))

									test.put(owner1, test.get(owner1) + 1);

							} else {
								test.put(owner1, 1);
							}

						}
					
				}
				
			}

			public void endDocument() {
				mp = sr.file2();
				greatestQ = TopTen.findGreatest(map, 10);
				greatestA = TopTen.findGreatest(test, 10);
				
				for (Entry<String, Integer> entry : greatestA) {
					for (Map.Entry<String, String> entry1 : mp.entrySet()) {
						if (entry1.getKey().equals(entry.getKey())) {
							finalAnswer.put(entry.getKey(), entry1.getValue()
									+ "    " + "Count of Answers Posted= "
									+ entry.getValue().toString() + "\n");

						}
					}

				}
				

			 for (Entry<String, Integer> entry : greatestQ) {
					 for (Map.Entry<String, String> entry1 : mp.entrySet()) {
							if (entry1.getKey().equals(entry.getKey())) {
								finalQuestions.put(entry.getKey(), entry1.getValue()
										+ "    " + "Count of Questions Posted= "
										+ entry.getValue().toString() + "\n");

							}
						}
				 
				 }
				
				
				
				
				printValues(finalQuestions);
				System.out
						.println("------------Top Users for questions posted complete---------\n");
				printValues(finalAnswer);
				System.out
						.println("------------Top Users for answers posted complete---------\n");
			}
			

		};
		try {
			saxParser2.parse("/Users/uditi_000/Downloads/posts.xml", handler2);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}private static void printValues(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			System.out.println("User Id = " + key + ":: Name =  " + "" + value);

		}
}
}
