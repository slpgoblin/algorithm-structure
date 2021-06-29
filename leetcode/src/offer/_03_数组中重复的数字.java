package offer;

import java.util.HashSet;

/**
 * @author goblin
 * @version 1.0.0
 * @className offer._03_数组中重复的数字
 * @description https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * @createTime 2021-03-03 23:14
 */
public class _03_数组中重复的数字 {

    public int findRepeatNumber(int[] nums) {
        int tmp;
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]){
                if (nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                tmp = nums[i];
                nums[i] = nums[nums[i]];
                nums[tmp] = tmp;
            }
        }
        return -1;
    }

}
