package Database;

public class User {

	private String user;
	private String pass;
	private String left;
	private String right;
	private String forward;
	private String back;
	
	public User(String user, String pass, String left, String right,
			String forward, String back) {
		super();
		this.user = user;
		this.pass = pass;
		this.left = left;
		this.right = right;
		this.forward = forward;
		this.back = back;
	}

	@Override
	public String toString() {
		return "User: " + user+" Password: " + pass + "" +
				"Left: " + left + " Right: " + right + "" +
				" Forward: " + forward + " Back: " + back;
	}
	
	public void print() {
	System.out.println(this);
	}
	
	
	public User(String user, String pass) {
		super();
		this.user = user;
		this.pass = pass;
	}



	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return the left
	 */
	public String getLeft() {
		return left;
	}
	/**
	 * @param left the left to set
	 */
	public void setLeft(String left) {
		this.left = left;
	}
	/**
	 * @return the right
	 */
	public String getRight() {
		return right;
	}
	/**
	 * @param right the right to set
	 */
	public void setRight(String right) {
		this.right = right;
	}
	/**
	 * @return the forward
	 */
	public String getForward() {
		return forward;
	}
	/**
	 * @param forward the forward to set
	 */
	public void setForward(String forward) {
		this.forward = forward;
	}
	/**
	 * @return the back
	 */
	public String getBack() {
		return back;
	}
	/**
	 * @param back the back to set
	 */
	public void setBack(String back) {
		this.back = back;
	}
	
	
	
	
	
}
