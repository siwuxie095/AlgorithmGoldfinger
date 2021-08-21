package com.siwuxie095.algorithm.chapter0th.example29th;

/**
 * @author Jiajing Li
 * @date 2021-08-21 17:20:16
 */
public class Main {

    /**
     * 四、最长无重复子串
     *
     * 这是 LeetCode 第 3 题，Longest Substring Without Repeating Characters，难度 Medium：
     *
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     *
     * 这个题终于有了点新意，不是一套框架就出答案，不过反而更简单了，稍微改一改框架就行了：
     *
     * int lengthOfLongestSubstring(string s) {
     *     unordered_map<char, int> window;
     *
     *     int left = 0, right = 0;
     *     int res = 0; // 记录结果
     *     while (right < s.size()) {
     *         char c = s[right];
     *         right++;
     *         // 进行窗口内数据的一系列更新
     *         window[c]++;
     *         // 判断左侧窗口是否要收缩
     *         while (window[c] > 1) {
     *             char d = s[left];
     *             left++;
     *             // 进行窗口内数据的一系列更新
     *             window[d]--;
     *         }
     *         // 在这里更新答案
     *         res = max(res, right - left);
     *     }
     *     return res;
     * }
     *
     * 这就是变简单了，连 need 和 valid 都不需要，而且更新窗口内数据也只需要简单的更新计数器 window 即可。
     *
     * 当 window[c] 值大于 1 时，说明窗口中存在重复字符，不符合条件，就该移动 left 缩小窗口了。
     *
     * 唯一需要注意的是，在哪里更新结果 res 呢？这里要的是最长无重复子串，哪一个阶段可以保证窗口中的字符串
     * 是没有重复的呢？
     *
     * 这里和之前不一样，要在收缩窗口完成后更新 res，因为窗口收缩的 while 条件是存在重复元素，换句话说收缩
     * 完成后一定保证窗口中没有重复。
     */
    public static void main(String[] args) {

    }

}
