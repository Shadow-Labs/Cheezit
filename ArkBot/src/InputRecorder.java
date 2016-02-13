
public class InputRecorder {
	static boolean recording;
	static Thread recordThread;
	public InputRecorder() {
		recording = false;
		recordThread = new Thread() {
			public void run() {
		        try {
		            System.out.println("Does it work?");

		            Thread.sleep(1000);

		            System.out.println("Nope, it doesnt...again.");
		        } catch(InterruptedException v) {
		            System.out.println(v);
		        }
			}
		};
	}
	public void Record() {
		if (recording) {
			recording = false;
			recordThread.stop();
			System.out.println("Done recording.");
		} else {
			recording = true;
			recordThread.start();
			System.out.println("Recording!");
		}
		
	}
}
