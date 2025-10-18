public class 101_对称二叉树 {
//判断一个树是不是对称的二叉树，一定有根结点。 
}

///和100_相同的树一样，可以认为相同的树是母题，对称的树只需要判断左右子树是否相同即可。
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    private boolean isSameTree(TreeNode p, TreeNode q){
        if(p == null || q == null){
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
}