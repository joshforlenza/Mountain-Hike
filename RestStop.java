package project5;

/**
 * This class represents a rest stop on a trail down a mountain. It stores the
 * label of the rest stop and a list of supplies and obstacles that a hiker can
 * collect at it.
 * 
 * @author Joshua Forlenza
 *
 */

public class RestStop implements Comparable<RestStop> {

	private String label;
	private int food;
	private int raft;
	private int axe;
	private int fallenTree;
	private int river;

	/**
	 * Creates a new RestStop object with the specified label
	 * 
	 * @param label label of the rest stop
	 * @throws IllegalArgumentException if label is empty
	 */
	public RestStop(String label) throws IllegalArgumentException {
		if (label.equals("")) {
			throw new IllegalArgumentException("Invalid label: Label cannot be an empty string");
		}

		this.label = label;
		this.food = 0;
		this.raft = 0;
		this.axe = 0;
		this.fallenTree = 0;
		this.river = 0;
	}

	/**
	 * Creates a new RestStop object with the specified label, supplies, and
	 * obstacles.
	 * 
	 * @param label      label of the rest stop
	 * @param food       amount of food at the rest stop
	 * @param raft       amount of rafts at the rest stop
	 * @param axe        amount of axes at the rest stop
	 * @param fallenTree amount of fallen trees at the rest stop
	 * @param river      amount of rivers at the rest stop
	 * @throws IllegalArgumentException if label is empty
	 */
	public RestStop(String label, int food, int raft, int axe, int fallenTree, int river)
			throws IllegalArgumentException {
		if (label.equals("")) {
			throw new IllegalArgumentException("Invalid label: Label cannot be an empty string");
		}

		this.label = label;
		this.food = food;
		this.raft = raft;
		this.axe = axe;
		this.fallenTree = fallenTree;
		this.river = river;
	}

	/**
	 * Returns the label of the RestStop object
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label of the RestStop object
	 * 
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Returns the amount of food at this RestStop object
	 * 
	 * @return the food
	 */
	public int getFood() {
		return food;
	}

	/**
	 * Sets the amount of food at this RestStop object
	 * 
	 * @param food the food to set
	 */
	public void setFood(int food) {
		this.food = food;
	}

	/**
	 * Returns the amount of rafts at this RestStop object
	 * 
	 * @return the raft
	 */
	public int getRaft() {
		return raft;
	}

	/**
	 * Sets the amount of rafts at this RestStop object
	 * 
	 * @param raft the raft to set
	 */
	public void setRaft(int raft) {
		this.raft = raft;
	}

	/**
	 * Returns the amount of axes at this RestStop object
	 * 
	 * @return the axe
	 */
	public int getAxe() {
		return axe;
	}

	/**
	 * Sets the amount of axes at this RestStop object
	 * 
	 * @param axe the axe to set
	 */
	public void setAxe(int axe) {
		this.axe = axe;
	}

	/**
	 * Returns the amount of fallen trees at this RestStop object
	 * 
	 * @return the fallenTree
	 */
	public int getFallenTree() {
		return fallenTree;
	}

	/**
	 * Sets the amount of fallen trees at this RestStop object
	 * 
	 * @param fallenTree the fallenTree to set
	 */
	public void setFallenTree(int fallenTree) {
		this.fallenTree = fallenTree;
	}

	/**
	 * Returns the amount of rivers at this RestStop object
	 * 
	 * @return the river
	 */
	public int getRiver() {
		return river;
	}

	/**
	 * Sets the amount of rivers at this RestStop object
	 * 
	 * @param river the river to set
	 */
	public void setRiver(int river) {
		this.river = river;
	}

	/**
	 * Compares this object with the specified object for order. Order is determined
	 * by the alphanumeric comparison of the object's label.
	 * 
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object is
	 *         less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(RestStop o) {
		if (this.label.compareTo(o.label) != 0) {
			return this.label.compareTo(o.label);
		}
		return 0;
	}

	/**
	 * Returns the string representation of this RestStop object.
	 * 
	 * @returns the string representation of this RestStop object
	 */
	@Override
	public String toString() {
		String line1 = this.label;
		return line1;
	}

}
