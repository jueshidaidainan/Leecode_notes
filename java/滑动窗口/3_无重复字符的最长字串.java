public class 3_无重复字符的最长字串 {
    
}

//使用同向滑动窗口来解，枚举右指针，保证滑动窗口里的字符串是没有重复字符的（用hashmap来保证，存储字符和出现的次数）。当有字符的次数大于1，只能是右移右指针的当下字符导致的，所以我们左指针右移，直到窗口内当下的字符次数==1.
//在这个过程中维护ans即可。
class Solution {
    public int lengthOfLongestSubstring(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int right = 0; right < s.length; right++){
            map.merge(s[right], 1, Integer::sum);
            while(map.get(s[right]) > 1){
                map.merge(s[left], -1, Integer::sum);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

//优化版本，使用map存储字符出现的位置，当字符出现的位置在当前窗口内，则更新left指针。
//注意由于map里存的是目前遇到的所有字符，所以map里存在的字符，可能现在没在窗口内，所以要和当前的left也就是窗口左指针求最大。
class Solution {
    public int lengthOfLongestSubstring(String S) {
        char[] s = S.toCharArray();
        int ans = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int right = 0; right < s.length; right++){
            if(map.containsKey(s[right])){
                left = Math.max(left, map.get(s[right]) + 1);
            }
            map.put(s[right], right);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}