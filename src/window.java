import java.awt.Desktop;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class window implements ActionListener {
	private JFrame f;
	private JButton r;
	private JButton l;
	private JButton e;
	private JButton c;
	private JButton t;
	private JLabel j;
	private int type = 0;
	private int indb;
	private int indc;
	private String dir;
	private ArrayList<String> ba;
	private ArrayList<String> co;
	private String steam;
	private String currentImg;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mnNewSkin;
	private JMenuItem mnPre;
	private JMenuItem mnHelp;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupGUI(String SteamVR, ArrayList<String> basestations, ArrayList<String> controllers) {
		dir = SteamVR + "\\workshop\\content\\250820";
		steam = SteamVR;
		ba = basestations;
		co = controllers;

		for(int i = 1; i < ba.size(); i++) {
			if(ba.get(0).equals(ba.get(i))) {
				indb = i;
			}
		}
		for(int i = 1; i < co.size(); i++) {
			if(co.get(0).equals(co.get(i))) {
				indc = i;
			}
		}
		
		f = new JFrame("SteamVR Skin Swapper");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r = new JButton(">");
		r.setToolTipText("Next skin");
		r.setBounds(456, 2, 50, 300);
		r.addActionListener(this);
		l = new JButton("<");
		l.setToolTipText("Previous skin");
		l.setBounds(2, 2, 50, 300);
		l.addActionListener(this);
		e = new JButton("Explorer");
		e.setToolTipText("Open skin in file explorer");
		e.setBounds(2, 304, 100, 50);
		e.addActionListener(this);
		c = new JButton("Choose");
		c.setToolTipText("Use this skin");
		c.setBounds(104, 304, 300, 50);
		c.addActionListener(this);
		t = new JButton("Controller");
		t.setToolTipText("Swap type of skin");
		t.setBounds(406, 304, 100, 50);
		t.addActionListener(this);
		j = new JLabel();
		j.setBounds(54, 2, 400, 300);
		f.getContentPane().setLayout(null);
		
		f.getContentPane().add(r);f.getContentPane().add(l);f.getContentPane().add(e);f.getContentPane().add(c);f.getContentPane().add(t);f.getContentPane().add(j);
		f.setSize(524,418);

		menuBar = new JMenuBar();
		f.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mnNewSkin = new JMenuItem("Find");
		mnNewSkin.setToolTipText("Manually find new skins");
		mnFile.add(mnNewSkin);
		mnPre = new JMenuItem("Preview");
		mnPre.setToolTipText("Get new preview image");
		mnFile.add(mnPre);

		mnHelp = new JMenuItem("Help");
		mnHelp.addActionListener(this);
		menuBar.add(mnHelp);
		
		f.setVisible(true);
		reloadImage();
	}

	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == r) {
			if(type == 0) { //Cycles right through skins
				indb++;
				if(indb == ba.size()) {
					indb = 1; //Overflows to "0"
				}
			} else { //If relevant skins are controllers
				indc++;
				if(indc == co.size()) {
					indc = 1;
				}
			}
			reloadImage();
		} else if(a.getSource() == l) {
			if(type == 0) { //Cycles left through skins
				indb--;
				if(indb < 1) {
					indb = ba.size()-1; //Underflows to max
				}
			} else { //If relevant skins are controllers
				indc--;
				if(indc < 1) {
					indc = co.size()-1;
				}
			}
			reloadImage();
		} else if(a.getSource() == e) {
			try {
				Desktop.getDesktop().open(new File(currentImg).getParentFile());
			} catch (IOException er) {
				popup err = new popup();
				err.display(er.toString(), true);
			}
		} else if(a.getSource() == c) {
			if(type == 0) {
				ba.set(0, ba.get(indb));
				skinChanger.swapSkins(type, ba, steam);
			} else {
				co.set(0, co.get(indc));
				skinChanger.swapSkins(type, co, steam);
			}

			reloadText();
		} else if(a.getSource() == t) {
			if(type == 0) {
				type = 1;
				t.setText("Base");
			} else {
				type = 0;
				t.setText("Controller");
			}

			reloadImage();
		} else if(a.getSource() == mnHelp) {
			popup h = new popup();
			h.display(
			"Skins:/n" +
			"* (Default) is the skin that comes with SteamVR/n" +
			"*** There may be more skins with identical previews, but (Default) marks the real one/n" +
			"* (Current) Is the skin that is currently in use/n" +
			"/n" +
			"Problems:/n" +
			"* Preview does not represent skin/n" +
			"*** File>Preview lets you select a file that is not the default, but may be more accurate/n" +
			"* Not all downloaded skins are detected/n" +
			"*** File>Find allows you to browse other workshop files to use as skins", false);
		} else if(a.getSource() == mnNewSkin) {
			//Find new skins
		} else if(a.getSource() == mnPre) {
			//Find other preview image
		}
	}

	public void reloadImage() {
		try {
			if(type == 0) {
				if(ba.size()>1) {
					File[] sub = new File(dir + "\\" + ba.get(indb)).listFiles();
					currentImg = sub[0] + "\\vive_base_thumbnail.png";
					BufferedImage img = ImageIO.read(new File(currentImg));
					ImageIcon icon = new ImageIcon(img);
					j.setIcon(icon);
					reloadText();
				} else {
					c.setText("No base station skins found.");
				}
			} else {
				if(co.size()>1) {
					File[] sub = new File(dir + "\\" + co.get(indc)).listFiles();
					currentImg = sub[0] + "\\vive_controller_thumbnail.png";
					BufferedImage img = ImageIO.read(new File(currentImg));
					ImageIcon icon = new ImageIcon(img);
					j.setIcon(icon);
					reloadText();
				} else {
					c.setText("No controller skins found.");
				}
			}
		} catch(IOException er) {
			popup err = new popup();
			err.display(er.toString(), true);
		}
	}

	public void reloadText() {
		String msg = "Choose";
			if(type == 0) {
				if(indb == ba.size()-1) {
					msg+= " (Default)";
				}

				if(ba.get(indb).equals(ba.get(0))) {
					msg+= " (Current)";
				}
			} else {
				if(indc == co.size()-1) {
					msg+= " (Default)";
				}

				if(co.get(indc).equals(co.get(0))) {
					msg+= " (Current)";
				}
			}
		c.setText(msg);
	}
}
