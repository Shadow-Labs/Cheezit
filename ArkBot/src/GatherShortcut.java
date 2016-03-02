import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

// Shortcut Actions
        public class GatherShortcut extends AbstractAction {
        	GatherShortcut() {
        		System.out.println("Pressed\n\n\n\n\n");
        	}
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		ArkBotGUI.GUIText("Different Shortcut Pressed!");
        	}
        }
