import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class setup {
	public static boolean initialize(String steam) {
		File basestation = new File(steam + "\\steamapps\\workshop\\content\\250820\\defaultBasestation");
		File controller = new File(steam + "\\steamapps\\workshop\\content\\250820\\defaultController");
		File basestationDefault = new File(steam + "\\steamapps\\common\\SteamVR\\resources\\rendermodels\\lh_basestation_vive");
		File controllerDefault = new File(steam + "\\steamapps\\common\\SteamVR\\resources\\rendermodels\\vr_controller_vive_1_5");
		File[] toCopy;
		
		if(!new File(basestation.getParent()).exists()) {
			return false;
		}
		try {
			if(!basestation.exists()) { //Only runs if basestation backup file does not yet exist
					basestation.mkdir(); //Creates pretend workshop entry
					new File(basestation, basestationDefault.getName()).mkdir(); //Creates sub-directory for textures
					toCopy = basestationDefault.listFiles();
					
					for(File copy : toCopy) { //Individually copies every file
						Files.copy(copy.toPath(), new File(basestation.toString() + "\\" + basestationDefault.getName() + "\\" + copy.getName()).toPath()); //Creates path for copied file
					}
			}
			
			if(!controller.exists()) { //Only runs if controller backup file does not yet exist
					controller.mkdir(); //Creates pretend workshop entry
					new File(controller, controllerDefault.getName()).mkdir(); //Creates sub-directory for textures
					toCopy = controllerDefault.listFiles();
					
					for(File copy : toCopy) { //Individually copies every file
						Files.copy(copy.toPath(), new File(controller.toString() + "\\" + controllerDefault.getName() + "\\" + copy.getName()).toPath()); //Creates path for copied file
					}
			}
		} catch(SecurityException e) { //Error tracking
			e.printStackTrace();
			return false;
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}