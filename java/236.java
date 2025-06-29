/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 给定两个节点的最近公共祖先
// 节点的值都是不重复的，所以可以利用逻辑判断来解
// 由于是深度最深的最近公共祖先，所以使用的是深搜遍历

class Solution {
    // private HashMap<TreeNode, Integer> map  = new HashMap<>();
    private TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        DFS(root, p, q);
        return ans;
    }
    private boolean DFS(TreeNode node, TreeNode p, TreeNode q){
        if(node == null) return false;
        boolean fl = DFS(node.left, p, q);
        boolean fr = DFS(node.right, p, q);
        if((fl && fr) || ((node.val == p.val || node.val == q.val)&& (fl || fr))){
            ans = node;
        }
        return (fl || fr || (node.val == p.val || node.val == q.val));
    }
}