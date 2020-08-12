import java.awt.event.*;

import javax.swing.*;

public class popup implements ActionListener {
	private JFrame f;
	private JButton ok;
	private JLabel m;
	private int t = 0;
	private boolean exit;
	private String og;
	
	public void display(String msg, boolean close) {
		exit = close;
		og = msg;
		
		f = new JFrame("SteamVR Skin Swapper");
		f.setSize(500, 50+msg.length()/2);
		m = new JLabel();
		m.setVerticalAlignment(SwingConstants.TOP);
		ok = new JButton("OK");
		ok.addActionListener(this);
		
		resize();
		
		f.add(ok);f.add(m);
		f.setLayout(null);
		f.setVisible(true);
		
		f.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        resize();
		    }
		});
		
		if(exit) {
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
	
	public String formatMsg(String msg) {
		for(int i = 0; i<msg.length(); i++) {
			t++;
			if(t>f.getWidth()/5&&msg.charAt(i)==' ') {
				msg = msg.substring(0,i) + "<br/>" + msg.substring(i);
				t = 0;
			} else if((i>0?msg.substring(i-1,i+1):"").equals("/n")) {
				msg = msg.substring(0,i-1) + "<br/>" + msg.substring(i+1);
				t = 0;
			}
		}
		msg = "<html>" + msg + "</html>";
		return msg;
	}
	
	public void resize() {
		m.setBounds(0, 0, f.getWidth(), f.getHeight());
		m.setText(formatMsg(og));
		ok.setBounds(1, f.getHeight()-65, f.getWidth()-18, 25);
	}
	
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == ok) {
			if(exit) {
				System.exit(0);
			} else {
				f.dispose();
			}
		}
	}
}