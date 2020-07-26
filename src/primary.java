public class primary {
	public static void main(String[] args) {
		String[][] skins = new String[2][100];
		
		if(!setup.initialize("C:\\Program Files (x86)\\Steam")) {
			System.out.print("You have either incorrectly selected Steam's directory or you have not yet installed any skins.");
			System.exit(0);
		} else {
			skins = skinChanger.findSkins();
		}
		
		System.out.println("Ready to swap!");
		if(skins[0][0] == "0" & skins[1][0] == "0") {
			System.out.print("No skins detected.");
			System.exit(0);
		}
		
		System.out.println("Basestations");
		for(int i = 1; i <= Integer.parseInt(skins[0][0]); i++) {
			System.out.println(skins[0][i]);
		}
		System.out.println("Controllers");
		for(int j = 1; j <= Integer.parseInt(skins[1][0]); j++) {
			System.out.println(skins[1][j]);
		}
	}
}