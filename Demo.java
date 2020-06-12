import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

class Demo {

	public static void main(String[] args) {

		geheimeCodeSamenstellen code = new geheimeCodeSamenstellen();
		invoerUserCode maakUserCode = new invoerUserCode();
		controleerCode controleer = new controleerCode();

		introSpel intro = new introSpel();
		intro.toonIntro();
		String geheimeCode = code.samenstellen();

		while (true) {
			int gewonnen = 4;
			String userCode = maakUserCode.userCode();
			
			if (userCode.contentEquals("q")) {
				System.out.println("Stop spel.");
				break;
			}
			
			int[] userCodeInts = controleerCode.codeNaarInts(userCode);
			int[] geheimeCodeInts = controleer.codeNaarInts(geheimeCode);
			System.out.println(userCode);            // <--printout ingevoerde code user
			System.out.println(geheimeCode);         // <printout geheime code door computer
			int uitkomst = controleer.vergelijkUserCode(geheimeCodeInts, userCodeInts);
			if (uitkomst == gewonnen) {
				System.out.println("Code juist! Je hebt gewonnen!");
				break;
			}
			if (uitkomst != gewonnen) {
				System.out.println("Helaas, probeer het opnieuw!");
				System.out.println();
				continue;
			}
		}
		System.out.println("Einde spel.");
	}

}

class introSpel {
	void toonIntro() {
		Scanner scn = new Scanner(System.in);
		System.out.println("Welkom bij het spel Mastermind!");
		System.out.println();
		System.out.println("Bij dit spel is het de bedoeling dat je mijn 4-letter code kraakt.");
		System.out.println(
				"Elke letter in de code heeft de mogelijkheid a t/m f. Een voorbeeld code is dus 'abcd' of 'bdfa'");
		System.out.println("Let op, een letter mag meermaals voorkomen! Dus 'aabb' is ook een mogelijke code!");
		System.out.println();
		System.out.println("Klaar om te starten? Druk <enter>! ");
		scn.nextLine();
	}
}

class geheimeCodeSamenstellen {
	public String samenstellen() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("We gaan de geheime code aanmaken die jij gaat kraken.");
		System.out.println("Druk op <enter> om geheime code te genereren: ");
		scanner.nextLine();
		String chars = "abcdef";
		Random rnd = new Random();
		char char1 = chars.charAt(rnd.nextInt(chars.length()));
		char char2 = chars.charAt(rnd.nextInt(chars.length()));
		char char3 = chars.charAt(rnd.nextInt(chars.length()));
		char char4 = chars.charAt(rnd.nextInt(chars.length()));
		String code = new StringBuilder().append(char1).append(char2).append(char3).append(char4).toString();
		System.out.println("Code **** is aangemaakt en opgeslagen!");
		System.out.println();
		return code;
	}

}

class invoerUserCode {
	String scope = "abcdef";
	Scanner scanner = new Scanner(System.in);

	public String userCode() {

		while (true) {
			System.out.println("Begin met kraken code...");
			System.out.println("Geef in mogelijke 4 letter (a t/m f) combinatie, rond af met <enter>:");
			System.out.println("Om te stoppen druk op <q>");
			String userInput = scanner.nextLine();

			if (userInput.equals("q")) {
				return userInput;
			}

			if ((userInput.length() == 4) && userInput.matches("[" + scope + "]+")) {
				return userInput;
			}

			else {
				System.out.println("Ongeldige code, geef in geldige code 4 letters a t/m f!");
				System.out.println();
			}

		}
	}
}

class controleerCode {
	
	 public static int[] codeNaarInts(String code) {
		 
	 int[] codeNaarInts = new int[4];
	 char[] chars = code.toCharArray();

	        for (int i=0; i < 4; i++) {
	            switch (chars[i]) {
	                case 'a': {
	                    codeNaarInts[i] = 0; break;
	                }
	                case 'b': {
	                    codeNaarInts[i] = 1; break;
	                }
	                case 'c': {
	                    codeNaarInts[i] = 2; break;
	                }
	                case 'd': {
	                    codeNaarInts[i] = 3; break;
	                }
	                case 'e': {
	                    codeNaarInts[i] = 4; break;
	                }
	                case 'f': {
	                    codeNaarInts[i] = 5; break;
	                }
	            }
	        }
	        return codeNaarInts;
	    }

	public int vergelijkUserCode(int[] geheimeCode, int[] userInput) {
		
		int counterJuist = 0; 
		int counterOnjuist = 0;
			
		int[] tempCode = new int[4];
	    int[] tempNums = new int[4];
	    
	        for (int i=0; i < 4; i++) {
	            tempCode[i] = geheimeCode[i];
	            tempNums[i] = userInput[i];
	        }


	        for (int i=0; i < geheimeCode.length; i++) {  //juiste positie juiste letter
	            if (tempCode[i]==tempNums[i]) {
	                counterJuist++;
	                tempCode[i]=-1;
	                tempNums[i]=-1;
	            }
	        }

	        for (int i=0; i < geheimeCode.length; i++) {  //verkeerde positie juiste letter
	            if (tempCode[i]==-1) {
	                continue;
	            }
	            for (int j=0;j < geheimeCode.length; j++) {
	                if (tempNums[j]==-1) {
	                    continue;
	                }
	                if (tempCode[i]==tempNums[j]) {
	                    counterOnjuist++;
	                    tempCode[i]=-1;
	                    tempNums[j]=-1;
	                }
	            }
	        }


	        int[] result = new int[2];
	        result[0] = counterJuist;
	        result[1] = counterOnjuist;


			System.out.println("Aantal correcte letters, welke op de juiste plaats staan: " + counterJuist);
			System.out.println("Aantal correcte letters, welke niet op de juiste plaats staan: " + counterOnjuist);
			return counterJuist;
		}
	}


