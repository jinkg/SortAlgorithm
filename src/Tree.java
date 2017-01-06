import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 34921
 * 2017/1/6.
 */
public class Tree {

    private static final String SEPARATOR = " ";

    public static void main(String[] args) {
        TreeNode tree1 = buildTree1();
        TreeNode tree2 = buildTree2();

        traversal(tree1);
        traversal(tree2);
    }

    private static void traversal(TreeNode root) {

        System.out.println("Preorder:");
        preorderTraversal(root);
        System.out.println();
        preorderTraversalRecursive(root);
        System.out.println();

        System.out.println("Postorder:");
        postorderTraversal(root);
        System.out.println();
        postorderTraversalRecursive(root);
        System.out.println();

        System.out.println("Inorder:");
        inorderTraversal(root);
        System.out.println();
        inorderTraversalRecursive(root);
        System.out.println();

        System.out.println("BreadthFirst:");
        breadthFirstTraversal(root);

        System.out.println();
        System.out.println();
    }

    private static void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            System.out.print(node.name + SEPARATOR);
            node = node.left;
        }
        while (!stack.isEmpty()) {
            node = stack.pop();

            TreeNode tmp = node.right;
            while (tmp != null) {
                stack.push(tmp);
                System.out.print(tmp.name + SEPARATOR);
                tmp = tmp.left;
            }
        }
    }

    private static void preorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.name + SEPARATOR);
        preorderTraversalRecursive(root.left);
        preorderTraversalRecursive(root.right);
    }

    private static void postorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode node = root;
        while (node != null) {
            list.add(node);
            node = node.right;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            node = list.get(i);
            if (node.traversed) {
                continue;
            }
            node.traversed = true;
            TreeNode tmp = node.left;
            boolean incresedSize = tmp != null;
            while (tmp != null) {
                list.add(tmp);
                tmp = tmp.right;
            }
            if (incresedSize) {
                i = list.size();
            }
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i).name + SEPARATOR);
        }
    }

    private static void postorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        postorderTraversalRecursive(root.left);
        postorderTraversalRecursive(root.right);
        System.out.print(root.name + SEPARATOR);
    }

    private static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.name + SEPARATOR);

            TreeNode tmp = node.right;
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
        }
    }

    private static void inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursive(root.left);
        System.out.print(root.name + SEPARATOR);
        inorderTraversalRecursive(root.right);
    }

    private static void breadthFirstTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.name + SEPARATOR);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

    }

    /**
     * Preorder:        A C E M F D G H Z
     * Postrorder:      M E F C G Z H D A
     * Inorder:         E M C F A G D Z H
     * BreadthFirst:    A C D E F G H M Z
     *
     * @return root
     */
    private static TreeNode buildTree1() {
        TreeNode nodeA = new TreeNode("A");

        TreeNode nodeC = new TreeNode("C");
        TreeNode nodeD = new TreeNode("D");

        TreeNode nodeE = new TreeNode("E");
        TreeNode nodeF = new TreeNode("F");
        TreeNode nodeG = new TreeNode("G");
        TreeNode nodeH = new TreeNode("H");

        TreeNode nodeM = new TreeNode("M");
        TreeNode nodeZ = new TreeNode("Z");

        nodeA.left = nodeC;
        nodeA.right = nodeD;

        nodeC.left = nodeE;
        nodeC.right = nodeF;
        nodeD.left = nodeG;
        nodeD.right = nodeH;

        nodeE.right = nodeM;
        nodeH.left = nodeZ;

        return nodeA;
    }

    /**
     * Preorder:        A B D G K H L E I M J N C F
     * Postrorder:      K G L H D M I N J E B F C A
     * Inorder:         G K D L H B I M E N J A F C
     * BreadthFirst:    A B C D E F G H I J K L M N
     *
     * @return root
     */
    private static TreeNode buildTree2() {
        TreeNode nodeA = new TreeNode("A");

        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");

        TreeNode nodeD = new TreeNode("D");
        TreeNode nodeE = new TreeNode("E");
        TreeNode nodeF = new TreeNode("F");

        TreeNode nodeG = new TreeNode("G");
        TreeNode nodeH = new TreeNode("H");
        TreeNode nodeI = new TreeNode("I");
        TreeNode nodeJ = new TreeNode("J");

        TreeNode nodeK = new TreeNode("K");
        TreeNode nodeL = new TreeNode("L");
        TreeNode nodeM = new TreeNode("M");
        TreeNode nodeN = new TreeNode("N");

        nodeA.left = nodeB;
        nodeA.right = nodeC;

        nodeC.left = nodeF;

        nodeB.left = nodeD;
        nodeB.right = nodeE;

        nodeD.left = nodeG;
        nodeD.right = nodeH;
        nodeE.left = nodeI;
        nodeE.right = nodeJ;

        nodeG.right = nodeK;
        nodeH.left = nodeL;
        nodeI.right = nodeM;
        nodeJ.left = nodeN;

        return nodeA;
    }

    static class TreeNode {
        String name;

        boolean traversed = false;

        TreeNode(String name) {
            this.name = name;
        }

        TreeNode left;
        TreeNode right;
    }
}
