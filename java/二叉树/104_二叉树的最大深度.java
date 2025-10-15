public class 104_二叉树的最大深度 {
    
}

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

 // 这是优美的写法  后序遍历，每个节点的深度由左右子树中最深的那个决定，为 null，则深度为 0.
 class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;//为空，则深度为 0

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;//滚动起来让深度
    }
}

 //这是我自己写的，代码太啰嗦了，ans和递归返回值 return 冲突了
class Solution {
    private int ans = -1;
    public int maxDepth(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode node, int depth){
        if(node == null) return depth;
        depth++;
        int depthLeft = dfs(node.left, depth);
        int depthRight = dfs(node.right, depth);
        ans = Math.max(depthLeft, Math.max(depthRight, ans));
        return Math.max(depthLeft, depthRight);
    }
}