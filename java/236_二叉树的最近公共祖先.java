/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//使用递归来解。重点是递归的滚动逻辑和返回值。
//要分类讨论：首先返回值是什么：对于最外层的调用者来说，是最近公共祖先。若是p和q在同一个子树里（比如p在q的子树里），找到哪个就返回哪个就行，不一样的是二者的最近公共祖先是一个新的节点，那就是第一中情况了。
//至于分类讨论其实就是下面4种情况。
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q){
            return root;//找到p或者q之后就不用往下递归了。如果找到了p，那么q在下面，公共祖先是p，不需要遍历。q不在下面，也不需要遍历了。
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){//1.左右都有，当前节点是最近的公共祖先
            return root;
        }
        // 2.如果只有左子树找到，就返回左子树的返回值
        // 3.如果只有右子树找到，就返回右子树的返回值
        // 4.如果左右子树都没有找到，就返回 null（注意此时 right = null）
        return left != null ? left : right;
    }
}

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