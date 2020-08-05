import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class setup {
	public static String initialize() {
		String steam = "";
		try {
			Process p = Runtime.getRuntime().exec("REG QUERY HKCU\\Software\\Valve\\Steam /v SteamPath"); //Runs a command line prompt to get the registry value of the steam path
			Scanner r = new Scanner(p.getInputStream()); //Reads result
			Scanner e = new Scanner(p.getErrorStream()); //Reads error
			int ir = 0;
			int ie = 0;
			String er = "";
			while(r.hasNextLine()) {
				ir++;
				if(ir==3) { //Once the third line has been reached, a substring of the exact path is taken as the result
					steam = r.nextLine();
					steam = steam.substring(steam.indexOf("REG_SZ")+10);
				} else {
					r.nextLine();
				}
			}
			while(e.hasNextLine()) { //Cycle through error lines (should consist of one blank line)
				ie++;
				er+=e.nextLine();
			}
			r.close();
			e.close();
			if(ie>1) {
				error err = new error();
				err.display(er);
			}
		} catch (IOException e) {
			error erro = new error();
			erro.display(e.toString());
			return "";
		}
		
		steam.replaceAll("/", "\\\\"); //Replaces "/" with "\\" in path
		steam+="\\steamapps"; //Specifies sub-directory
		
		File basestation = new File(steam + "\\workshop\\content\\250820\\defaultBasestation");
		File controller = new File(steam + "\\workshop\\content\\250820\\defaultController");
		File basestationDefault = new File(steam + "\\common\\SteamVR\\resources\\rendermodels\\lh_basestation_vive");
		File controllerDefault = new File(steam + "\\common\\SteamVR\\resources\\rendermodels\\vr_controller_vive_1_5");
		File[] toCopy;
		
		try {
			if(!basestation.exists()) { //Only runs if base station backup file does not yet exist
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
		} catch(IOException e) {
			e.printStackTrace();
		}
		return steam;
	}
}