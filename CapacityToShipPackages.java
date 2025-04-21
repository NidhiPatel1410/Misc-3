// In this problem, the min capacity a ship should have is the max weight of any ship present, and the max capacity should be the 
// sum of all weights of all ships. So doing a binary search on this min and max. Calc mid and think if this is the capacity. Take 
// day as 1, then iterate over the wieghts array and check how much you can fit with the capacity in one day, then if it exceeds mid
// then just increment day and set the sum as current weight and check how much you can fit in 2nd day. Like that compute the number
// of days required with the mid as capacity, if exceeding the allowed days, we want greater value of capacity so move right, else
// left. At end low will have correct value

// Time Complexity : O(n)+O(nlogm) where is weights array length and m=high-low is binary search range.
// So for each binary search we compute number of days which takes O(n) time and log(m) for binary search. The plus O(n) for 
// computing sum and calc high and low
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // Base case
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int low = 0;
        int high = 0;
        // Compute what is high and low
        for (int i = 0; i < weights.length; i++) {
            // Low will be the max weight
            low = Math.max(low, weights[i]);
            // High will be sum of all
            high = high + weights[i];
        }
        // Do binary search
        while (low <= high) {
            // Compute mid and think this is capacity
            int mid = low + (high - low) / 2;
            // Initialize day and sum for calc number of days required with this capacity
            int day = 1;
            int sum = 0;
            // Compute number of days
            for (int i = 0; i < weights.length; i++) {
                if (sum + weights[i] <= mid) {
                    sum = sum + weights[i];
                } else {
                    day++;
                    sum = weights[i];
                }
            }
            // IF less than equal to allowed days, then we want smaller capacity, so move
            // left
            if (day <= days) {
                high = mid - 1;

            }
            // Else right
            else {
                low = mid + 1;
            }
        }

        return low;
    }
}