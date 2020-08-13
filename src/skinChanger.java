import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.BufferedReader;

public class skinChanger {
	public static ArrayList<String> findBasestations(String steam) {
		File SteamVR = new File(steam + "\\workshop\\content\\250820");
		ArrayList<String> skins = new ArrayList<String>();
		skins.add(null);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(steam + "\\common\\SteamVR\\resources\\rendermodels\\vr_controller_vive_1_5\\skinID.txt"));
			File[] check = SteamVR.listFiles();
			for(File corr : check) {
				if(corr.isDirectory()) {
					File[] test = corr.listFiles();
					for(File thumb : test) {
						String th = thumb.toString();
						if(new File(th + "\\vive_base_thumbnail.png").exists()) {
							if(!new File(th + "\\skinID.txt").exists()) {
								FileWriter c = new FileWriter(new File(th, "skinID.txt"));
								c.write(corr.getName()+"/nvive_base_thumbnail.png/nBasestation");
								c.close();
							}
						} else if(new File(th + "\\skinID.txt").exists()) {
							BufferedReader r = new BufferedReader(new FileReader(th + "\\skinID.txt"));
							String temp = r.readLine();
							if(temp.split("/n")[2].equals("Basestation")) {
								skins.add(temp);
							}
							r.close();
						}
					}
				}
			}
			skins.set(0, br.readLine());
			br.close();
		} catch (IOException e) {
			popup err = new popup();
			err.display(e.toString(), true);
		}
		
		return skins;
	}
	
	public static ArrayList<String> findControllers(String steam) {
		File SteamVR = new File(steam + "\\workshop\\content\\250820");
		ArrayList<String> skins = new ArrayList<String>();
		skins.add(null);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(steam + "\\common\\SteamVR\\resources\\rendermodels\\vr_controller_vive_1_5\\skinID.txt"));
			File[] check = SteamVR.listFiles();
			for(File corr : check) {
				if(corr.isDirectory()) {
					File[] test = corr.listFiles();
					for(File thumb : test) {
						String th = thumb.toString();
						if(new File(th + "\\vive_controller_thumbnail.png").exists()) {
							if(!new File(th + "\\skinID.txt").exists()) {
								FileWriter c = new FileWriter(new File(th, "skinID.txt"));
								c.write(corr.getName()+"/nvive_controller_thumbnail.png/nController");
								c.close();
							}
						} else if(new File(th + "\\skinID.txt").exists()) {
							BufferedReader r = new BufferedReader(new FileReader(th + "\\skinID.txt"));
							String temp = r.readLine();
							if(temp.split("/n")[2].equals("Controller")) {
								skins.add(temp);
							}
							r.close();
						}
					}
				}
			}
			skins.set(0, br.readLine());
			br.close();
		} catch (IOException e) {
			popup err = new popup();
			err.display(e.toString(), true);
		}
		
		return skins;
	}
	
	public static void swapSkins(int type, ArrayList<String> skins, String steam) {
		File skin = new File(steam + "\\workshop\\content\\250820");
		File basestationDefault = new File(steam + "\\common\\SteamVR\\resources\\rendermodels\\lh_basestation_vive");
		File controllerDefault = new File(steam + "\\common\\SteamVR\\resources\\rendermodels\\vr_controller_vive_1_5");

		if(type==0) {
			File[] delete = basestationDefault.listFiles();
			for(File del : delete) {
				del.delete();
			}
			File[] copy = new File(skin.toString() + "\\" + skins.get(0)).listFiles();
			for(File c : copy) {
				File[] b = c.listFiles();
				for(File temp : b) {
					try {
						Files.copy(temp.toPath(), new File(basestationDefault.toString() + "\\" + temp.getName()).toPath());
					} catch (IOException e) {
						popup err = new popup();
						err.display(e.toString(), true);
					}
				}
			}
		} else {
			File[] delete = controllerDefault.listFiles();
			for(File del : delete) {
				del.delete();
			}
			File[] copy = new File(skin.toString() + "\\" + skins.get(0)).listFiles();
			for(File c : copy) {
				File[] b = c.listFiles();
				for(File temp : b) {
					try {
						Files.copy(temp.toPath(), new File(controllerDefault.toString() + "\\" + temp.getName()).toPath());
					} catch (IOException e) {
						popup err = new popup();
						err.display(e.toString(), true);
					}
				}
			}
		}
	}
	
	public static void deleteSkinID(String steam) {
		FilenameFilter dir = new FilenameFilter() {public boolean accept(File dir, String name) {return !name.contains(".");}};
		for(File dl : new File(steam + "\\workshop\\content\\250820").listFiles(dir)) {
			for(File sub : dl.listFiles(dir)) {
				File skinID = new File(sub.toString() + "//skinID.txt");
				if(skinID.exists()) {
					skinID.delete();
				}
			}
		}
	}
}