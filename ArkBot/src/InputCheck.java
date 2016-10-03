
public class InputCheck {
	public static boolean isInt(String str) {
		try {
	        int temp = Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	        return false;
	    }
        return true;
	}
}
