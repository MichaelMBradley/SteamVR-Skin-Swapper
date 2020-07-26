import java.util.Scanner;

public class primary {
	public static void main(String[] args) {
		String[][] skins = new String[2][100];
		boolean cont = true;
		boolean base = true;
		Scanner sc = new Scanner(System.in);
		int cmd = 0;
		String steam = "C:\\Program Files (x86)\\Steam";
		
		if(!setup.initialize(steam)) {
			cont = false;
			base = false;
		} else {
			skins = skinChanger.findSkins(steam);
			
			if(skins[0][0] == "0") {
				base = false;
			}
			if(skins[1][0] == "0") {
				cont = false;
			}
		}
		
		System.out.println("Base Stations");
		for(int i = 1; i <= Integer.parseInt(skins[0][0]); i++) {
			System.out.println(skins[0][i]);
		}
		System.out.println("\nControllers");
		for(int j = 1; j <= Integer.parseInt(skins[1][0]); j++) {
			System.out.println(skins[1][j]);
		}
		System.out.println("\n1->" + (Integer.valueOf(skins[0][0]) + Integer.valueOf(skins[1][0])) + " swaps skins. " + (Integer.valueOf(skins[0][0]) + Integer.valueOf(skins[1][0]) + 1) + " exits.");
		
		do {
			cmd = sc.nextInt();
			if(cmd!=9) {
				System.out.println(skinChanger.swapSkins(cmd, skins, steam));
			}
		} while (cmd!=9);
		sc.close();
	}
}