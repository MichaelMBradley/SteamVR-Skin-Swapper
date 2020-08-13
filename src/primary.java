import java.util.ArrayList;

public class primary {
	public static void main(String[] args) {
		//skinChanger.deleteSkinID(setup.initialize());/*
		ArrayList<String> controllers = new ArrayList<String>();
		ArrayList<String> basestations = new ArrayList<String>();
		String steam;
		
		steam = setup.initialize();
		if(steam!="") {
			controllers = skinChanger.findControllers(steam);
			basestations = skinChanger.findBasestations(steam);
					
			window w = new window();
			w.setupGUI(steam, basestations, controllers);
		} else {
			popup e = new popup();
			e.display("The Steam directory could not be located.", true);
		}//*/
	}
}