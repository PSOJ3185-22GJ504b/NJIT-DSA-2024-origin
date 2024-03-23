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
 
       if (aValue.equals(midValue)) {  
           return midIndex;  
       } else if (aValue.compareTo(midValue) < 0) {  
           toIndex = midIndex;
       } else {  
           fromIndex = midIndex + 1;  
       }  
   }  
   return -1;  
}

}