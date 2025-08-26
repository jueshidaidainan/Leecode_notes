//给定一个字符串，看这个字符串能不能拆分为字典里的单词。
//f[i]表示字符串中长度为i的前缀能不能被字典中的单词成功分割。枚举状态的赋值f[i]的时候，需要考虑将i的前缀和剩余部分是否在字典中。
//而判断是否在字典中的问题，如果我们枚举字典的中的单词，我们发现字典中的单词最多有1000个，但是字典中单词长度最长只有20。如果从这个角度枚举，
//只需要j >= Math.max(0, i - maxLen)就可。

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        Set<String> words = new HashSet<>(wordDict);//创建一个hashSet，用于存储单词，几乎collections框架的集合类都提供了接收其他集合作为参数的构造方法
        int n = s.length();
        boolean f[] = new boolean[n + 1];
        f[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= Math.max(0, i - maxLen); j--) {//类似于剪枝，最大的字串枚举到最大长度，写j>=0,我试了也能过，就是慢点
                if (f[j] && words.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }

            }
        }
        return f[n];
    }
}