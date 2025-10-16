public class 226_翻转二叉树 {
//将二叉树的左右节点交换
}

//二叉树的问题，很容易想到使用递归来解。交换左右节点，我们很容易想到直接交换左右子树的指针即可，那左右子树都存在的时候很容易交换，左右子树有一个为null或者都为null的时候需不需要特判？
//当我们写出递归出口的时候发现是不需要的，因为当root不为null的时候，左右子树要是为null这是可以访问的，而且与null进行交换也是符合题意的。
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode tmp;
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

}