public  extra_100_相同的树 {
//两颗树 结构相同且值相同则为相同的树
}

//使用递归来解，判断两颗树当前节点是否相同，然后相应的判断左子树和右子树是否相同。树的结构是否相同的判断逻辑体现在递归的调用中。
//注意递归的出口写法很优雅。
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null){//为空的时候是边界，都为空则为true，否则false，p==q则是优雅写法
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
