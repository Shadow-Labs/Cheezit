import java.awt.event.KeyEvent;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ShortcutManager implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
 //       System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        //ArkBotGUI.GUIText("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

    	// ServerStartShortcut (Home)
        if (e.getKeyCode() == 0xE47) {
        	ArkBot.serve = true;
        }
    	
    	
    	
        if (e.getKeyCode() == NativeKeyEvent.VC_F8) {
            try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
//        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
//        ArkBotGUI.GUIText("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        
        // AutoGatheringShortcuts
        if (ArkBot.gatherer.gathering) {
	        if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("Minus") || NativeKeyEvent.getKeyText(e.getKeyCode()).equals("NumPad Subtract")) {
	        	System.out.println("gather: " + ArkBot.gatherer.gathering + " click: " + ArkBot.gatherer.clicking);
	        	ArkBot.gatherer.StartStop();
	        } else if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals("NumPad Multiply")) {
	        	if (ArkBot.gatherer.dropping) {
		        	if (ArkBot.gatherer.clicking) {
		        		ArkBot.gatherer.StartStop();
		        		ArkBot.gatherer.Drop();
		        		ArkBot.gatherer.StartStop();
		        	} else {
		        		ArkBot.gatherer.Drop();
		        	}
	        	}
	        }
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
//        System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
//        ArkBotGUI.GUIText("Key Typed: " + e.getKeyText(e.getKeyCode()));
    }

//    public static void main(String[] args) {
//        try {
//            GlobalScreen.registerNativeHook();
//        }
//        catch (NativeHookException ex) {
//            System.err.println("There was a problem registering the native hook.");
//            System.err.println(ex.getMessage());
//
//            System.exit(1);
//        }
//
//        GlobalScreen.addNativeKeyListener(new ShortcutManager());
//    }
}
