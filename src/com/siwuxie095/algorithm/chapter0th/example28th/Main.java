package com.siwuxie095.algorithm.chapter0th.example28th;

/**
 * @author Jiajing Li
 * @date 2021-08-21 17:17:06
 */
public class Main {

    /**
     * 三、找所有字母异位词
     *
     * 这是 LeetCode 第 438 题，Find All Anagrams in a String，难度 Medium：
     *
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 异位词 指字母相同，但排列不同的字符串。
     *
     * 呵呵，这个所谓的字母异位词，不就是排列吗，搞个高端的说法就能糊弄人了吗？相当于，输入一个串 S，一个串 T，
     * 找到 S 中所有 T 的排列，返回它们的起始索引。
     *
     * 直接默写一下框架，明确滑动窗口的 4 个问题，即可秒杀这道题：
     *
     * vector<int> findAnagrams(string s, string t) {
     *     unordered_map<char, int> need, window;
     *     for (char c : t) need[c]++;
     *
     *     int left = 0, right = 0;
     *     int valid = 0;
     *     vector<int> res; // 记录结果
     *     while (right < s.size()) {
     *         char c = s[right];
     *         right++;
     *         // 进行窗口内数据的一系列更新
     *         if (need.count(c)) {
     *             window[c]++;
     *             if (window[c] == need[c])
     *                 valid++;
     *         }
     *         // 判断左侧窗口是否要收缩
     *         while (right - left >= t.size()) {
     *             // 当窗口符合条件时，把起始索引加入 res
     *             if (valid == need.size())
     *                 res.push_back(left);
     *             char d = s[left];
     *             left++;
     *             // 进行窗口内数据的一系列更新
     *             if (need.count(d)) {
     *                 if (window[d] == need[d])
     *                     valid--;
     *                 window[d]--;
     *             }
     *         }
     *     }
     *     return res;
     * }
     *
     * 跟寻找字符串的排列一样，只是找到一个合法异位词（排列）之后将起始索引加入 res 即可。
     */
    public static void main(String[] args) {

    }

}
