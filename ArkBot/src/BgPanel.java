import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BgPanel extends JPanel {
	Image icon = new ImageIcon("ArkBotFiles/Images/ArkBotBackground.png").getImage();
	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(icon, 0, 0, getWidth(), getHeight(), this);
    }
}
