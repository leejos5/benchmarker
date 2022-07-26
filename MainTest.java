package hw3;

public class MainTest {
    
    public static void main(String[] theArgs) {
        MyBinarySearchTree<Integer> testTree = new MyBinarySearchTree<Integer>(true);
        testTree.add(10);
        testTree.add(20);
        testTree.add(30);
        testTree.add(40);
        testTree.add(50);
        testTree.add(25);
        testTree.add(29);
        testTree.add(2);
        testTree.add(60);
        testTree.add(100);
        testTree.add(43);
        testTree.add(18);
        testTree.add(4);
        testTree.add(400);
        
        System.out.println(testTree.toString());
    }
}
