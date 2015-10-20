import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestOwn {

	public void MyOwnParser() {
		Scanner file1 = null;
		@SuppressWarnings("unused")
		Scanner file2;
		Map<String, Integer> mapQ = new HashMap<String, Integer>();
		Map<String, Integer> mapA = new HashMap<String, Integer>();
		try {
			file1 = new Scanner(
					new File("/Users/uditi_000/Downloads/posts.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			file2 = new Scanner(
					new File("/Users/uditi_000/Downloads/users.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (file1.hasNextLine()) {
			String line = file1.nextLine();
			Pattern postId = Pattern.compile("(?<=PostTypeId=\")(\\d+)(?=\".)");
			Pattern id = Pattern.compile("OwnerUserId+\\=\"(\\d{1,10})");
			Matcher post = postId.matcher(line);
			Matcher matchId = id.matcher(line);

			if (matchId.find()  ) {
				if (post.find()) {
					String postType = post.group(1);
					String userId = matchId.group(1);
					if (postType.equals("1")) {
						if (mapQ.containsKey(userId)) {
							mapQ.put(userId, mapQ.get(userId) + 1);
						} else {
							mapQ.put(userId, 1);
						}

					} else if (postType.equals("2")) {
						if (mapA.containsKey(userId)) {
							mapA.put(userId, mapA.get(userId) + 1);
						} else {
							mapA.put(userId, 1);
						}
					}
				}
			}
		}
		
		Map<String, String> mp = new HashMap<String, String>();
		Map<String, String> finalAnswer = new HashMap<String, String>();
		Map<String, String> finalQuestions = new HashMap<String, String>();
		List<Entry<String, Integer>> greatestQ = TopTen.findGreatest(mapQ, 10);
		List<Entry<String, Integer>> greatestA = TopTen.findGreatest(mapA, 10);
		final SAX sr = new SAX();
		mp = sr.file2();
		for (Entry<String, Integer> entry : greatestA) {
			for (Map.Entry<String, String> entry1 : mp.entrySet()) {
				if (entry1.getKey().equals(entry.getKey())) {
					finalAnswer.put(entry.getKey(), entry1.getValue() + "    "
							+ "Count of Answers Posted= "
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

	private static void printValues(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue();
			System.out.println("User Id = " + key + ":: Name =  " + "" + value);

		}

	}
}
