import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BgPanel extends JPanel {
	Image imgIcon;
	public BgPanel(Image icon) {
		imgIcon = icon;
	}
	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(imgIcon, 0, 0, getWidth(), getHeight(), this);
    }
}
