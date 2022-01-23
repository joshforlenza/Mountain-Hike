package project5;

import java.util.*;

/**
 * This class is an implementation of a balanced binary search tree using AVL
 * rules. The BST represents a mountain that is able to be traversed by a Hiker
 * object. For this reason, it is limited to storing RestStop objects in its
 * nodes.
 * 
 * Based off of BST class lecture code developed by Joanna Klukowska
 * 
 * @author Joshua Forlenza and Joanna Klukowska
 *
 */

public class BSTMountain<T extends Comparable<RestStop>> {

	private BSTNode root; // reference to the root node of the tree
	private int size;

	private boolean added; // helper variable used by the add method

	/**
	 * Constructs a new, empty tree, sorted according to the natural ordering of its
	 * elements.
	 */
	public BSTMountain() {
		root = null;
		this.size = 0;
	}

	/**
	 * Adds the specified element to this tree if it is not already present. If this
	 * tree already contains the element, the call leaves the tree unchanged and
	 * returns false. Tree is rebalanced if the added element throws it out of
	 * balance.
	 * 
	 * @param data element to be added to this tree
	 * @return true if this tree did not already contain the specified element
	 * @throws NullPointerException if the specified element is null
	 */
	public boolean add(RestStop data) {
		added = false;
		if (data == null)
			throw new NullPointerException("null value found");
		// replace root with the reference to the tree after the new
		// value is added
		root = add(data, root);
		return added;
	}

	/**
	 * Actual recursive implementation of add.
	 *
	 * This function returns a reference to the subtree in which the new value was
	 * added.
	 *
	 * @param data element to be added to this tree
	 * @param node node at which the recursive call is made
	 */
	private BSTNode add(RestStop data, BSTNode node) {
		if (node == null) {
			added = true;
			this.size++;
			return new BSTNode(data);
		}

		int comp = 0;
		comp = node.data.compareTo(data);
		// find the location to add the new value
		if (comp > 0) { // add to the left subtree
			node.left = add(data, node.left);
			updateHeight(node.left);
		} else if (comp < 0) { // add to the right subtree
			node.right = add(data, node.right);
			updateHeight(node.right);
		} else { // duplicate found, do not add
			added = false;
			// return node;
		}

		updateHeight(node);

		// checks if tree is out of balance
		if (balanceFactor(node) == -2) {
			if (balanceFactor(node.left) == 1) {
				// LR rotation
				node = balanceLR(node);
			} else {
				// LL rotation
				node = balanceLL(node);
			}
		}
		if (balanceFactor(node) == 2) {
			if (balanceFactor(node.right) == -1) {
				// RL rotation
				node = balanceRL(node);
			} else {
				// RR rotation
				node = balanceRR(node);
			}
		}

		return node;
	}

	/**
	 * Updates the height of the node based off its children
	 *
	 * @param node node whose height will be updated
	 */
	private void updateHeight(BSTNode node) {
		// node with no children is set to a height of 0
		if (node.left == null && node.right == null) {
			node.height = 0;
		}

		// height of node with a right child is determined by the height of its child
		else if (node.left == null) {
			node.height = node.right.height + 1;
		}

		// height of node with a left child is determined by the height of its child
		else if (node.right == null) {
			node.height = node.left.height + 1;
		}

		// height of node with a two children is determined by which child has the max
		// height
		else {
			node.height = 1 + Math.max(node.left.height, node.right.height);
		}
	}

	/**
	 * Calculates the balance factor of a node Balance factor is determined by
	 * subtracting the height of the node's left subtree from the height of the
	 * node's right subtree.
	 *
	 * @param n node whose balanceFactor will be calculated
	 */
	private int balanceFactor(BSTNode n) {
		if (n.right == null) {
			return -n.height;
		}
		if (n.left == null) {
			return n.height;
		}

		return n.right.height - n.left.height;
	}

	/**
	 * Performs a LL rotation on an unbalanced node A whose left subtree B has a
	 * greater height than its right. To perform the rotation, the A node is pushed
	 * down, and its left child B becomes the new root.
	 *
	 * @param A node that is out of balance
	 */
	private BSTNode balanceLL(BSTNode A) {
		BSTNode B = A.left;

		A.left = B.right;
		B.right = A;

		updateHeight(A);
		updateHeight(B);

		return B;
	}

	/**
	 * Performs a LR rotation on an unbalanced node A whose left subtree B has a
	 * greater height than its right, and B's right subtree C is larger than its
	 * left. To perform the rotation, the C node is pulled up, the A node is set to
	 * the right of C, and the B node is set to the left of C.
	 *
	 * @param A node that is out of balance
	 */
	private BSTNode balanceLR(BSTNode A) {
		BSTNode B = A.left;
		BSTNode C = B.right;

		A.left = C.right;
		B.right = C.left;
		C.left = B;
		C.right = A;

		updateHeight(A);
		updateHeight(B);
		updateHeight(C);

		return C;
	}

	/**
	 * Performs a RR rotation on an unbalanced node A whose right subtree B has a
	 * greater height than its left. To perform the rotation, the A node is pushed
	 * down, and its right child B becomes the new root.
	 *
	 * @param A node that is out of balance
	 */
	private BSTNode balanceRR(BSTNode A) {
		BSTNode B = A.right;

		A.right = B.left;
		B.left = A;

		updateHeight(A);
		updateHeight(B);

		return B;
	}

	/**
	 * Performs a RL rotation on an unbalanced node A whose right subtree B has a
	 * greater height than its left, and B's left subtree C is larger than its
	 * right. To perform the rotation, the C node is pulled up, the A node is set to
	 * the left of C, and the B node is set to the right of C.
	 *
	 * @param A node that is out of balance
	 */
	private BSTNode balanceRL(BSTNode A) {
		BSTNode B = A.right;
		BSTNode C = B.left;

		A.right = C.left;
		B.left = C.right;
		C.right = B;
		C.left = A;

		updateHeight(A);
		updateHeight(B);
		updateHeight(C);

		return C;
	}

	/**
	 * Simulates a hiker traversing down a mountain. When a Hiker object is passed
	 * into a method, all possible paths down the mountain are explored. Hiker
	 * traverses by moving from node to node, an uses food at each traversal. If the
	 * hiker runs out of food, he cannot continue down the path. RestStops may
	 * contain food, axes, and rafts that the Hiker can pick up and add into his
	 * inventory. RestStops may also contain obstacles that the hiker must pass. An
	 * axe is needed to continue past a fallen tree obstacle, and a raft is needed
	 * to cross a river obstacle. These items are single-use. A leaf at the highest
	 * level of the tree represents the bottom of the mountain. If the hiker reaches
	 * the bottom of the mountain, the path he took will be displayed.
	 * 
	 * @param h Hiker object that traverses down the mountain
	 */
	public void traverse(Hiker h) {
		ArrayList<String> path = new ArrayList<>();
		traverseRec(h, this.root, path);
	}

	/**
	 * Recursive implementation of the traverse method.
	 * 
	 * Method is continuously called until the hiker reaches the bottom of the
	 * mountain or cannot progress any further due to insufficient supplies.
	 *
	 *
	 * @param h    Hiker object that traverses down the mountain
	 * @param node node that the hiker is at
	 * @param path path that the hiker is currently on
	 */
	private void traverseRec(Hiker h, BSTNode node, ArrayList<String> path) {

		if (node != root) {
			// hiker uses one food resource at each traversal call
			h.setFood(h.getFood() - 1);
		}

		RestStop r = node.data;

		// process supplies
		h.setFood(h.getFood() + r.getFood());
		h.setRaft(h.getRaft() + r.getRaft());
		h.setAxe(h.getAxe() + r.getAxe());

		// process obstacles
		if (r.getFallenTree() > 0) {
			if (h.getAxe() < r.getFallenTree()) {
				return;
			} else {
				h.setAxe(h.getAxe() - r.getFallenTree());
			}
		}
		if (r.getRiver() > 0) {
			if (h.getRaft() < r.getRiver()) {
				return;
			} else {
				h.setRaft(h.getRaft() - r.getRiver());
			}
		}

		path.add(r.getLabel());
		// checks if end of path has been reached
		if (node.left == null && node.right == null) {
			// check if it is a cliff or bottom of mountain
			if (path.size() == root.height + 1) {
				for (int i = 0; i < path.size(); i++) {
					System.out.print(path.get(i) + " ");
				}
				System.out.println();
				path.remove(r.getLabel());
				return;
			} else {
				path.remove(r.getLabel());
				return;
			}

		}

		// hiker cannot progress if he has no food
		if (h.getFood() == 0) {
			path.remove(r.getLabel());
			return;
		}

		// this variable allows a hiker with the same data fields to be passed
		// into both calls to node.left and node.right
		// credit to classmate Harry Minsky for the idea
		Hiker duplicate = new Hiker();
		duplicate.setFood(h.getFood());
		duplicate.setAxe(h.getAxe());
		duplicate.setRaft(h.getRaft());

		if (node.left != null) {
			traverseRec(h, node.left, path);
		}

		if (node.right != null) {
			traverseRec(duplicate, node.right, path);
		}

		path.remove(r.getLabel());

	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {

		return this.size;
	}

	/**
	 * Returns the string representation of this BSTMountain.
	 * 
	 * @returns the string representation of this BSTMountain object
	 */
	public String toStringTree() {
		StringBuffer sb = new StringBuffer();
		toStringTree(sb, root, 0);
		return sb.toString();
	}

	/**
	 * Recursive implementation of toStringTree
	 * 
	 * Uses preorder traversal to display the tree; will not work if the
	 * data.toString returns more than one line.
	 * 
	 * @param sb    used to create string
	 * @param node  current node that is being converted to its String
	 *              representation.
	 * @param level current level of the tree
	 */
	private void toStringTree(StringBuffer sb, BSTNode node, int level) {
		// display the node
		if (level > 0) {
			for (int i = 0; i < level - 1; i++) {
				sb.append("   ");
			}
			sb.append("|--");
		}
		if (node == null) {
			sb.append("->\n");
			return;
		} else {
			sb.append(node.data + "\n");
		}

		// display the left subtree
		toStringTree(sb, node.left, level + 1);
		// display the right subtree
		toStringTree(sb, node.right, level + 1);
	}

	/**
	 * Node class for this BST Node is only capable of storing RestStop objects as
	 * its data.
	 * 
	 * @author Joshua Forlenza
	 * 
	 */
	private class BSTNode implements Comparable<BSTNode> {

		RestStop data;
		int height;
		BSTNode left;
		BSTNode right;

		/**
		 * Creates new BSTNode with specified RestStop data
		 * 
		 * @param data RestStop object that the node will store
		 * 
		 */
		public BSTNode(RestStop data) {
			this.data = data;
		}

		/**
		 * Creates new BSTNode with specified RestStop data, height, and left and right
		 * children.
		 * 
		 * @param data   RestStop object that the node will store
		 * @param height height of the node
		 * @param left   left child of the node
		 * @param right  right child of the node
		 * 
		 */
		public BSTNode(RestStop data, int height, BSTNode left, BSTNode right) {
			this.data = data;
			this.height = height;
			this.left = left;
			this.right = right;
		}

		/**
		 * Compares this object with the specified object for order.
		 * 
		 * @param o the object to be compared.
		 * @return a negative integer, zero, or a positive integer as this object is
		 *         less than, equal to, or greater than the specified object.
		 */
		public int compareTo(BSTNode other) {
			return this.data.compareTo(other.data);
		}

	}

}
