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
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean l = false, r = false;

        if (root.left != null) {
            if (root.left.val < root.val) {
                l =  isValidBST(root.left);
            } else {
                l = false;
            }
        }else{
            l =  true;
        }

        if (root.right != null) {
            if (root.right.val > root.val) {
                r =  isValidBST(root.right);
            } else {
                r = false;
            }
        }else{
            r = true;
        }
        System.out.println(l + " " +  r);
        return l && r ;
    }
    // 这样写每次只是判断该节点下的左右节点，是否满足二叉搜索树的要求，但是真正的二叉搜索树是中序遍历的顺序，我的写法对于[5,4,6,null,null,3,7]这个树是不满足的
}

//灵山大人的先序遍历；不仅保证左子树小于根节点，右子树大于根节点，还保证左子树和右子树也是二叉搜索树
//重点是在遍历的时候进行边界的传递
class Solution {
    public boolean isValidBST(TreeNode root) {
      return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean isValidBST(TreeNode node, long left, long right){
        if(node == null){
            return true;
        }
        // long x = node.val;
        return left < node.val && node.val < right 
        && isValidBST(node.left, left, node.val) 
        && isValidBST(node.right, node.val, right);
    } 
}