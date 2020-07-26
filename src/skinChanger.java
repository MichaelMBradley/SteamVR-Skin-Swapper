import java.io.File;

public class skinChanger {
	public static String[][] findSkins() {
		File SteamVR = new File("C:\\Program Files (x86)\\Steam\\steamapps\\workshop\\content\\250820");
		String [][] skins = new String[2][100];
		File[] check = SteamVR.listFiles();
		int b = 1;
		int c = 1;
		
		for(File corr : check) {
			if(corr.isDirectory()) {
				File[] test = corr.listFiles();
				for(File thumb : test) {
					if(new File(thumb.toString() + "\\vive_base_thumbnail.png").exists()) {
						skins[0][b] = corr.getName();
						b++;
					} else if(new File(thumb.toString() + "\\vive_controller_thumbnail.png").exists()) {
						skins[1][c] = corr.getName();
						c++;
					}
				}
			}
		}
		
		b--;
		c--;
		skins[0][0] = String.valueOf(b);
		skins[1][0] = String.valueOf(c);
		
		return skins;
	}
}