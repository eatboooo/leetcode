package cn.eatboooo.bean;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2019/11/11 16:58
 */
public class TreeNode {

    public Integer val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode arrAsTree(Integer[] arr) {
        TreeNode treeNode = new TreeNode();
        arrAsTree(arr, treeNode, 0);
        return treeNode;
    }

    private static void arrAsTree(Integer[] arr, TreeNode treeNode, int index) {
        if (arr.length <= index) return;
        if (null == arr[index]) {
            return;
        }
        treeNode.val = arr[index];
        if (arr.length > index * 2  + 1) {
            treeNode.left = new TreeNode();
            arrAsTree(arr, treeNode.left, index * 2 + 1);
        }
        if (arr.length > index * 2 + 2) {
            treeNode.right = new TreeNode();
            arrAsTree(arr, treeNode.right, index * 2 + 2);
        }
    }
}
