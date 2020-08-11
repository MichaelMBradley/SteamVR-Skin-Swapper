import java.awt.Desktop;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class window implements ActionListener {
	private JInternalFrame f;
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
	private String steam;
	private String currentImg;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void setupGUI(String SteamVR, String[][] skin) {
		dir = SteamVR + "\\workshop\\content\\250820";
		steam = SteamVR;
		skinfo = skin;
		
		f = new JInternalFrame("SteamVR Skin Swapper", false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setClosable(true);
		r = new JButton(">");
		r.setBounds(455, 0, 50, 300);
		r.addActionListener(this);
		l = new JButton("<");
		l.setBounds(1, 0, 50, 300);
		l.addActionListener(this);
		e = new JButton("Explorer");
		e.setBounds(1, 302, 100, 50);
		e.addActionListener(this);
		c = new JButton("Choose");
		c.setBounds(103, 302, 300, 50);
		c.addActionListener(this);
		t = new JButton("Controller");
		t.setBounds(405, 302, 100, 50);
		t.addActionListener(this);
		j = new JLabel();
		j.setBounds(53, 0, 400, 300);
		f.getContentPane().setLayout(null);
		
		f.getContentPane().add(r);f.getContentPane().add(l);f.getContentPane().add(e);f.getContentPane().add(c);f.getContentPane().add(t);f.getContentPane().add(j);
		f.setSize(522,405);
		
		menuBar = new JMenuBar();
		f.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Select skins");
		mnNewMenu.add(mntmNewMenuItem);
		f.setVisible(true);
		
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
			} catch (IOException er) {
				error err = new error();
				err.display(er.toString());
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
					c.setText("No downloaded base station skins could be found.");
				}
			} else {
				if(Integer.valueOf(skinfo[1][0])!=0) {
					File[] sub = new File(dir + "\\" + skinfo[1][indc]).listFiles();
					currentImg = sub[0] + "\\vive_controller_thumbnail.png";
					BufferedImage img = ImageIO.read(new File(currentImg));
					ImageIcon icon = new ImageIcon(img);
					j.setIcon(icon);
				} else {
					c.setText("No downloaded controller skins could be found.");
				}
			}
		} catch(IOException er) {
			error err = new error();
			err.display(er.toString());
		}
	}
}