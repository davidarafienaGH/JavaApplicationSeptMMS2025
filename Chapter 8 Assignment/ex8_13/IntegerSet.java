import java.util.Arrays;

public class IntegerSet {
    private final boolean[] set = new boolean[101];

    public IntegerSet() {
        Arrays.fill(set, false);
    }

    public static IntegerSet union(IntegerSet set1, IntegerSet set2) {
        IntegerSet resultSet = new IntegerSet();
        for (int i = 0; i <= 100; i++) {
            resultSet.set[i] = set1.set[i] || set2.set[i];
        }
        return resultSet;
    }

    public static IntegerSet intersection(IntegerSet set1, IntegerSet set2) {
        IntegerSet resultSet = new IntegerSet();
        for (int i = 0; i <= 100; i++) {
            resultSet.set[i] = set1.set[i] && set2.set[i];
        }
        return resultSet;
    }

    public void insertElement(int k) {
        checkRange(k);
        set[k] = true;
    }

    public void deleteElement(int m) {
        checkRange(m);
        set[m] = false;
    }

    public boolean isEqualTo(IntegerSet other) {
        return Arrays.equals(this.set, other.set);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean empty = true;
        for (int i = 0; i <= 100; i++) {
            if (set[i]) {
                sb.append(i).append(" ");
                empty = false;
            }
        }
        return empty ? "---" : sb.toString().trim();
    }

    private void checkRange(int val) {
        if (val < 0 || val > 100) {
            throw new IllegalArgumentException("Value out of bounds (0-100)");
        }
    }
}
