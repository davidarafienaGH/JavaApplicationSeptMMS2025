public class IntegerSetTest {
    public static void main(String[] args) {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.insertElement(10);
        set1.insertElement(20);
        set1.insertElement(30);

        set2.insertElement(20);
        set2.insertElement(40);

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        System.out.println("Union: " + IntegerSet.union(set1, set2));
        System.out.println("Intersection: " + IntegerSet.intersection(set1, set2));

        set1.deleteElement(10);
        System.out.println("Set 1 after deleting 10: " + set1);
    }
}
