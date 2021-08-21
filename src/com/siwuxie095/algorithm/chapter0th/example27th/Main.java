package com.siwuxie095.algorithm.chapter0th.example27th;

/**
 * @author Jiajing Li
 * @date 2021-08-21 17:11:22
 */
public class Main {

    /**
     * 二、字符串排列
     *
     * LeetCode 567 题，Permutation in String，难度 Medium：
     *
     * 给你两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     * 换句话说，s1 的排列之一是 s2 的 子串。
     *
     * 注意，输入的 s1 是可以包含重复字符的，所以这个题难度不小。
     *
     * 这种题目，是明显的滑动窗口算法，相当给你一个 S 和一个 T，请问你 S 中是否存在一个子串，包含 T 中
     * 所有字符且不包含其他字符？
     *
     * 首先，先复制粘贴之前的算法框架代码，然后明确滑动窗口的 4 个问题，即可写出这道题的答案：
     *
     * // 判断 s 中是否存在 t 的排列
     * bool checkInclusion(string t, string s) {
     *     unordered_map<char, int> need, window;
     *     for (char c : t) need[c]++;
     *
     *     int left = 0, right = 0;
     *     int valid = 0;
     *     while (right < s.size()) {
     *         char c = s[right];
     *         right++;
     *         // 进行窗口内数据的一系列更新
     *         if (need.count(c)) {
     *             window[c]++;
     *             if (window[c] == need[c])
     *                 valid++;
     *         }
     *
     *         // 判断左侧窗口是否要收缩
     *         while (right - left >= t.size()) {
     *             // 在这里判断是否找到了合法的子串
     *             if (valid == need.size())
     *                 return true;
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
     *     // 未找到符合条件的子串
     *     return false;
     * }
     *
     * 对于这道题的解法代码，基本上和最小覆盖子串一模一样，只需要改变两个地方：
     *
     * 1、本题移动 left 缩小窗口的时机是窗口大小大于 t.size() 时，应为排列嘛，显然长度应该是一样的。
     *
     * 2、当发现 valid == need.size() 时，就说明窗口中就是一个合法的排列，所以立即返回 true。
     *
     * 至于如何处理窗口的扩大和缩小，和最小覆盖子串完全相同。
     */
    public static void main(String[] args) {

    }

}
