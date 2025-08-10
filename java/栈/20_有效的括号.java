// 思路：遇见左括号就入栈，遇见右括号就出栈。出栈时比较栈顶元素，栈为空或者栈顶元素和当前右括号不匹配则返回false。
// 因为只有右边的括号配配的时候，是找和它最近的左括号是否匹配。（思想）
// 注意栈为空，但是字符串还没遍历完，以及字符串遍历完之后栈不为空的特例。也就是左右括号的个数不匹配。
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) { // s 长度必须是偶数
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>(s.length());
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (map.containsKey(x)) {//左括号入栈
                stack.push(x);
            } else {
                if (stack.isEmpty() || x != map.get(stack.pop())) {
                    return false;
                }
            }
        }
        // if (!stack.isEmpty())
        //     return false;
        return stack.isEmpty();
    }
}