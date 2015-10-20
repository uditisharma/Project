import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOM {
	void DomParser() {
		File userxml = new File("/Users/uditi_000/Downloads/users.xml");
		File postsxml = new File("/Users/uditi_000/Downloads/posts.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc1 = null;
		Document doc2 = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> test = new HashMap<String, Integer>();
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {

			e1.printStackTrace();
		}
		try {
			doc1 = dBuilder.parse(userxml);
			doc2 = dBuilder.parse(postsxml);

		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		doc1.getDocumentElement().normalize();
		Element root = doc1.getDocumentElement();
		doc2.getDocumentElement().normalize();
		NodeList nodeList = root.getChildNodes();
		NodeList nodeList1 = doc2.getElementsByTagName("row");

		for (int count = 0; count < nodeList1.getLength(); count++) {
			Node tempNode = nodeList1.item(count);
			Element e = (Element) tempNode;
			String owner1 = e.getAttribute("OwnerUserId");
			String post = e.getAttribute("PostTypeId");
			try {
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
			} catch (Exception ex) {
				System.out.println("Null");
			}

		}
		Map<String, String> finalAnswer = new HashMap<String, String>();
		Map<String, String> finalQuestions = new HashMap<String, String>();
		List<Entry<String, Integer>> greatestQ = TopTen.findGreatest(map, 10);
		List<Entry<String, Integer>> greatestA = TopTen.findGreatest(test, 10);
		for (Entry<String, Integer> entry : greatestA) {

			for (int count = 0; count < nodeList.getLength(); count++) {
				Node tempNode = nodeList.item(count);
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) tempNode;
					String dpname = e.getAttribute("DisplayName");
					String dpid = e.getAttribute("Id");
					if (entry.getKey().equals(dpid)) {
						finalAnswer.put(entry.getKey(),
								dpname + "    " + "Count of Answers Posted= "
										+ entry.getValue() + "\n");

					}
				}

			}

		}
		for (Entry<String, Integer> entry : greatestQ) {

			for (int count = 0; count < nodeList.getLength(); count++) {
				Node tempNode = nodeList.item(count);
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) tempNode;
					String dpname = e.getAttribute("DisplayName");
					String dpid = e.getAttribute("Id");
					if (entry.getKey().equals(dpid)) {
						finalQuestions.put(entry.getKey(),
								dpname + "    " + "Count of Questions Posted= "
										+ entry.getValue() + "\n");
					}

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

	private static void printValues(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			System.out.println("User Id = " + key + ":: Name =  " + "" + value);

		}

	}
}
