package RobotMovement;

import webservice.RobotController;

public class UserMovement extends Thread {
	private static String user;
	private static String[] left = { "Left" };
	private static String[] right = { "Right" };
	private static String[] forward = { "Forward" };
	private static String[] back = { "Back" };
	private static String command;

	@Override
	public void run() {
		executeCommand();
	}

	private synchronized void executeCommand() {
		String[] commands;
		synchronized (command) {
			if (command.equals("Left")) {
				commands = getLeft();
			} else if (command.equals("Right")) {
				commands = getRight();
			} else if (command.equals("Forward")) {
				commands = getForward();
			} else if (command.equals("Back")) {
				commands = getBack();
			} else {
				commands = new String[1];
				commands[0] = "Stop";
			}
		}
		for (String cmd : commands) {
			RobotMovement.movement = cmd;
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RobotMovement.movement = "Stop";
	}

	/**
	 * @return the user
	 */
	public static String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public static void setUser(String user1) {
		user = user1;

	}

	/**
	 * @return the left
	 */
	public static String[] getLeft() {
		return left;
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public static void setLeft(String[] left1) {
		left = left1;
	}

	/**
	 * @return the right
	 */
	public static String[] getRight() {
		return right;
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public static void setRight(String[] right1) {
		right = right1;
	}

	/**
	 * @return the forward
	 */
	public static String[] getForward() {
		return forward;
	}

	/**
	 * @param forward
	 *            the forward to set
	 */
	public static void setForward(String[] forward1) {
		forward = forward1;
	}

	/**
	 * @return the back
	 */
	public static String[] getBack() {
		return back;
	}

	/**
	 * @param back
	 *            the back to set
	 */
	public static void setBack(String[] back1) {
		back = back1;
	}

	/**
	 * @param command
	 *            the command to set
	 */
	public static void setCommand(String command1) {
		command = command1;
	}
}