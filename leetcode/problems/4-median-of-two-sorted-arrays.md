#
> first submission，but someone told this time complexity is O(m+n), which is not obey the problem's requirement rule.
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i >= nums1.length) {
                result[k++] = nums2[j++];
            } else if (j >= nums2.length) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums1[i] > nums2[j] ? nums2[j++] : nums1[i++];
            }
        }
        if (result.length % 2 == 0) {
            return new Double(result[result.length / 2 - 1] + result[result.length / 2]) / 2;
        } else {
            return new Double(result[result.length / 2]);
        }
    }
}
```

# Approach 1: Recursive Approach
> use binary search and the time complexiyy is O(log(min(m, n)))
```java
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
```

# Recursive Approach with optimize
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            this.findMedianSortedArrays(nums2, nums1);
        }
        int min = 0, max = m;
        while (min <= max) {
            int i = (min + max) / 2;
            int j = (m + n + 1) / 2 - i;
            int maxALeft = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int maxBLeft = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int minARight = i == m ? Integer.MAX_VALUE : nums1[i];
            int minBRight = j == n ? Integer.MAX_VALUE : nums2[j];
            if ((maxALeft <= minBRight) && (maxBLeft <= minARight)) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxALeft, maxBLeft) + Math.min(minARight, minBRight)) / 2.0;
                } else {
                    return Math.max(maxALeft, maxBLeft) * 1.0;
                }
            } else if (maxALeft > minBRight) {
                max = i - 1;
            } else if (minARight < maxBLeft) {
                min = i + 1;
            }
        }
        return 0.0;
    }
}
```