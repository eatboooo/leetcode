package cn.etaboooo.demo.algorithm;

import java.util.HashMap;

import cn.etaboooo.bean.TreeNode;

/**
 * @author weiZhiLin
 * @version 1.0
 * @date 2019/11/19 14:55
 */
/*
105. 从前序与中序遍历序列构造二叉树

输入
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：
    3
   / \
  9  20
    /  \
   15   7
 */
public class Demo02 {
    int p_index = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] preorder;
    int[] inorder;

  /*  private TreeNode TreeHelper(int l, int r) {
        if (l == r) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[p_index]);
        Integer rootVal = map.get(preorder[p_index]);
        p_index++;
        root.left=TreeHelper(l,rootVal);
        root.right=TreeHelper(rootVal+1,r);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        int index = 0;
        for (Integer i : inorder) {
            map.put(i, index++);
        }
        return TreeHelper(0, preorder.length);
    }
*/
    /*public static void main(String[] args) {
        int[] a = {3, 9, 20, 15, 7};
        int[] b = {9, 3, 15, 20, 7};
        buildTree(a, b);
    }*/
}
