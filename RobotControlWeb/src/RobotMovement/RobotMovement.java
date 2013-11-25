package RobotMovement;

public class RobotMovement {
	public static String movement = "Nothing";

	/**
	 * @return the movement
	 */
	public static synchronized String getMovement() {
		return movement;
	}

	/**
	 * @param movement the movement to set
	 */
	public static synchronized void setMovement(String movement) {
		RobotMovement.movement = movement;
	}
	
}
