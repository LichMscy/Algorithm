package com.lichmscy.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author lichmscy
 * 17-7-31.
 */
public class SORT {

    public static void main(String[] args) {
        int[] arr = {54,35,48,36,27,12,44,44,8,14,26,17,28};
//        System.out.print(Arrays.toString(insertSort(arr)));
//        System.out.print(Arrays.toString(shellSort(arr)));
//        System.out.print(Arrays.toString(selectSort(arr)));
//        System.out.print(Arrays.toString(heapSort(arr)));
//        System.out.print(Arrays.toString(bubbleSort(arr)));
//        System.out.print(Arrays.toString(quickSort(arr)));
//        System.out.print(Arrays.toString(mergeSort(arr)));
        System.out.print(Arrays.toString(radixSort(arr)));

    }

    // PART1 - 插入排序 - 直接插入排序
    private static int[] insertSort(int[] arr) {
        int key = 0;
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            key = arr[i];
            while (j >= 0) {
                if (arr[j] > key){
                    arr[j + 1] = arr[j];
                    arr[j] = key;
                }
                j--;
            }
        }
        return arr;
    }

    // PART1 - 插入排序 - 希尔排序（最小增量排序）
    private static int[] shellSort(int[] arr) {
//        double d1 = arr.length;
//        int temp;
//        while(true) {
//            d1 = Math.ceil(d1 / 2);
//            int d = (int) d1;
//            for (int x = 0; x < d; x++) {
//                for (int i = x + d; i < arr.length; i += d) {
//                    int j = i - d;
//                    temp = arr[i];
//                    for (; j >= 0 && temp < arr[j]; j -= d) {
//                        arr[j + d] = arr[j];
//                    }
//                    arr[j + d] = temp;
//                }
//            }
//            if (d == 1) {
//                break;
//            }
//        }
//        return arr;

        double d = arr.length;
        int key;
        while (true) {
            d = Math.ceil(d / 2);
            int di = (int) d;
            for (int x = 0; x < di; x++) {
                for (int i = x + di; i < arr.length; i+=di) {
                    int j = i - di;
                    key = arr[i];
                    for (; j >= 0 && arr[j] > key; j-=di) {
                        arr[j + di] = arr[j];
                    }
                    arr[j + di] = key;
                }
            }
            if (di == 1) {
                break;
            }
        }
        return arr;
    }

    // PART2 - 选择排序 - 简单选择排序
    private static int[] selectSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            arr[i] = arr[min];
            arr[min] = key;
        }
        return arr;
    }

    // PART2 - 选择排序 - 堆排序
    private static int[] heapSort(int[] arr) {

        int temp;
        for (int l = 0; l < arr.length; l++) {
            //建堆
            int lastIndex = arr.length - 1 - l;
            for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
                int k = i;

                while (k * 2 + 1 <= lastIndex) {
                    int biggerIndex = 2 * k + 1;    // k节点的左子节点
                    if (biggerIndex < lastIndex) {  // biggerIndex + 1代表k节点的右子节点存在
                        if (arr[biggerIndex] < arr[biggerIndex + 1]) {
                            biggerIndex++;
                        }
                    }
                    if (arr[k] < arr[biggerIndex]) {    // k节点的值小于其较大的子节点的值
                        temp = arr[k];
                        arr[k] = arr[biggerIndex];
                        arr[biggerIndex] = temp;
                        k = biggerIndex;
                    } else break;
                }
            }
            //交换堆顶最大值和最后一个元素
            temp = arr[0];
            arr[0] = arr[lastIndex];
            arr[lastIndex] = temp;
        }

        return arr;
    }

    // PART3 - 交换排序 - 冒泡排序
    private static int[] bubbleSort(int[] arr) {

        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

        return arr;
    }

    // PART3 - 交换排序 - 快速排序
    private static int[] quickSort(int[] arr) {

        _quickSort(arr, 0,  arr.length - 1);
        return arr;
    }

    private static void _quickSort(int[] list, int low, int high) {
        if (low < high) {

            int mid = _quickSort_getMid(list, low, high);
            _quickSort(list, low, mid - 1);
            _quickSort(list, mid + 1, high);
        }
    }

    private static int _quickSort_getMid(int[] list, int low, int high) {
        // 将数组一分为二，大于基准值的在右边，小于的在左边
        int temp = list[low];
        while (low < high) {
            while (low < high && list[high] >= temp) {
                high--;
            }
            list[low] = list[high];
            while (low < high && list[low] <= temp) {
                low++;
            }
            list[high] = list[low];
        }
        list[low] = temp;
        return low;
    }

    // PART4 - 归并排序
    private static int[] mergeSort(int[] arr) {
        _merge_Sort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void _merge_Sort(int[] data, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;    // 找出中间索引
            _merge_Sort(data, left, center);    // 对左边数组进行递归
            _merge_Sort(data, center + 1, right);   // 对右边数组进行递归
            _merge_Merge(data, left, center, right);    // 合并
        }
    }

    private static void _merge_Merge(int[] data, int left, int center, int right) {
        int[] temps = new int[data.length];
        int mid = center + 1;
        int tempIndex = left;   // 记录中间数组的索引
        int temp = left;
        while (left <= center && mid <= right) {
            // 从两个数组中取出最小的放入中间数组
            if (data[left] <= data[mid]) {
                temps[tempIndex++] = data[left++];
            } else {
                temps[tempIndex++] = data[mid++];
            }
        }
        // 剩余部分依次放入中间数组
        while (mid <= right) {
            temps[tempIndex++] = data[mid++];
        }
        while (left <= center) {
            temps[tempIndex++] = data[left++];
        }
        //将中间数组中的内容复制回原数组
        while (temp <= right) {
            data[temp] = temps[temp++];
        }
    }

    // PART5 - 基数排序
    private static int[] radixSort(int[] arr) {
        // 确定排序的次数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int times = 0;
        while (max > 0) {
            max/=10;
            times++;
        }
        List<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }
        //进行times次分配和收集
        for (int i = 0; i < times; i++) {
            //分配数组元素
            for (int j = 0; j < arr.length; j++) {
                //得到数字的第times+1位数
                int x = arr[j]  % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(arr[j]);
                queue.set(x, queue2);
            }
            int count = 0;  //元素计数器
            //收集队列元素
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    arr[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
        return arr;
    }

}
