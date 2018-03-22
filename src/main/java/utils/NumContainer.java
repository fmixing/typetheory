package utils;

public class NumContainer {

    private int count = 0;

    public int increment() {
        count++;
        return count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "NumContainer{" +
                "count=" + count +
                '}';
    }
}
