package oy.tol.tra;

import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {
   public static <T extends Comparable<T>> void sort(T [] array) {
      // implementation here...
      int n = array.length;  
      for (int i = 0; i < n - 1; i++) {  
         for (int j = 0; j < n - i - 1; j++) {  
            if (array[j].compareTo(array[j + 1]) > 0) {
            T temp = array[j];  
            array[j] = array[j + 1];  
            array[j + 1] = temp;  
            }  
         }
      }
   }
    
   public static <T> void reverse(T [] array) {
      // implementation here... 
      int left = 0;  
      int right = array.length-1;  
      while (left < right) {  
         T temp = array[left];  
         array[left] = array[right];  
         array[right] = temp;  
         left++;  
         right--; 
      }
   }

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {  
      if (fromArray == null || fromIndex < 0 || toIndex > fromArray.length || fromIndex >= toIndex) {  
         throw new IllegalArgumentException("Invalid array or range");  
      }  
      while (fromIndex < toIndex) {  
         int midIndex = fromIndex + (toIndex - fromIndex) / 2;  
         T midValue = fromArray[midIndex];  

         int comparison = aValue.compareTo(midValue);  
         if (comparison == 0) {  
            return midIndex;  
         } else if (comparison < 0) {  
            toIndex = midIndex;  
         } else {  
            fromIndex = midIndex + 1;  
         }  
      }  
      if (fromIndex == toIndex && fromArray[fromIndex].equals(aValue)) {  
         return fromIndex;  
      }  
      return -1;  
   }  
  
    public static <T extends Comparable<T>> void fastSort(T[] array) {  
        if (array == null || array.length <= 1) {  
            return;  
        }  
        quickSort(array, 0, array.length - 1);  
   }  
  
    private static <T extends Comparable<T>> void quickSort(T[] array, int begin, int end) {  
        if (begin < end) {  
            int partitionIndex = partition(array, begin, end);  
            quickSort(array, begin, partitionIndex - 1);  
            quickSort(array, partitionIndex + 1, end);  
        }  
   }  
  
    private static <T extends Comparable<T>> int partition(T[] array, int begin, int end) {  
        T pivot = array[end];  
        int i = begin;  
  
        for (int j = begin; j < end; j++) {  
            if (array[j].compareTo(pivot) <= 0) {  
                swap(array, i, j);  
                i++;  
            }  
        }  
        swap(array, i, end);  
        return i;  
   }  
  
    public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {  
        if (array == null || count <= 0) {  
            return 0;  
        }  
        int index = 0;  
        while (index < count && !rule.test(array[index])) {  
            index++;  
        }  
        if (index >= count) {  
            return count;  
        }  
        int writeIndex = index;  
        for (int readIndex = index + 1; readIndex < count; readIndex++) {  
            if (rule.test(array[readIndex])) {  
                swap(array, writeIndex, readIndex);  
                writeIndex++;  
            }  
        }  
        return writeIndex;  
    }  
  

    public static <T> void swap(T[] array, int i, int j) {  
        T temp = array[i];  
        array[i] = array[j];  
        array[j] = temp;  
    }  
  
    public static <T extends Comparable<T>> void sortWithComparator(T[] array, Comparator<T> comparator) {  
        if (array == null || array.length <= 1) {  
        return;  
    }  
  
    boolean swapped;  
    for (int i = 0; i < array.length - 1; i++) {  
        swapped = false;  
        for (int j = 0; j < array.length - 1 - i; j++) {  
            if (comparator.compare(array[j], array[j + 1]) > 0) {  
                swap(array, j, j + 1); 
                swapped = true;  
            }  
        }  
        if (!swapped) {  
            break;  
        }  
    }  
}

}
