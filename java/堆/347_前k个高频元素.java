
//官方使用小顶堆，优化过的解法，当堆顶的大小比k小，则判断堆顶元素与k的大小，大于k，将堆顶元素弹出，将当前元素加入堆中。否则，堆的大小小于k，则将当前元素加入堆中。
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurances = new HashMap<>();//hash获取频次
        for(int num : nums){
            occurances.put(num, occurances.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int []>(){//利用PriorityQueue构建小顶堆
            public int compare(int[] m, int[] n){
                return m[1] - n[1];
            }
        });

        for(Map.Entry<Integer, Integer> entry : occurances.entrySet()){//使用Entry遍历hash
            int num = entry.getKey(), count = entry.getValue();//注意需要getKey方法
            if(queue.size() == k){//由于堆大小是从0开始构建的，所以慢慢变大到k，不需要再判断大于k的情况了
                if(queue.peek()[1] < count){
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            }else{
                queue.offer(new int[]{num, count});
            }
        }

        int[] ans = new int [k];
        for(int i = 0; i < k; i++){
            ans[i] = queue.poll()[0];
        }

        return ans;
    }
}

//下面是灵神的桶排序的解法：贴这个主要是为了记录桶排序的思想（当然这道题不是标准的桶排序，标准的是每个桶里进行排序，然后合并）
//这道题思路：先hash获取频次，然后根据频次创建桶，桶里存放相同频次的数，然后倒序遍历桶，将前k大的数加入答案。
//精髓就是频次恰好是数组下标，桶是使用类似于hash的底层结构那样，数组的每个元素是list。
//由于maxCnt需要存储且频次最小是1没有0，所以数组需要开maxCnt+1
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for(int x : nums){
            cnt.merge(x, 1, Integer::sum);//cnt[x]++
        }

        int maxCnt = Collections.max(cnt.values());

        List<Integer>[] buckets = new ArrayList[maxCnt + 1];
        Arrays.setAll(buckets, i -> new ArrayList());
        for(Map.Entry<Integer, Integer> entry : cnt.entrySet()){
            buckets[entry.getValue()].add(entry.getKey());//相同频次的数放在一个数组的列表里
        }

        //倒序buckets，将前k大的元素加入答案
        int[] ans = new int[k];
        int j = 0;
        for(int i = maxCnt; i >= 0 && j < k; i-- ){
            for(int x : buckets[i]){
                ans[j++] = x;
            }
        }
        return ans;
    }
}