// package oy.tol.tira.books;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;
// import java.nio.charset.StandardCharsets;
// import java.util.Locale;

// public class KeyValueHashTable implements Book {


//     private static final int MAX_WORDS = 2000000;
//     private static final int MAX_WORD_LEN = 200;
//     private static final double LOAD_FACTOR = 0.45;
//     private Count[] words = null;
//     private String bookFile = null;
//     private String wordsToIgnoreFile = null;
//     private WordFilter filter = null;
//     private int maxProbingSteps = 0;
//     private int uniqueWordCount = 0;
//     private int totalWordCount = 0;
//     private int ignoredWordsTotal = 0;
//     private long collisionCount = 0;
//     private long reallocationCount = 0;

//     @Override
//     public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {
//         boolean success = false;
//         if (checkFile(fileName)) {
//             bookFile = fileName;
//             if (checkFile(ignoreWordsFile)) {
//                 wordsToIgnoreFile = ignoreWordsFile;
//                 success = true;
//             }
//         }
//         if (!success) {
//             throw new FileNotFoundException("Cannot find the specified files");
//         }
//     }
//     private boolean checkFile(String fileName) {
//         if (fileName != null) {
//             File file = new File(fileName);
//             if (file.exists() && !file.isDirectory()) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     @Override
//     public void countUniqueWords() throws IOException, OutOfMemoryError {
//         if (bookFile == null || wordsToIgnoreFile == null) {
//             throw new IOException("No file(s) specified");
//         }
//         uniqueWordCount = 0;
//         totalWordCount = 0;
//         collisionCount = 0;
//         ignoredWordsTotal = 0;
//         words = new Count[MAX_WORDS];
//         filter = new WordFilter();
//         filter.readFile(wordsToIgnoreFile);
//         FileReader reader = new FileReader(bookFile, StandardCharsets.UTF_8);
//         int c;
//         int[] array = new int[MAX_WORD_LEN];
//         int currentIndex = 0;
//         while ((c = reader.read()) != -1) {
//             if (Character.isLetter(c)) {
//                 array[currentIndex] = c;
//                 currentIndex++;
//             } else {
//                 if (currentIndex > 0) {
//                     String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
//                     currentIndex = 0;
//                     addToWords(new Count(word, 1));
//                 }
//             }
//         }
//         if (currentIndex > 1) {
//             String word = new String(array, 0, currentIndex).toLowerCase(Locale.ROOT);
//             addToWords(new Count(word, 1));
//         }
//         reader.close();
//     }


//     private void addToWords(Count wordcount) throws OutOfMemoryError {
//         if (!filter.shouldFilter(wordcount.word) && wordcount.word.length() >= 2) {
//             if (((double)uniqueWordCount * (1.0 + LOAD_FACTOR)) >= words.length) {
//                 reallocate((int)((double)(words.length) * (1.0 / LOAD_FACTOR)));
//             }
//             int hash=wordcount.hashCode();
//             int index=hash%words.length;
//             if(index<0){
//                 index+=words.length;
//             }
//             int tmpIndex;
//             for(int i=0;;i++){
//                 tmpIndex=(index+i*i)%words.length;
//                 if(words[tmpIndex]==null){
//                     words[tmpIndex]=wordcount;
//                     uniqueWordCount++;
//                     break;
//                 }else if(words[tmpIndex].word.equals(wordcount.word)){
//                     words[tmpIndex].count+=1;
//                     break;
//                 }
//                 collisionCount++;
//                 if(i>maxProbingSteps){
//                     maxProbingSteps=i;
//                 }
//             }
//         } else {
//             ignoredWordsTotal++;
//         }
//     }

//     private void reallocate(int newSize) throws OutOfMemoryError {
//         if (newSize < MAX_WORDS) {
//             newSize = MAX_WORDS;
//         }
//         reallocationCount++;
//         Count[] oldWords = words;
//         this.words = new Count[(int)((double)newSize * (1.0 + LOAD_FACTOR))];
//         uniqueWordCount = 0;
//         collisionCount = 0;
//         maxProbingSteps = 0;
//         for (int index = 0; index < oldWords.length; index++) {
//             if (oldWords[index] != null) {
//                 addToWords(oldWords[index]);
//             }
//         }
//     }

//     private void Arrayreallocate(int newSize) throws OutOfMemoryError {
//         Count[] newWords = new Count[newSize];
//         for (int index = 0; index < newSize; index++) {
//             newWords[index] = words[index];
//             totalWordCount+=words[index].count;
//         }
//         words = newWords;
//     }



//     @Override
//     public void report() {
//         if (words == null) {
//             System.out.println("*** No words to report! ***");
//             return;
//         }
//         System.out.println("Listing words from a file: " + bookFile);
//         System.out.println("Ignoring words from a file: " + wordsToIgnoreFile);
//         System.out.println("Sorting the results...");
//         int length=Algorithms.partitionByRule(words, words.length, element->element==null);
//         Arrayreallocate(length);
//         Algorithms.fastSort(words);
//         System.out.println("...sorted.");

//         for (int index = 0; index < 100; index++) {
//             if (index>=words.length) {
//                 break;
//             }
//             String word = String.format("%-20s", words[index].word).replace(' ', '.');
//             System.out.format("%4d. %s %6d%n", index + 1, word, words[index].count);
//         }
//         System.out.println("Count of words in total: " + totalWordCount);
//         System.out.println("Count of unique words:    " + uniqueWordCount);
//         System.out.println("Count of words to ignore:    " + filter.ignoreWordCount());
//         System.out.println("Ignored words count:      " + ignoredWordsTotal);
//         System.out.println("Data of the HashTable: ");
//         System.out.println("Count of collision: " + collisionCount);
//         System.out.println("Count of reallocation: " + reallocationCount);
//         System.out.println("Max ProbingSteps: " + maxProbingSteps);

//     }
//     @Override
//     public void close() {
//         bookFile = null;
//         wordsToIgnoreFile = null;
//         words = null;
//         if (filter != null) {
//             filter.close();
//         }
//         filter = null;
//     }
//     @Override
//     public int getUniqueWordCount() {
//         return uniqueWordCount;
//     }
//     @Override
//     public int getTotalWordCount() {
//         return totalWordCount;
//     }
//     @Override
//     public String getWordInListAt(int position) {
//         if (words != null && position >= 0 && position < uniqueWordCount) {
//             return words[position].word;
//         }
//         return null;
//     }
//     @Override
//     public int getWordCountInListAt(int position) {
//         if (words != null && position >= 0 && position < uniqueWordCount) {
//             return words[position].count;
//         }
//         return -1;
//     }
// }