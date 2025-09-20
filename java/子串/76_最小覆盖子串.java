//给定字符串s和t，就是找s的字串中覆盖了t的所有字母的最小字串（不要求顺序一致）
//字符串里大小写字母都有
//使用滑动窗口的思想，枚举窗口的右端点，判断是否已经覆盖，若是覆盖了，则更新最小值，并左端点右移。
//注意：为了方便我们使用了128长度的数组，包含了ASCII码的所有值，但是判断是否覆盖的逻辑时，只需要遍历大小字母即可。
//      初始值ansRight 和ansLeft初始值都为-1和m-1，初始设置一个不合逻辑的值，后续一旦有符合条件的right和left，则更新ansLeft和ansRight。

class Solution {
    public String minWindow(String S, String t) {
        int m = S.length();
        int n = t.length();

        int[] cntS = new int[128];
        int[] cntT = new int[128];
        for(char c : t.toCharArray()){
            cntT[c]++;
        }

        char[] s = S.toCharArray();
        int ansLeft = -1;//初始值都在s的边界外，方便求最小 
        int ansRight = m;
        
        for(int right = 0, left = 0; right < m; right++){//枚举字串的右端点
            cntS[s[right]]++;//窗口右端移动
            while(isCovered(cntS, cntT)){
                if(right - left < ansRight - ansLeft){
                    ansLeft = left;
                    ansRight = right;
                }
                cntS[s[left]]--;//窗口左端点右移
                left++;
            }
        }
        return ansLeft < 0 ? "" : S.substring(ansLeft, ansRight + 1);
    }

    boolean isCovered(int [] cntS, int [] cntT){
        for(int i = 'A'; i <= 'Z'; i++){
            if(cntT[i] > cntS[i]) return false;
        }
        for(int i = 'a'; i <= 'z'; i++){
            if(cntT[i] > cntS[i]) return false;
        }
        return true;
    }
}