package com.goblin;

import com.goblin.util.Integers;
import com.goblin.util.Times;

/**
 * 冒泡排序
 *
 * @author goblin
 * @version 1.0.0
 * @since 2021-11-28 14:18
 */
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] array1 = Integers.tailAscOrder(1, 10000, 3000);

        Integer[] array2 = Integers.copy(array1);
        Integer[] array3 = Integers.copy(array1);

        Times.test("冒泡排序-基础版本", () -> 基础版本(array1));
        Times.test("冒泡排序-优化1版本", () -> 基础版本_优化1(array2));
        Times.test("冒泡排序-优化2版本", () -> 基础版本_优化2(array3));

    }


    public static Integer[] 基础版本(Integer[] array) {
        for (int end = array.length; end > 0; end--) {
            for (int begin = 1; begin < end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                }
            }
        }
        return array;
    }

    /**
     * 针对数据完全有序情况
     *
     * @param array
     * @return
     */
    public static Integer[] 基础版本_优化1(Integer[] array) {
        for (int end = array.length; end > 0; end--) {
            boolean sorted = true;
            for (int begin = 1; begin < end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;
                    sorted = false;
                }
            }

            if (sorted) break;
        }
        return array;
    }


    /**
     * 针对尾部数据部分有序情况
     *
     * @param array
     * @return
     */
    public static Integer[] 基础版本_优化2(Integer[] array) {
        for (int end = array.length; end > 0; end--) {
            // 初始值在数组完全有序的时候有用
            int sortedIndex = 1;
            for (int begin = 1; begin < end; begin++) {
                if (array[begin] < array[begin - 1]) {
                    int tmp = array[begin];
                    array[begin] = array[begin - 1];
                    array[begin - 1] = tmp;

                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
        return array;
    }


}
