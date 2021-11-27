package 动态规划;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 *
 * @author goblin
 * @version 1.0.0
 * @since 2021-11-27 20:47
 */
public class _322_零钱兑换 {

    public int coinChange(int[] coins, int amount) {
        return 暴力递归(coins, amount);
    }

    public int 暴力递归(int[] coins, int amount) {
        // base case
        if (amount <= 0) return amount;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int tmp = 暴力递归(coins, amount - coin);
            if (tmp < 0) continue;
            res = Integer.min(res, tmp + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;

    }


    public int 备忘录(int[] coins, int amount) {
        // 记录amount所需硬币个数
        int[] amountArray = new int[amount + 1];
        return 备忘录计算(coins, amount, amountArray);
    }

    public int 备忘录计算(int[] coins, int amount, int[] amountArray) {
        // base case
        if (amount <= 0) return amount;

        if (amountArray[amount] != 0) return amountArray[amount];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int tmp = 备忘录计算(coins, amount - coin, amountArray);
            if (tmp < 0) continue;
            res = Integer.min(res, tmp + 1);
        }
        amountArray[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return amountArray[amount];

    }


    public int dp数组(int[] coins, int amount) {
        if (amount == 0) return amount;

        // 记录amount所需硬币个数
        int[] amountArray = new int[amount + 1];
        Arrays.fill(amountArray, amount + 1);
        // base case
        amountArray[0] = 0;

        for (int n = 0; n < amountArray.length; n++) {
            for (int coin : coins) {
                if (n - coin < 0) continue;
                amountArray[n] = Math.min(amountArray[n], 1 + amountArray[n - coin]);
            }
        }
        return amountArray[amount] > amount ? -1 : amountArray[amount];
    }


}
