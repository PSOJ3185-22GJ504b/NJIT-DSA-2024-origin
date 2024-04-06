package oy.tol.tira.books;

import java.util.Arrays;
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

    public static <E extends Comparable<E>> void fastSort(E [] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static <T>void swap(T[] array,int index1,int index2){
        T temp=array[index1];
        array[index1]=array[index2];
        array[index2]=temp;
    }

    private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
        E p=array[begin];
        int left=begin;
        int right=end;
        while(left!=right){
            while ((left<right)&&array[right].compareTo(p)>0) {
                right--;
            }
            while ((left<right)&&array[left].compareTo(p)<=0) {
                left++;
            }
            if(left<right){
                swap(array, left, right);
            }
        }
        array[begin]=array[left];
        array[left]=p;
        return left;
    }

    public static <T> int partitionByRule(T [] pairs,int count,Predicate<T> judgeNullPredicate){
        int left = 0;
        int right = count - 1;
        while (left <= right) {
            while (left <= right && !judgeNullPredicate.test(pairs[left])) {
                left++;
            }
            while (left <= right && judgeNullPredicate.test(pairs[right])) {
                right--;
            }
            if (left < right) {
                swap(pairs, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static <T> void sortWithComparator( T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }
}