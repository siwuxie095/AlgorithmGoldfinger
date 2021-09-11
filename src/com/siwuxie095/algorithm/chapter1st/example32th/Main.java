package com.siwuxie095.algorithm.chapter1st.example32th;

/**
 * @author Jiajing Li
 * @date 2021-09-11 21:37:52
 */
public class Main {

    /**
     * 去重算法
     *
     * 这里不仅会让你学会算法套路，还可以顺便去 LeetCode 上拿下如下题目：
     *
     * 316.去除重复字母（中等）
     * 1081.不同字符的最小子序列（中等）
     *
     * 关于去重算法，应该没什么难度，往哈希集合里面塞不就行了么？
     *
     * 最多给你加点限制，问你怎么给有序数组原地去重。
     *
     * 这里讲的问题应该是去重相关算法中难度最大的了，把这个问题搞懂，就再也不用怕数组去重问题了。
     *
     * 这是力扣第 316 题「去除重复字母」，题目如下：
     *
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的
     * 字典序最小（要求不能打乱其他字符的相对位置）。
     *
     *
     * 这道题和第 1081 题「不同字符的最小子序列」的解法是完全相同的，你可以把这道题的解法代码
     * 直接粘过去把 1081 题也干掉。
     *
     * 题目的要求总结出来有三点：
     *
     * 要求一、要去重。
     *
     * 要求二、去重字符串中的字符顺序不能打乱 s 中字符出现的相对顺序。
     *
     * 要求三、在所有符合上一条要求的去重字符串中，字典序最小的作为最终结果。
     *
     * 上述三条要求中，要求三可能有点难理解，举个例子。
     *
     * 比如说输入字符串 s = "babc"，去重且符合相对位置的字符串有两个，分别是 "bac" 和 "abc"，
     * 但是算法得返回 "abc"，因为它的字典序更小。
     *
     * 按理说，如果想要有序的结果，那就得对原字符串排序对吧，但是排序后就不能保证符合 s 中字符
     * 出现顺序了，这似乎是矛盾的。
     *
     * 其实这里借鉴了之前「单调栈结构解决三道算法题」中讲到的「单调栈」的思路，看看你就明白了。
     */
    public static void main(String[] args) {

    }

}