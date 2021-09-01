package com.siwuxie095.algorithm.chapter1st.example20th;

/**
 * @author Jiajing Li
 * @date 2021-09-01 23:33:06
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 数据结构设计：最大栈
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 895.最大频率栈（困难）
     *
     * 个人很喜欢设计特殊数据结构的问题，毕竟在工作中会经常用到基本数据结构，而设计类的问题就非常考验对基本
     * 数据结构的理解和运用。
     *
     * 力扣第 895 题要求实现一个特殊的数据结构「最大频率栈」，比较有意思，下面来实现这两个 API：
     *
     * class FreqStack {
     *
     *     // 在栈中加入一个元素 val
     *     public void push(int val) {}
     *
     *     // 从栈中删除并返回出现频率最高的元素
     *     // 如果频率最高的元素不止一个，
     *     // 则返回最近添加的那个元素
     *     public int pop() {}
     * }
     *
     * 比如下面这个例子：
     *
     * FreqStack stk = new FreqStack();
     *
     * // 向最大频率栈中添加元素
     * stk.push(2); stk.push(7); stk.push(2);
     * stk.push(7); stk.push(2); stk.push(4);
     *
     * // 栈中元素：[2,7,2,7,2,4]
     *
     * stk.pop() // 返回 2
     * // 因为 2 出现了三次
     *
     * // 栈中元素：[2,7,2,7,4]
     *
     * stk.pop() // 返回 7
     * // 2 和 7 都出现了两次，但 7 是最近添加的
     *
     * // 栈中元素：[2,7,2,4]
     *
     * stk.pop() // 返回 2
     *
     * // 栈中元素：[2,7,4]
     *
     * stk.pop() // 返回 4
     *
     * // 栈中元素：[2,7]
     *
     * 这种设计数据结构的问题，主要是要搞清楚问题的难点在哪里，然后结合各种基本数据结构的特性，高效实现题目
     * 要求的 API。
     *
     * 那么，下面仔细思考一下 push 和 pop 方法，难点如下：
     *
     * 1、每次 pop 时，必须要知道频率最高的元素是什么。
     *
     * 2、如果频率最高的元素有多个，还得知道哪个是最近 push 进来的元素是哪个。
     *
     * 为了实现上述难点，这里要做到以下几点：
     *
     * 1、肯定要有一个变量 maxFreq 记录当前栈中最高的频率是多少。
     *
     * 2、这里得知道一个频率 freq 对应的元素有哪些，且这些元素要有时间顺序。
     *
     * 3、随着 pop 的调用，每个 val 对应的频率会变化，所以还得维持一个映射记录每个 val 对应的 freq。
     *
     * 综上，可以先实现 FreqStack 所需的数据结构：
     *
     * class FreqStack {
     *     // 记录 FreqStack 中元素的最大频率
     *     int maxFreq = 0;
     *     // 记录 FreqStack 中每个 val 对应的出现频率，后文就称为 VF 表
     *     HashMap<Integer, Integer> valToFreq = new HashMap<>();
     *     // 记录频率 freq 对应的 val 列表，后文就称为 FV 表
     *     HashMap<Integer, Stack<Integer>> freqToVals = new HashMap<>();
     * }
     *
     * 其实这有点类似「算法就像搭乐高：带你手撸 LFU 算法」，注意 freqToVals 中 val 列表用一个栈实现，如
     * 果一个 freq 对应的元素有多个，根据栈的特点，可以首先取出最近添加的元素。
     *
     * 要记住在 push 和 pop 方法中同时修改 maxFreq、VF 表、FV 表，否则容易出现 bug。
     *
     * 现在，可以来实现 push 方法了：
     *
     * public void push(int val) {
     *     // 修改 VF 表：val 对应的 freq 加一
     *     int freq = valToFreq.getOrDefault(val, 0) + 1;
     *     valToFreq.put(val, freq);
     *     // 修改 FV 表：在 freq 对应的列表加上 val
     *     freqToVals.putIfAbsent(freq, new Stack<>());
     *     freqToVals.get(freq).push(val);
     *     // 更新 maxFreq
     *     maxFreq = Math.max(maxFreq, freq);
     * }
     *
     * pop 方法的实现也非常简单：
     *
     * public int pop() {
     *     // 修改 FV 表：pop 出一个 maxFreq 对应的元素 v
     *     Stack<Integer> vals = freqToVals.get(maxFreq);
     *     int v = vals.pop();
     *     // 修改 VF 表：v 对应的 freq 减一
     *     int freq = valToFreq.get(v) - 1;
     *     valToFreq.put(v, freq);
     *     // 更新 maxFreq
     *     if (vals.isEmpty()) {
     *         // 如果 maxFreq 对应的元素空了
     *         maxFreq--;
     *     }
     *     return v;
     * }
     *
     * 这样，两个 API 都实现了。
     *
     * 嗯，这道题就解决了，Hard 难度的题目也不过如此嘛～
     */
    public static void main(String[] args) {

    }

}
