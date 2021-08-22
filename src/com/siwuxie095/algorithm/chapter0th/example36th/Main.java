package com.siwuxie095.algorithm.chapter0th.example36th;

/**
 * @author Jiajing Li
 * @date 2021-08-22 16:50:13
 */
public class Main {

    /**
     * 提高刷题幸福感的小技巧
     *
     * 相信每个人都有过被代码的小 bug 搞得心态爆炸的经历，这里分享一个最常用的简单技巧，可以大幅提升刷题的幸福感。
     *
     * 在这之前，首先回答一个问题，刷力扣题是直接在网页上刷比较好还是在本地 IDE 上刷比较好？
     *
     * 如果是牛客网笔试那种自己处理输入输出的判题形式，一定要在 IDE 上写，这个没啥说的，但像力扣这种判题形式，
     * 个人偏好直接在网页上刷，原因有二：
     *
     * 1、方便
     *
     * 因为力扣有的数据结构是自定的，比如说 TreeNode，ListNode 这种，在本地你还得把这个类 copy 过去。
     *
     * 而且在 IDE 上没办法测试，写完代码之后还得粘贴到网页上跑测试数据，那还不如直接网页上写呢。
     *
     * 算法又不是工程代码，量都比较小，IDE 的自动补全带来的收益基本可以忽略不计。
     *
     *
     * 2、实用
     *
     * 到时候面试的时候，面试官给你出的算法题大都是希望你直接在网页上完成的，最好是边写边讲你的思路。
     *
     * 如果平时练习的时候就习惯没有 IDE 的自动补全，习惯手写代码大脑编译，到时候面试的时候写代码就能更快更从容。
     *
     *
     * 接下来分享个人觉得最常实用的干货技巧。
     *
     *
     *
     * 如何给算法 debug
     *
     * 代码的错误时无法避免的，有时候可能整个思路都错了，有时候可能是某些细节问题，比如 i 和 j 写反了，这种问题
     * 怎么排查？
     *
     * 一般的算法问题肯定不难排查，肉眼检查应该都没啥问题，再不济 print 打印一些关键变量的值，总能发现问题。
     *
     * 比较让人头疼的的应该是递归算法的问题排查。
     *
     * 如果没有一定的经验，函数递归的过程很难被正确理解，所以这里就重点讲讲如何高效 debug 递归算法。
     *
     * 有的读者可能会说，把算法 copy 到 IDE 里面，然后打断点一步步跟着走不就行了吗？
     *
     * 这个方法肯定是可以的，但是递归函数最好从一个全局的角度理解，而不要跳进具体的细节。
     *
     * 如果你对递归还不够熟悉，没有一个全局的视角，这种一步步打断点的方式也容易把人绕进去。
     *
     * 这里的建议是直接在递归函数内部打印关键值，配合缩进，直观地观察递归函数执行情况。
     *
     * 最能提升 debug 效率的是缩进，除了解法函数，还可以新定义一个函数 printIndent 和一个全局变量 count：
     *
     * // 全局变量，记录递归函数的递归层数
     * int count = 0;
     *
     * // 输入 n，打印 n 个 tab 缩进
     * void printIndent(int n) {
     *     for (int i = 0; i < n; i++) {
     *         printf("   ");
     *     }
     * }
     *
     * 接下来，套路来了：
     *
     * 在递归函数的开头，调用 printIndent(count++) 并打印关键变量；然后在所有 return 语句之前调用
     * printIndent(--count) 并打印返回值。
     *
     * 举个具体的例子，比如一个递归的 dp 函数，大致的结构如下：
     *
     * int dp(string& ring, int i, string& key, int j) {
     *     // base case
     *     if (j == key.size()) {
     *         return 0;
     *     }
     *
     *     // 状态转移
     *     int res = INT_MAX;
     *     for (int k : charToIndex[key[j]]) {
     *         res = min(res, dp(ring, j, key, i + 1));
     *     }
     *
     *     return res;
     * }
     *
     * 这个递归的 dp 函数在进行了 debug 之后，变成了这样：
     *
     * int count = 0;
     * void printIndent(int n) {
     *     for (int i = 0; i < n; i++) {
     *         printf("   ");
     *     }
     * }
     *
     * int dp(string& ring, int i, string& key, int j) {
     *     // printIndent(count++);
     *     // printf("i = %d, j = %d\n", i, j);
     *
     *     if (j == key.size()) {
     *         // printIndent(--count);
     *         // printf("return 0\n");
     *         return 0;
     *     }
     *
     *     int res = INT_MAX;
     *     for (int k : charToIndex[key[j]]) {
     *         res = min(res, dp(ring, j, key, i + 1));
     *     }
     *
     *     // printIndent(--count);
     *     // printf("return %d\n", res);
     *     return res;
     * }
     *
     * 就是在函数开头和所有 return 语句对应的地方加上一些打印代码。
     *
     * 如果去掉注释，执行一个测试用例，会得到对应的输出。
     *
     * 这样，通过对比对应的缩进就能知道每次递归时输入的关键参数 i, j 的值，以及每次递归调用返回的结果是多少。
     *
     * 最重要的是，这样可以比较直观地看出递归过程，你有没有发现这就是一棵递归树？
     *
     * 理解递归函数最重要的就是画出递归树，这样打印一下，连递归树都不用自己画了，而且还能清晰地看出每次递归的
     * 返回值。
     *
     * 可以说，这是对刷题「幸福感」提升最大的一个小技巧，比 IDE 打断点要高效。
     */
    public static void main(String[] args) {

    }

}
