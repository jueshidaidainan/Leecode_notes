//这道题和之前的跳跃游戏其实很像，要求同一个字母只能出现在一个区间里。
//思路：
//1. 创建一个26长度的数组，记录每个字母最后出现的下标
//2. 创建一个变量start和end，start表示当前区间的起始下标，end表示当前区间的结束下标，在遍历字符串的时候维护start和end，end要表示当前字串所包含的字母最后出现的最远的下标。
//3. 当i==end的时候，说明当前区间结束。ans.add(end - start + 1);



class Solution {
    public List<Integer> partitionLabels(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int [] last = new int[26];

        for(int i = 0; i < n; i++){
            last[chs[i] - 'a'] = i;//每个字母最后出现的下标
        }

        List<Integer> ans = new ArrayList<>();
        int start = 0, end = 0;
        for(int i = 0; i < n; i++){
            end = Math.max(last[chs[i] - 'a'], end);//更新区间右端点的最大值
            if(i == end){//当前的字母处理完毕
                ans.add(end - start + 1);//加入答案
                start = end + 1;//维护开始指针，下一个字串
            }
        }

        return ans;
    }
}