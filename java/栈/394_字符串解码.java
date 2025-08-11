//第一个是灵神的递归写法，第二个是双栈的写法
//思路：识别左括号前的数字，然后识别左括号后的字符，遇到右括号的时候，将这部分的字符重复并和之前的结果进行拼接。
//这两个思路也体现了其实递归的底层逻辑就是栈（计算机物理的实现方式）
}
class Solution {
    public String decodeString(String s) {
        return decode(s.toCharArray());
    }

    private int i = 0;
    private String decode(char[] s){
        StringBuilder res = new StringBuilder();
        int k = 0;
        while(i < s.length){
            char c = s[i];
            i++;
            if(Character.isLetter(c)){
                res.append(c);
            }else if(Character.isDigit(c)){
                k = k * 10 + c - '0';
            }else if(c == '['){//递归
                res.repeat(decode(s), k);//重复
                k = 0;//有点类似于恢复现场，避免k的旧值污染
            }else break;//']'        
        return res.toString();
    }
}


class Solution {
    public String decodeString(String s) {
        Deque<Integer> countStack = new ArrayDeque<>(); // 存储数字
        Deque<String> stringStack = new ArrayDeque<>(); // 存储字符串
        String currentString = ""; // 当前解码字符串
        int k = 0; // 当前的倍数

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0'); // 处理多位数
            } else if (ch == '[') {
                // 遇到 '['，将当前的字符串和数字推入各自的栈
                countStack.push(k);
                stringStack.push(currentString);
                currentString = ""; // 重置当前字符串
                k = 0; // 重置倍数
            } else if (ch == ']') {
                // 遇到 ']'，解码
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(currentString); // 重复当前字符串
                }
                currentString = temp.toString(); // 更新当前字符串
            } else {
                // 如果是字母，直接加到当前字符串
                currentString += ch;
            }
        }

        return currentString;
    }
}