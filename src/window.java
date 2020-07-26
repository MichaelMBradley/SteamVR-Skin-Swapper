import java.awt.Desktop;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class window implements ActionListener {
	private JFrame f;
	private JButton r;
	private JButton l;
	private JButton e;
	private JButton c;
	private JButton t;
	private JLabel j;
	private int type = 0;
	private int indb = 1;
	private int indc = 1;
	private String dir;
	private String[][] skinfo;
	private String message;
	private String steam;
	private String currentImg;
	
	public void info(String SteamVR, String[][] skin, String msg) {
		dir = SteamVR + "\\steamapps\\workshop\\content\\250820";
		steam = SteamVR;
		skinfo = skin;
		message = msg;
	}
	
	public void setupGUI() {
		f = new JFrame("SteamVR Skin Swapper");
		r = new JButton(">");
		r.setBounds(450, 0, 50, 300);
		r.addActionListener(this);
		l = new JButton("<");
		l.setBounds(0, 0, 50, 300);
		l.addActionListener(this);
		e = new JButton("Explorer");
		e.setBounds(0, 300, 100, 50);
		e.addActionListener(this);
		c = new JButton("Choose");
		c.setBounds(100, 300, 300, 50);
		c.addActionListener(this);
		t = new JButton("Controller");
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
				System.exit(0);
			}
		});
		
		reloadImage();
	}
	
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == r) {
			if(type == 0) {
				indb++;
				if(indb > Integer.valueOf(skinfo[0][0])) {
					indb = 1;
				}
			} else {
				indc++;
				if(indc > Integer.valueOf(skinfo[1][0])) {
					indc = 1;
				}
			}
			reloadImage();
		} else if(a.getSource() == l) {
			if(type == 0) {
				indb--;
				if(indb < 1) {
					indb = Integer.valueOf(skinfo[0][0]);
				}
			} else {
				indc--;
				if(indc < 1) {
					indc = Integer.valueOf(skinfo[1][0]);
				}
			}
			reloadImage();
		} else if(a.getSource() == e) {
			try {
				Desktop.getDesktop().open(new File(currentImg).getParentFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(a.getSource() == c) {
			if(type == 0) {
				skinChanger.swapSkins(type, indb, skinfo, steam);
			} else {
				skinChanger.swapSkins(type, indc, skinfo, steam);
			}
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
				if(Integer.valueOf(skinfo[0][0])!=0) {
					File[] sub = new File(dir + "\\" + skinfo[0][indb]).listFiles();
					currentImg = sub[0] + "\\vive_base_thumbnail.png";
					BufferedImage img = ImageIO.read(new File(currentImg));
					ImageIcon icon = new ImageIcon(img);
					j.setIcon(icon);
				} else {
					c.setText(message);
				}
			} else {
				if(Integer.valueOf(skinfo[1][0])!=0) {
					File[] sub = new File(dir + "\\" + skinfo[1][indc]).listFiles();
					currentImg = sub[0] + "\\vive_controller_thumbnail.png";
					BufferedImage img = ImageIO.read(new File(currentImg));
					ImageIcon icon = new ImageIcon(img);
					j.setIcon(icon);
				} else {
					c.setText(message);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}