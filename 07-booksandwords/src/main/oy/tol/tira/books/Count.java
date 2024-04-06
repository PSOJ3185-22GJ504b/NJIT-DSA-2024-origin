package oy.tol.tira.books;


public class Count implements Comparable<Count> {
    String word;
    Count left;
    Count right;
    int hash;
    int count;
    LinkedListImplementation<Count> list = null;

    public Count(){
        this.word="";
        this.count=0;
        Count left=null;
        Count right=null;
        hash=hashCode();
    }

    public Count(final Count wordcount) {
        this.word = new String(wordcount.word);
        this.count = wordcount.count;
        Count left=null;
        Count right=null;
        hash=hashCode();
    }

    public Count(String word, int count) {
        this.word = word;
        this.count = count;
        Count left=null;
        Count right=null;
        hash=hashCode();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word){
        this.word=word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count=count;
    }

    public static void binaryTreeToListArray(Count node) {
        if (node == null) {
            return;
        }
        binaryTreeToListArray(node.left);
        if (node.list != null) {
            for (int i = 0; i < node.list.size(); i++) {
                Count current = node.list.get(i);
                KeyValueBSearchTree.words[KeyValueBSearchTree.indexOfWordCount++] = current;
            }
        } else {
            KeyValueBSearchTree.words[KeyValueBSearchTree.indexOfWordCount++] = node;
        }
        binaryTreeToListArray(node.right);
    }

    private int uniqueWordCount;
    private int maxProbingSteps;

    public int getUniqueWordCount() {
        return uniqueWordCount;
    }

    public void setUniqueWordCount(int uniqueWordCount) {
        this.uniqueWordCount = uniqueWordCount;
    }

    public int getMaxProbingSteps() {
        return maxProbingSteps;
    }

    public void setMaxProbingSteps(int maxProbingSteps) {
        this.maxProbingSteps = maxProbingSteps;
    }

    public void insert(Count wordCount, int toInsertHash) {
        if (toInsertHash < this.hash) {
            if (left == null) {
                left = wordCount;
                uniqueWordCount++;
            } else {
                left.insert(wordCount, toInsertHash);
            }
        } else if (toInsertHash > this.hash) {
            if (right == null) {
                right = wordCount;
                uniqueWordCount++;
            } else {
                right.insert(wordCount, toInsertHash);
            }
        } else {
            if (this.equals(wordCount)) {
                this.count++;
            } else {
                if (list == null) {
                    list = new LinkedListImplementation<>();
                    list.add(wordCount);
                    uniqueWordCount++;
                } else {
                    Count newItem = wordCount;
                    int index = list.indexOf(newItem);
                    if (index < 0) {
                        list.add(newItem);
                        uniqueWordCount++;
                    } else {
                        list.get(index).count++;
                    }
                }
                if (list.size() > maxProbingSteps) {
                    maxProbingSteps = list.size();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "word="+word+" count="+count;
    }

    @Override
    public int hashCode() {
        int hash=0;
        String hashString=word;
        for (int i = 0; i < hashString.length(); i++) {
            hash=37*hash+hashString.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Count) {
            return this.word.equals(((Count)other).word);
        }
        return false;
    }

    @Override
    public int compareTo(Count other) {
        return (other.count)-count;
    }
}

