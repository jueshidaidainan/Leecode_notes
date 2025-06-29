/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int ans = -1;

    public int kthSmallest(TreeNode root, int k) {
        DFS(root, 1, k);
        return ans;
    }

    private int DFS(TreeNode node, int cnt, int k) {
        if (node == null || cnt == k + 1) { //找到之后就返回，避免过多遍历
            return cnt;
        } 
        int l = DFS(node.left, cnt, k);//递归左边
        if (l == k) {//访问节点
            ans = node.val;
        }
        l++;//每访问一个节点，序数递增
        return DFS(node.right, l, k);
    }
}