class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int [] cntP = new int [26];
        int [] cntS = new int [26];
        for(char c : p.toCharArray()){
            cntP[c - 'a']++;
        }

        for(int right = 0; right < s.length(); right++){//枚举右端点
            cntS[s.charAt(right) - 'a']++;//加进去
            int left = right - p.length() + 1;//左端点
            if(left < 0){//窗口没满
                continue;
            }
            if(Arrays.equals(cntP, cntS)){
                ans.add(left);
            }
            cntS[s.charAt(left) - 'a']--;//无论匹配与否，窗口都会右移
        }
        return ans;
    }
}