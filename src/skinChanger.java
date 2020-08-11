import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class skinChanger {
	public static ArrayList<String> findBasestations(String steam) {
		File SteamVR = new File(steam + "\\workshop\\content\\250820");
		ArrayList<String> skins = new ArrayList<String>();
		skins.add(null);
		
		File[] check = SteamVR.listFiles();
		for(File corr : check) {
			if(corr.isDirectory()) {
				File[] test = corr.listFiles();
				for(File thumb : test) {
					if(new File(thumb.toString() + "\\vive_base_thumbnail.png").exists()) {
						skins.add(corr.getName());
						if(!new File(thumb.toString() + "\\skinID.txt").exists()) {
							try {
								File basestationSkin = new File(thumb.toString(), "skinID.txt");
								FileWriter b = new FileWriter(basestationSkin);
								b.write(corr.getName());
								b.close();
							} catch (IOException e) {
								error err = new error();
								err.display(e.toString());
							}
							
						}
					}
				}
			}
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(steam + "\\common\\SteamVR\\resources\\rendermodels\\lh_basestation_vive\\skinID.txt"));
			skins.set(0, br.readLine());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return skins;
	}
	
	public static ArrayList<String> findControllers(String steam) {
		File SteamVR = new File(steam + "\\workshop\\content\\250820");
		ArrayList<String> skins = new ArrayList<String>();
		skins.add(null);
		
		File[] check = SteamVR.listFiles();
		for(File corr : check) {
			if(corr.isDirectory()) {
				File[] test = corr.listFiles();
				for(File thumb : test) {
					if(new File(thumb.toString() + "\\vive_controller_thumbnail.png").exists()) {
						skins.add(corr.getName());
						if(!new File(thumb.toString() + "\\skinID.txt").exists()) {
							try {
								File controllerSkin = new File(thumb.toString(), "skinID.txt");
								FileWriter c = new FileWriter(controllerSkin);
								c.write(corr.getName());
								c.close();
							} catch (IOException e) {
								error err = new error();
								err.display(e.toString());
							}
							
						}
					}
				}
			}
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(steam + "\\common\\SteamVR\\resources\\rendermodels\\vr_controller_vive_1_5\\skinID.txt"));
			skins.set(0, br.readLine());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
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
			File[] copy = new File(skin.toString() + "\\" + skins.get(0)).listFiles();
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