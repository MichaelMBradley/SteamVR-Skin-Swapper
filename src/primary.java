public class primary {
	public static void main(String[] args) {
		String[][] skins = new String[2][100];
		String steam;
		
		steam = setup.initialize();
		if(steam!="") {
			skins = skinChanger.findSkins(steam);
					
			window w = new window();
			w.setupGUI(steam, skins);
		} else {
			error e = new error();
			e.display("The Steam directory could not be located.");
		}
	}
}