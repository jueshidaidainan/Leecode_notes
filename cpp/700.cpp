/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

/*
二叉搜索树：左子树小于根节点 右子树大于根节点
下面的写法是递归写法：
注意递归出口,一是遇到的节点值等于所搜索的值，另一个是当前节点为空即已经到头了
*/ 
class Solution {
public:
    TreeNode* searchBST(TreeNode* root, int val) {
        if( root == nullptr|| root->val == val ) return root;
        TreeNode* res = nullptr;
        if(root->val < val) res = searchBST(root->right, val);
        if(root->val > val) res = searchBST(root->left, val);
        return res;
    }
};