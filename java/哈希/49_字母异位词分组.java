public class 49_字母异位词分组 {
    
}

//要判断字母异位词且将相同的字母放在一起进行返回。
//首先字母异位词利用char数组进行排序，然后创建新的字符串，这样异位的单词最终会变成同一个字符串，作为key存入map中。
//根据这个逻辑遍历输入的字符串数组，将其加入map。map的key是排序后的字符串，value是相同的字符串组成的list。
//注意在这里可以学到map的put和getOrDefault方法以及computeIfAbsent方法。
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);

            // List<String> list = map.getOrDefault(key, new ArrayList());
            // list.add(str);
            // map.put(key, list);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList(map.values());
    }
}