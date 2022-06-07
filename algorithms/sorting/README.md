## Selection sort

The algorithm maintains two subarrays in a given array:
- an already sorted subarray
- remaining subarray which is unsorted
Initially, the sorted subarray is empty and the unsorted subarray is the whole array.

In each iteration the unsorted subarray is traversed to find the smallest element (or largest, depending on sorting order). Then this element is added to the sorted subarray by swapping it with the leftmost unsorted element.

#### ***Time Complexity O(N^2)***
- Inefficient on large arrays.
- For each element, the entire array is checked to find the smallest element. So in the worst case, N elements are checked for each element.
#### ***Space Complexity O(1)***
- In-place algorithm (Algorithm that does not need an extra space and produces an output in the same memory that contains the data by transforming the input ‘in-place’. However, a small constant extra space used for variables is allowed).
#### ***Not Stable*** 
The default implementation is not stable,but it can be made stable (stable -> if two objects with equal keys appear in the same order in sorted output as they appear in the input array).
#### ***Number of Comparisons O(N^2)***
Every element is compared to every other element.
#### ***Number of Swaps O(N)***
Makes the minimum possible number of swaps, n − 1 in the worst case (the final element is already in place).

![<img src="Selection_Sort_Baeldung_on_Computer_Science.png" width="100" align="center"/>](Selection_Sort_Baeldung_on_Computer_Science.png)

Rendered Image

#### USE:
- When the array is NOT partially sorted
- When we have memory usage constraints
- When a simple sorting implementation is desired
- When the array to be sorted is relatively small

#### AVOID:
- When the array to be sorted has a large number of elements
- When the array is nearly sorted
- When you want a faster run time and memory is not a concern.
        






**References:**  
:point_right: [Geeks for Geeks](https://www.geeksforgeeks.org/sorting-algorithms/)]  
:point_right: [nj](https://www.acodersjourney.com/tag/programming-interviews/)