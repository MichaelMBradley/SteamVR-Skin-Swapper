import java.awt.event.*;
import javax.swing.*;

public class error implements ActionListener {
	private JFrame f;
	private JButton ok;
	private JLabel m;
	private int p = 1;
	private int t = 0;
	
	public void display(String msg) {
		msg = "<html>" + msg + "</html>";
		for(int i = 0; i<msg.length(); i++) {
			t++;
			if(t>40&&msg.charAt(i)==' ') {
				p++;
				msg = msg.substring(0,i) + "<br/>" + msg.substring(i);
				t = 0;
			}
		}
		f = new JFrame("SteamVR Skin Swapper");
		ok = new JButton("OK");
		ok.setBounds(0, 25*(p+1), 300, 25);
		ok.addActionListener(this);
		m = new JLabel(msg);
		m.setBounds(0, 0, 300, 25*p);
		
		f.add(ok);f.add(m);
		f.setSize(300+15, (p+2)*25 +38);
		f.setLayout(null);
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	}
	
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == ok) {
			System.exit(0);
		}
	}
}