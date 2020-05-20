package ProblemC.Graph;

public class Inder {


/*
    public static int[][] preprocess(int[] input) {
        int n = input.length;
        int[][] sparse = new int[n][log2(n) + 1];
        for (int i = 0; i < input.length; i++) {
            sparse[i][0] = i;
        }

        for (int j = 1; 1 << j <= n; j++) {
            for (int i = 0; i + (1 << j) - 1 < n; i++) {
                if (input[sparse[i][j - 1]] < input[sparse[i + (1 << (j - 1))][j - 1]]) {
                    sparse[i][j] = sparse[i][j - 1];
                } else {
                    sparse[i][j] = sparse[i + (1 << (j - 1))][j - 1];
                }
            }
        }
        return sparse;
    }

    public static int rangeMinimumQuery(int low, int high, int[] input, int[][] sparse) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }
        int l = high - low + 1;
        int k = log2(l);
        if (input[sparse[low][k]] <= input[sparse[low + l - (1 << k)][k]]) {
            return input[sparse[low][k]];
        } else {
            return input[sparse[high - (1 << k) + 1][k]];
        }
    }

    private static int log2(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros(n);
    }

    // Fills lookup array lookup[][] in bottom up manner.
    public static int[][] buildSparseTable(int arr[], int n)
    {

        int [][]lookup = new int[n][log2(n) + 1];

        // Initialize M for the intervals with length 1
        for (int i = 0; i < n; i++)
            lookup[i][0] = arr[i];

        // Compute values from smaller to bigger intervals
        for (int j = 1; (1 << j) <= n; j++) {

            // Compute minimum value for all intervals with
            // size 2^j
            for (int i = 0; (i + (1 << j) - 1) < n; i++) {

                // For arr[2][10], we compare arr[lookup[0][7]]
                // and arr[lookup[3][10]]
                if (lookup[i][j - 1] <
                        lookup[i + (1 << (j - 1))][j - 1])
                    lookup[i][j] = lookup[i][j - 1];
                else
                    lookup[i][j] =
                            lookup[i + (1 << (j - 1))][j - 1];
            }
        }
        return lookup;
    }

    // Returns minimum of arr[L..R]
    public static int query(int L, int R, int[][] lookup)
    {
        // Find highest power of 2 that is smaller
        // than or equal to count of elements in given
        // range. For [2, 10], j = 3
        int j = (int)Math.log(R - L + 1);

        // Compute minimum of last 2^j elements with first
        // 2^j elements in range.
        // For [2, 10], we compare arr[lookup[0][3]] and
        // arr[lookup[3][3]],
        if (lookup[L][j] <= lookup[R - (1 << j) + 1][j])
            return lookup[L][j];

        else
            return lookup[R - (1 << j) + 1][j];
    }
*/

}
