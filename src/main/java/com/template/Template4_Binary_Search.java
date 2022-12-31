package com.template;

public class Template4_Binary_Search {

    public static void main(String[] args) {

        int[] numbers = {1, 3, 4, 5, 6, 7, 9};
        int[] numbers1 = {1, 3, 4, 5, 6, 7, 9, 12};

        System.out.println(search(numbers, 1));
        System.out.println(search(numbers, 7));
        System.out.println(search(numbers, 5));
        System.out.println(search1(numbers, 1));
        System.out.println(search1(numbers, 7));
        System.out.println(search1(numbers, 5));

        System.out.println(search(numbers1, 1));
        System.out.println(search(numbers1, 12));
        System.out.println(search(numbers1, 5));
        System.out.println(search(numbers1, 6));
        System.out.println(search1(numbers1, 1));
        System.out.println(search1(numbers1, 12));
        System.out.println(search1(numbers1, 5));
        System.out.println(search1(numbers1, 6));

    }

    public static int search(int[] nums, int target) {
        // Set the left and right boundaries
        int left = 0, right = nums.length - 1;

        // Under this condition
        while (left <= right) {
            // Get the middle index and the middle value.
            int mid = left + (right - left) / 2;
            // Case 1, return the middle index.
            if (nums[mid] == target) {
                return mid;
            }
            // Case 2, discard the smaller half.
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            // Case 3, discard the larger half.
            else {
                right = mid - 1;
            }
        }
        // If we finish the search without finding target, return -1.
        return -1;
    }

    public static int search1(int[] nums, int target) {
        // Set the left and right boundaries
        int left = 0, right = nums.length;

        // Under this condition
        while (left < right) {
            // Get the middle index and the middle value.
            int mid = left + (right - left) / 2;
            // Case 1, return the middle index.
            if (nums[mid] == target) {
                return mid;
            }
            // Case 2, discard the smaller half.
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            // Case 3, discard the larger half.
            else {
                right = mid;
            }
        }
        // If we finish the search without finding target, return -1.
        return -1;
    }
}
