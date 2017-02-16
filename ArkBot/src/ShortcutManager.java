import java.awt.event.KeyEvent;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ShortcutManager implements NativeKeyListener {
	
    public void nativeKeyPressed(NativeKeyEvent e) {
    	
    	// Check to see if nextKey is requested
    	if (Global.nextKey) {
    		Global.lastPressed = e.getKeyCode();
    	}
    	
 //       System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        //ArkBotGUI.GUIText("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

    	// ServerStartShortcut (Home)
//        if (e.getKeyCode() == 0xE47) {
//        	ArkBot.serverStart = !ArkBot.serverStart;
//        	ArkBot.serverRun = !ArkBot.serverRun;
//        }
    	
    	
    	
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
        if (ArkBot.state.gatherer.gathering) {
	        if (e.getKeyCode() == Global.AGatherStartStop) {
	        	System.out.println("gather: " + ArkBot.state.gatherer.gathering + " click: " + ArkBot.state.gatherer.clicking);
	        	ArkBot.state.gatherer.StartStop();
	        } else if (e.getKeyCode() == Global.AGatherDrop) {
	        	if (ArkBot.state.gatherer.drop) {
	        		// Actively Dropping
	        		if (ArkBot.state.gatherer.dropping) {
	        			ArkBot.state.gatherer.dropping = false;
	        		// Not dropping
	        		} else {
	        			ArkBot.state.gatherer.dropping = true;
	        		}
	        	}
	        }
        }
        
        // BreedingShortcuts
        if (ArkBot.state.breeder.breed) {
        	if (e.getKeyCode() ==  Global.BreederStartStop) {
        		if (ArkBot.state.breeder.breeding) {
        			ArkBotGUI.GUIText("[BREEDER]: Paused.");
        			ArkBot.state.breeder.breeding = false;
        		} else {
        			ArkBotGUI.GUIText("[BREEDER]: Resumed.");
        			ArkBot.state.breeder.breeding = true;
        		}
        	}
        }
        
        // AutoHealingShortcuts
        if (ArkBot.state.healer.heal) {
	        if (e.getKeyCode() ==  Global.AHealIncOne) {
	        	ArkBotGUI.GUIText("[HEALER]: Added 100 Health to Queue.");
	        	ArkBot.state.healer.healcount += 1;
	        	ArkBot.state.healer.healing = true;
	        } else if (e.getKeyCode() == Global.AHealIncTen) {
	        	ArkBotGUI.GUIText("[HEALER]: Added 1000 Health to Queue.");
	        	ArkBot.state.healer.healcount += 10;
	        	ArkBot.state.healer.healing = true;
	        } else if (e.getKeyCode() == Global.AHealAbort) {
	        	ArkBotGUI.GUIText("[HEALER]: Aborted Healing.");
	        	ArkBot.state.healer.abort = true;
	        	ArkBot.state.healer.healing = false;
	        	ArkBot.state.healer.healcount = 0;	        	
	        }
        }
        
        // MeatSplitterShortcuts
        if (ArkBot.state.splitter.split) {
	        if (e.getKeyCode() ==  Global.MSplitter) {
	        	if (ArkBot.state.splitter.splitting) {
		        	ArkBotGUI.GUIText("[SPLITTER]: Stopped");
		        	ArkBot.state.splitter.splitting = false;
	        	} else {
	        		ArkBotGUI.GUIText("[SPLITTER]: Started");
		        	ArkBot.state.splitter.splitting = true;
	        	}
	        }
        }
        
        // Autopilot Shortcuts
        if (ArkBot.state.piloter.pilot) {
	        if (e.getKeyCode() ==  Global.APilot) {
	        	if (ArkBot.state.piloter.piloting) {
		        	ArkBotGUI.GUIText("[AUTOPILOT]: Paused");
		        	ArkBot.state.piloter.piloting = false;
	        	} else {
	        		ArkBotGUI.GUIText("[AUTOPILOT]: Resumed");
		        	ArkBot.state.piloter.piloting = true;
	        	}
	        }
        }
        
        // AutoFisher Shortcuts
        if (ArkBot.state.fisher.fish) {
	        if (e.getKeyCode() ==  Global.AFish) {
	        	if (ArkBot.state.fisher.fishing) {
		        	ArkBotGUI.GUIText("[AUTOFISHER]: Paused");
		        	ArkBot.state.fisher.fishing = false;
	        	} else {
	        		ArkBotGUI.GUIText("[AUTOFISHER]: Resumed");
		        	ArkBot.state.fisher.fishing = true;
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
