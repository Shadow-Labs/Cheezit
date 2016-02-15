import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class InputRecorder {
	static boolean recording;
	static Thread recordThread;
	static Point p;
	public InputRecorder() {
		recording = false;
		p = new Point(MouseInfo.getPointerInfo().getLocation());
		recordThread = new Thread() {
			public void run() {
	        	MouseObserver mo = new MouseObserver(ArkBotGUI.GUI);
                mo.addMouseMotionListener(new MouseMotionListener() {
                        public void mouseMoved(MouseEvent e) {                            
                        }
                        public void mouseDragged(MouseEvent e) {
                        }
                    });
                mo.start();
                System.out.println("Recording: " + recording);
	            while (recording) {
	            }
	            System.out.println("Stopped Running.");
			}
		};
	}
	public void Record() {
		if (recording) {
			recording = false;
			System.out.println("Done recording.");
		} else {
			recording = true;
			recordThread.start();
			System.out.println("Recording!");
		}
		
	}
}
