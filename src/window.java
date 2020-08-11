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
		r = new JButton(">"); //Upper right
		r.setBounds(450, 0, 50, 300);
		r.addActionListener(this);
		l = new JButton("<");//Upper left
		l.setBounds(0, 0, 50, 300);
		l.addActionListener(this);
		e = new JButton("Open File"); //Bottom left
		e.setBounds(0, 300, 100, 50);
		e.addActionListener(this);
		c = new JButton("Choose"); //Bottom middle
		c.setBounds(100, 300, 300, 50);
		c.addActionListener(this);
		t = new JButton("Controller"); //Bottom right
		t.setBounds(400, 300, 100, 50);
		t.addActionListener(this);
		j = new JLabel();
		j.setBounds(50, 0, 400, 300);
		
		f.add(r);f.add(l);f.add(e);f.add(c);f.add(t);f.add(j);
		f.setSize(515,388);
		f.setLayout(null);
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0); //Quits program on window close
			}
		});
		
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
				error err = new error();
				err.display(er.toString());
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
			error err = new error();
			err.display(er.toString());
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