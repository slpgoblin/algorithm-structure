package 二叉树;

import java.util.ArrayList;
import java.util.List;

/**
 * @author goblin
 * @version 1.0.0
 * @className 二叉树._144_二叉树的前序遍历
 * @description https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * @createTime 2021-06-29 22:55
 */
public class _144_二叉树的前序遍历 {

    //递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> valueList = new ArrayList<>();
        return recursion(root, valueList);
    }

    public List<Integer> recursion(TreeNode node, List<Integer> valueList) {
        if (node == null) {
            return valueList;
        }
        valueList.add(node.val);
        recursion(node.left, valueList);
        recursion(node.right, valueList);
        return valueList;
    }

    //TODO 迭代


}
