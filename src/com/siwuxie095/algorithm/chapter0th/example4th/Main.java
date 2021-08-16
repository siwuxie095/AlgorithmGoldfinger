package com.siwuxie095.algorithm.chapter0th.example4th;

/**
 * @author Jiajing Li
 * @date 2021-08-16 22:01:58
 */
public class Main {

    /**
     * 三、算法刷题指南
     *
     * ⾸先要明确的是，数据结构是⼯具，算法是通过合适的⼯具解决特定问题的⽅法。也就是说，学习算法之前，
     * 最起码得了解那些常⽤的数据结构，了解它们的特性和缺陷。
     *
     * 那么该如何在 LeetCode 刷题呢？这里直接说具体的建议：
     *
     * 先刷⼆叉树，先刷⼆叉树，先刷⼆叉树！
     *
     * 为什么要先刷⼆叉树呢，因为⼆叉树是最容易培养框架思维的，⽽且⼤部分算法技巧，本质上都是树的遍历
     * 问题。
     *
     * 刷⼆叉树看到题⽬没思路？其实⼤家不是没思路，只是没有理解这里所说的「框架」是什么。不要⼩看这⼏
     * ⾏破代码，⼏乎所有⼆叉树的题⽬都是⼀套这个框架就出来了。
     *
     * void traverse(TreeNode root) {
     *     // 前序遍历
     *     traverse(root.left)
     *     // 中序遍历
     *     traverse(root.right)
     *     // 后序遍历
     * }
     *
     * ⽐如随便拿⼏道题的解法出来，不⽤管具体的代码逻辑，只要看看框架在其中是如何发挥作⽤的就⾏。
     *
     * LeetCode 124 题，难度 Hard，让你求⼆叉树中最⼤路径和，主要代码如下：
     *
     * int ans = INT_MIN;
     * int oneSideMax(TreeNode* root) {
     *     if (root == nullptr) return 0;
     *     int left = max(0, oneSideMax(root->left));
     *     int right = max(0, oneSideMax(root->right));
     *     ans = max(ans, left + right + root->val);
     *     return max(left, right) + root->val;
     * }
     *
     * 你看，这就是个后序遍历嘛。
     *
     * LeetCode 105 题，难度 Medium，让你根据前序遍历和中序遍历的结果还原⼀棵⼆叉树，很经典的问题
     * 吧，主要代码如下：
     *
     * TreeNode buildTree(int[] preorder, int preStart, int preEnd,
     *  int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMa p) {
     *     if(preStart > preEnd || inStart > inEnd) return null;
     *     TreeNode root = new TreeNode(preorder[preStart]);
     *     int inRoot = inMap.get(root.val);
     *     int numsLeft = inRoot - inStart;
     *     root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,
     *         inorder, inStart, inRoot - 1, inMap);
     *     root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,
     *         inorder, inRoot + 1, inEnd, inMap);
     *     return root;
     * }
     *
     * 不要看这个函数的参数很多，只是为了控制数组索引⽽已，本质上该算法也就是⼀个前序遍历。
     *
     * LeetCode 99 题，难度 Hard，恢复⼀棵 BST（二叉搜索树），主要代码如下：
     *
     * void traverse(TreeNode* node) {
     *     if (!node) return;
     *     traverse(node->left);
     *     if (node->val < prev->val) {
     *          s = (s == NULL) ? prev : s;
     *          t = node;
     *     }
     *     prev = node;
     *     traverse(node->right);
     * }
     *
     * 这不就是个中序遍历嘛，对于⼀棵 BST 中序遍历意味着什么，应该不需要解释了吧。
     *
     * 你看，Hard 难度的题⽬不过如此，⽽且还这么有规律可循，只要把框架写出来，然后往相应的位置加东⻄
     * 就⾏了，这不就是思路吗？
     *
     * 对于⼀个理解⼆叉树的⼈来说，刷⼀道⼆叉树的题⽬花不了多⻓时间。那么如果你对刷题⽆从下⼿或者有畏
     * 惧⼼理，不妨从⼆叉树下⼿，前 10 道也许有点难受；结合框架再做 20 道，也许你就有点⾃⼰的理解了；
     * 刷完整个专题，再去做什么回溯动规分治专题，你就会发现只要涉及递归的问题，都是树的问题。
     *
     * 先来看看动态规划，比如凑零钱问题，暴⼒解法就是遍历⼀棵 N 叉树：
     *
     * def coinChange(coins: List[int], amount: int):
     *     def dp(n):
     *         if n == 0: return 0
     *         if n < 0: return -1
     *
     *         res = float('INF')
     *         for coin in coins:
     *             subproblem = dp(n - coin)
     *             # ⼦问题⽆解，跳过
     *             if subproblem == -1: continue
     *             res = min(res, 1 + subproblem)
     *         return res if res != float('INF') else -1
     *     return dp(amount)
     *
     * 这么多代码看不懂咋办？直接提取出框架，就能看出核⼼思路了：
     *
     * # 不过是⼀个 N 叉树的遍历问题⽽已
     * def dp(n):
     *     for coin in coins:
     *         dp(n - coin)
     *
     * 其实很多动态规划问题就是在遍历⼀棵树，你如果对树的遍历操作烂熟于⼼，起码知道怎么把思路转化成代
     * 码，也知道如何提取别⼈解法的核⼼思路。
     *
     * 再来看看回溯算法，回溯算法就是个 N 叉树的前后序遍历问题，没有例外。
     *
     * ⽐如 N 皇后问题，主要代码如下：
     *
     * void backtrack(int[] nums, LinkedList<Integer> track) {
     *     if (track.size() == nums.length) {
     *         res.add(new LinkedList(track));
     *         return;
     *     }
     *
     *     for (int i = 0; i < nums.length; i++) {
     *         if (track.contains(nums[i]))
     *             continue;
     *         track.add(nums[i]);
     *         // 进⼊下⼀层决策树
     *         backtrack(nums, track);
     *         track.removeLast();
     *      }
     *
     * // 提取出 N 叉树遍历框架
     * void backtrack(int[] nums, LinkedList<Integer> track) {
     *     for (int i = 0; i < nums.length; i++) {
     *         backtrack(nums, track);
     *     }
     * }
     *
     * N 叉树的遍历框架，找出来了把〜你说，树这种结构重不重要？
     *
     * 综上，对于畏惧算法的朋友来说，可以先刷树的相关题⽬，试着从框架上看问题，⽽不要纠结于细节问题。
     *
     * 纠结细节问题，就⽐如纠结 i 到底应该加到 n 还是加到 n - 1，这个数组的⼤⼩到底应该开 n 还是
     * n + 1？
     *
     * 从框架上看问题，就是像这样基于框架进⾏抽取和扩展，既可以在看别⼈解法时快速理解核⼼逻辑，也有助
     * 于找到⾃⼰写解法时的思路⽅向。
     *
     * 当然，如果细节出错，你得不到正确的答案，但是只要有框架，你再错也错不到哪去，因为你的⽅向是对的。
     *
     * 但是，你要是⼼中没有框架，那么你根本⽆法解题，给了你答案，你也不会发现这就是个树的遍历问题。
     *
     * 这种思维是很重要的，比如，在动态规划中找状态转移⽅程有⼏步流程，有时候按照流程写出解法，⾃⼰都
     * 不知道为啥是对的，反正它就是对了。
     *
     * 这就是框架的⼒量，能够保证你在快睡着的时候，依然能写出正确的程序；就算你啥都不会，都能⽐别⼈⾼
     * ⼀个级别。
     */
    public static void main(String[] args) {

    }

}
