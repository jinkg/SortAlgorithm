import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by YaLin
 */
public class Sort {
    public static void main(String[] args) {
        int[] source = new int[]{6, 2, 3, 8, 1, 2, 5, 7, 0, 9, 3};
        int[] source1 = new int[]{6, 2, 3, 8, 1, 2, 5, 7, 0, 9, 3};
        int[] source2 = new int[]{6, 2, 3, 8, 1, 2, 5, 7, 0, 9, 3};
        int[] source3 = new int[]{6, 2, 3, 8, 1, 2, 5, 7, 0, 9, 3};
        System.out.println("SortTest Source: " + Arrays.toString(source));

        int[] quickRecursiveResult = quickSortRecursive(source, 0, source.length - 1);
        int[] quickResult = quickSort(source1);
        int[] mergeRecursiveResult = mergeSortRecursive(source2, 0, source.length - 1);
        int[] mergeResult = mergeSort(source3);

        System.out.println("QuickSort RecursiveResult: " + Arrays.toString(quickRecursiveResult));
        System.out.println("QuickSort Result: " + Arrays.toString(quickResult));
        System.out.println("MergeSort RecursiveResult: " + Arrays.toString(mergeRecursiveResult));
        System.out.println("MergeSort Result: " + Arrays.toString(mergeResult));

        System.out.println();
        source = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        source1 = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        source2 = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        source3 = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        System.out.println("SortTest Source: " + Arrays.toString(source));

        quickRecursiveResult = quickSortRecursive(source, 0, source.length - 1);
        quickResult = quickSort(source1);
        mergeRecursiveResult = mergeSortRecursive(source2, 0, source.length - 1);
        mergeResult = mergeSort(source3);

        System.out.println("QuickSort RecursiveResult: " + Arrays.toString(quickRecursiveResult));
        System.out.println("QuickSort Result: " + Arrays.toString(quickResult));
        System.out.println("MergeSort RecursiveResult: " + Arrays.toString(mergeRecursiveResult));
        System.out.println("MergeSort Result: " + Arrays.toString(mergeResult));
    }

    public static int[] quickSortRecursive(int[] source, int start, int end) {
        if (start >= end) {
            return source;
        }
        int startIndex = start;
        int endIndex = end;

        int pivot = source[startIndex];

        while (startIndex < endIndex) {
            while (pivot <= source[endIndex] && startIndex < endIndex) {
                endIndex--;
            }
            source[startIndex] = source[endIndex];
            while (pivot >= source[startIndex] && startIndex < endIndex) {
                startIndex++;
            }
            source[endIndex] = source[startIndex];
        }

        source[startIndex] = pivot;

        quickSortRecursive(source, start, startIndex - 1);
        quickSortRecursive(source, startIndex + 1, end);

        return source;
    }

    static class Position {
        int start;
        int end;
        int divider;

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void setDivider(int divider) {
            this.divider = divider;
        }
    }

    public static int[] quickSort(int[] source) {
        int startIndex = 0;
        int endIndex = source.length - 1;

        Position firstPosition = quickStep(source, startIndex, endIndex);

        Queue<Position> dividerList = new LinkedList<>();

        if (firstPosition != null) {
            dividerList.add(firstPosition);
        }

        for (Position position = dividerList.poll(); position != null; position = dividerList.poll()) {
            Position leftDivider = quickStep(source, position.start, position.divider);
            Position rightDivider = quickStep(source, position.divider + 1, position.end);

            if (leftDivider != null) {
                dividerList.add(leftDivider);
            }
            if (rightDivider != null) {
                dividerList.add(rightDivider);
            }
        }
        return source;
    }

    public static Position quickStep(int[] source, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return null;
        }
        Position position = new Position(startIndex, endIndex);

        int pivot = source[startIndex];
        while (startIndex < endIndex) {
            while (pivot <= source[endIndex] && startIndex < endIndex) {
                endIndex--;
            }
            source[startIndex] = source[endIndex];
            while (pivot >= source[startIndex] && startIndex < endIndex) {
                startIndex++;
            }
            source[endIndex] = source[startIndex];
        }
        source[startIndex] = pivot;
        position.setDivider(startIndex);
        return position;
    }

    public static int[] mergeSortRecursive(int[] source, int start, int end) {
        if (start >= end) {
            return source;
        }

        int mid = (start + end) >> 1;

        mergeSortRecursive(source, start, mid);
        mergeSortRecursive(source, mid + 1, end);

        int[] buffer = new int[end - start + 1];
        int index = 0;
        int left = start;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if (source[left] > source[right]) {
                buffer[index++] = source[right++];
            } else {
                buffer[index++] = source[left++];
            }
        }
        while (left <= mid) {
            buffer[index++] = source[left++];
        }
        while (right <= end) {
            buffer[index++] = source[right++];
        }

        System.arraycopy(buffer, 0, source, start, buffer.length);
        return source;
    }

    public static int[] mergeSort(int[] source) {
        int length = source.length;
        for (int i = 1; i < length; i <<= 1) {
            int pairCount = (length / i) >> 1;
            if (length % i != 0) {
                pairCount++;
            }
            for (int j = 0; j < pairCount; j++) {
                int pairStartIndex = i * j * 2;
                int pairEndIndex = (j + 1) * i * 2 - 1;
                pairEndIndex = Math.min(pairEndIndex, length - 1);

                int leftIndex = pairStartIndex;
                int rightIndex = leftIndex + i;

                rightIndex = Math.min(rightIndex, length - 1);

                int mid = rightIndex - 1;

                int[] buffer = new int[pairEndIndex - pairStartIndex + 1];
                int index = 0;
                while (leftIndex <= mid && rightIndex <= pairEndIndex) {
                    if (source[leftIndex] > source[rightIndex]) {
                        buffer[index++] = source[rightIndex++];
                    } else {
                        buffer[index++] = source[leftIndex++];
                    }
                }
                while (leftIndex <= mid) {
                    buffer[index++] = source[leftIndex++];
                }
                while (rightIndex <= pairEndIndex) {
                    buffer[index++] = source[rightIndex++];
                }

                System.arraycopy(buffer, 0, source, pairStartIndex, buffer.length);
            }
        }
        return source;
    }
}
