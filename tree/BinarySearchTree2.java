package check_list;

import check_list.BinarySearchTree;
import check_list.BinarySearchTree.TreeNode;

public class BinarySearchTree
{

	private TreeNode root;

	private class TreeNode
	{
		private int data; // Generic type
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int data)
		{
			this.data = data;
		}
	}

	public void insert(int value)
	{
		root = insert(root, value);
	}

	private TreeNode insert(TreeNode root, int value)
	{
		if (root == null)
		{
			root = new TreeNode(value);
			return root;
		}

		if (value < root.data)
		{
			root.left = insert(root.left, value);
		} else
		{
			root.right = insert(root.right, value);
		}
		return root;
	}

	public void inOrder()
	{
		inOrder(root);
	}

	private void inOrder(TreeNode root)
	{
		if (root == null)
		{
			return;
		}
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}

	public TreeNode search(int key)
	{
		return search(root, key);
	}

	private TreeNode search(TreeNode root, int key)
	{
		if (root == null || root.data == key)
		{ // base case 
			return root;
		}

		if (key < root.data)
		{
			return search(root.left, key);
		} else
		{
			return search(root.right, key);
		}

	}

	
	public int getMax()
	{
		return getMax(root);
	}
	private int getMax(TreeNode root) {
		if(root == null)
			return Integer.MIN_VALUE;
		while (root.right != null) {
			root = root.right;
		}
		return root.data;
	}
	
	
	public int getMin()
	{
		return getMin(root);
	}
	private int getMin(TreeNode root) {
		if(root == null)
			return Integer.MAX_VALUE;
		while (root.left != null) {
			root = root.left;
		}
		return root.data;
	}


	public TreeNode deleteNode(int key)
	{
		return deleteNode(root, key);
	}

	public TreeNode deleteNode(TreeNode root, int key)
	{
		if (root == null)
			return root;
		if (key > root.data)
		{ // move right
			root.right = deleteNode(root.right, key);
		} else if (key < root.data)
		{ // move left
			root.left = deleteNode(root.left, key);
		} else
		{ // oh yes, we finally found the target
			if (root.left == null && root.right == null)
			{ // hmm, its a leaf node; easy peasy
				root = null;
			} else if (root.right != null)
			{ // oh, it has a right child, don't make it an orphan or is it old enough to
				// become a parent ? lets find out
				root.data = successor(root); // my worthy successor
				root.right = deleteNode(root.right, root.data);
			} else
			{ // oh it seems that I do not have a worthy successor, fallback, fallback ...
				root.data = predecessor(root);
				root.left = deleteNode(root.left, root.data);
			}
		}
		return root;
	}

	private int predecessor(TreeNode root)
	{
		root = root.left;
		while (root.right != null)
		{
			root = root.right;
		}
		return root.data;
	}

	private int successor(TreeNode root)
	{
		root = root.right;
		while (root.left != null)
		{
			root = root.left;
		}
		return root.data;
	} 
	
	public static void main(String[] args)
	{
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(5);
		bst.insert(3);
		bst.insert(7);
		bst.insert(1);

		bst.inOrder();

		System.out.println();
		System.out.println(bst.getMax());
		System.out.println(bst.getMin());

		if (null != bst.search(1))
		{
			System.out.println("Key found !!!");
		} else
		{
			System.out.println("Key not found !!!");
		}

		//bst.deleteNode(5);
		bst.inOrder();

	}
}