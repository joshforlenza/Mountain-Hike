package project5;

/**
 * This class represents a hiker that traverses on a trail down a mountain. It
 * stores the hiker's list of supplies.
 * 
 * @author Joshua Forlenza
 *
 */

public class Hiker {

	private int food;
	private int raft;
	private int axe;

	/**
	 * Creates a new Hiker object.
	 * 
	 */
	public Hiker() {
		this.food = 0;
		this.raft = 0;
		this.axe = 0;
	}

	/**
	 * Creates a new Hiker object with the specified number of supplies
	 * 
	 * @param food amount of food the Hiker object has
	 * @param raft the amount of rafts the Hiker object has
	 * @param axe  the amount of axes the Hiker object has
	 */
	public Hiker(int food, int raft, int axe) {
		this.food = 0;
		this.raft = 0;
		this.axe = 0;
	}

	/**
	 * Returns the amount of food that the Hiker has
	 * 
	 * @return the food
	 */
	public int getFood() {
		return food;
	}

	/**
	 * Sets the amount of food that the Hiker has
	 * 
	 * @param food the food to set
	 */
	public void setFood(int food) {
		this.food = food;
	}

	/**
	 * Returns the amount of rafts the hiker has
	 * 
	 * @return the raft
	 */
	public int getRaft() {
		return raft;
	}

	/**
	 * Sets the amount of rafts the hiker has
	 * 
	 * @param raft the raft to set
	 */
	public void setRaft(int raft) {
		this.raft = raft;
	}

	/**
	 * Returns the amount of axes the hiker has
	 * 
	 * @return the axe
	 */
	public int getAxe() {
		return axe;
	}

	/**
	 * Sets the number of axes the hiker has
	 * 
	 * @param axe the axe to set
	 */
	public void setAxe(int axe) {
		this.axe = axe;
	}

}
