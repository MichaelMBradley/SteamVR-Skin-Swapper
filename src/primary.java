public class primary {
	public static void main(String[] args) {
		String[][] skins = new String[2][100];
		String steam = "C:\\Program Files (x86)\\Steam";
		String msg = null;
		
		if(!setup.initialize(steam)) {
			msg = "Either the SteamVR directory could not be found or you have not yet downloaded any mods.";
		} else {
			skins = skinChanger.findSkins(steam);
			msg = "You have not yet downloaded any of this type of mod.";
		}
		
		window w = new window();
		w.info(steam, skins, msg);
		w.setupGUI();
	}
}