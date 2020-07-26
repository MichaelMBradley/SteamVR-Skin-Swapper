import java.awt.event.*;
import javax.swing.*;

public class window implements ActionListener {
	private JFrame f;
	private JButton r;
	private JButton l;
	private JButton e;
	private JButton c;
	private JButton t;
	
	public static void main(String[] args) {
		window w = new window();
		w.setupGUI();
	}
	
	public void setupGUI() {
		f = new JFrame("SteamVR Skin Swapper");
		r = new JButton(">");
		r.setBounds(450, 0, 50, 300);
		r.addActionListener(this);
		l = new JButton("<");
		l.setBounds(0, 0, 50, 300);
		l.addActionListener(this);
		e = new JButton("Exit");
		e.setBounds(0, 300, 100, 50);
		e.addActionListener(this);
		c = new JButton("Choose");
		c.setBounds(100, 300, 300, 50);
		c.addActionListener(this);
		t = new JButton("Swap Type");
		t.setBounds(400, 300, 100, 50);
		t.addActionListener(this);
		
		f.add(r);f.add(l);f.add(e);f.add(c);f.add(t);
		f.setSize(515,388);
		f.setLayout(null);
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == r) {
			
		} else if(a.getSource() == l) {
			
		} else if(a.getSource() == e) {
			System.exit(0);
		} else if(a.getSource() == c) {
			
		} else if(a.getSource() == t) {
			
		}
	}
}