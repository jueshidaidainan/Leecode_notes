//这道题使用的思路是中心扩展：分别考虑中心对称的初始字符串是奇数还是偶数的情况。然后看左右两边的字符串是否相等，相等则左右两边各自移动。与其说像是动态规划，我更觉得像是双指针（虽然标签里确实有）
//奇数情况，所代表的中心点是每个字符总共有n个，偶数情况，中心点是虚拟的空隙，总共有n-1种。
//所以可以分别遍历取最大值，也可以利用将这2n-1个中心点合在一起遍历，利用/2是向下取整的这个技巧，当i为奇数和偶数是其实也就是分开遍历的情况。

//合在一起遍历
public class Solution {
    public String longestPalindrome(String s) {  
        char [] S = s.toCharArray();
        int n = S.length;
        int ansLeft = 0;
        int ansRight = 0;

        for(int i = 0; i < 2 * n - 1; i++){//2n - 1个虚拟中心点
            int l = i / 2;
            int r = (i + 1) / 2;
            while(l >= 0 && r < n && S[l] == S[r]){//中心扩散的条件
                l--;
                r++;
            }
            if(ansRight - ansLeft < r - l - 1){//退出while循环之后，l和r都指向了不符合条件的下一个位置。所以r - l - 1也需要长度-1，来对其一下所表示的字符串的长度
                ansLeft = l + 1;                //我们调用substring方法，是左开右闭的方法，所以l需要加1，r不用。
                ansRight = r;
            }
        }
        return s.substring(ansLeft, ansRight);
    }
} 


//分开遍历
class Solution {
    public String longestPalindrome(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int ansLeft = 0;
        int ansRight = 0;

        // 奇回文串
        for (int i = 0; i < n; i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            // 循环结束后，s[l+1] 到 s[r-1] 是回文串
            if (r - l - 1 > ansRight - ansLeft) {
                ansLeft = l + 1;
                ansRight = r; // 左闭右开区间
            }
        }

        // 偶回文串
        for (int i = 0; i < n - 1; i++) {
            int l = i;
            int r = i + 1;
            while (l >= 0 && r < n && s[l] == s[r]) {
                l--;
                r++;
            }
            if (r - l - 1 > ansRight - ansLeft) {
                ansLeft = l + 1;
                ansRight = r; // 左闭右开区间
            }
        }

        return S.substring(ansLeft, ansRight);
    }
}

