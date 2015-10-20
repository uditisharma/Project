import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		int choice = 0;
		@SuppressWarnings("resource")
		Scanner sr = new Scanner(System.in);
		while (choice == (int) choice) {
			System.out
					.println("Enter 1 for DOM \n Enter 2 for SAX \n Enter 3 for NewParser");
			try {
				choice = sr.nextInt();
			} catch (Exception e) {
				System.out.println("Please enter integer value");
				continue;
			}
			break;
		}

		switch (choice) {
		case 1:
			DOM d = new DOM();
			d.DomParser();

		case 2:
			SAX2 s = new SAX2();
			s.file2();

		case 3:
			TestOwn t = new TestOwn();
			t.MyOwnParser();

		}
	}

}
