package oy.tol.tra;

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

   public static <T extends Comparable<T>> void quickSort(T[] array) {
      quickSort(array, 0, array.length - 1);
   }

   private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
      if (low < high) {
         int pi = partition(array, low, high);
         quickSort(array, low, pi - 1);
         quickSort(array, pi + 1, high);
      }
   }

   private static <T extends Comparable<T>> int partition(T[] array, int low, int high) {
      T pivot = array[high];
      int i = low - 1;
      for (int j = low; j < high; j++) {
         if (array[j].compareTo(pivot) < 0) {
            i++;
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
         }
      }
      T temp = array[i + 1];
      array[i + 1] = array[high];
      array[high] = temp;
      return i + 1;
   }
}
