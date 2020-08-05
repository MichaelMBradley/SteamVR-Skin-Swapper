import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class skinChanger {
	public static String[][] findSkins(String steam) {
		File SteamVR = new File(steam + "\\workshop\\content\\250820");
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
	
	public static void swapSkins(int type, int ind, String[][] skins, String steam) {
		File skin = new File(steam + "\\workshop\\content\\250820");
		File basestationDefault = new File(steam + "\\common\\SteamVR\\resources\\rendermodels\\lh_basestation_vive");
		File controllerDefault = new File(steam + "\\common\\SteamVR\\resources\\rendermodels\\vr_controller_vive_1_5");

		if(type==0) {
			File[] delete = basestationDefault.listFiles();
			for(File del : delete) {
				del.delete();
			}
			File[] copy = new File(skin.toString() + "\\" + skins[0][ind]).listFiles();
			for(File c : copy) {
				File[] b = c.listFiles();
				for(File temp : b) {
					try {
						Files.copy(temp.toPath(), new File(basestationDefault.toString() + "\\" + temp.getName()).toPath());
					} catch (IOException e) {
						error err = new error();
						err.display(e.toString());
					}
				}
			}
		} else {
			File[] delete = controllerDefault.listFiles();
			for(File del : delete) {
				del.delete();
			}
			File[] copy = new File(skin.toString() + "\\" + skins[1][ind]).listFiles();
			for(File c : copy) {
				File[] b = c.listFiles();
				for(File temp : b) {
					try {
						Files.copy(temp.toPath(), new File(controllerDefault.toString() + "\\" + temp.getName()).toPath());
					} catch (IOException e) {
						error err = new error();
						err.display(e.toString());
					}
				}
			}
		}
	}
}