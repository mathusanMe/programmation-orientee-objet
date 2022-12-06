import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        // String[] tableau = {"a", "b", "c", null, "d"};
        // TestIter<String> iter = new TestIter<String>(tableau);
        // while (iter.hasNext()) {
        //     System.out.println(iter.next());
        // }
    
        TabSet<String> tabSet = new TabSet<String>();
        tabSet.add("a");
        tabSet.add("b");
        tabSet.add("c");
        tabSet.add("d");
        tabSet.add("e");
        System.out.println(tabSet.contains("a"));
        System.out.println(tabSet.isEmpty());
        tabSet.remove("a");
        for (String s : tabSet) {
            System.out.println(s);
        }
        tabSet.clear();
        tabSet.addAll(Arrays.asList("a", "b", "c", "d", "e"));
        tabSet.removeAll(Arrays.asList("a", "b", "c"));
        tabSet.retainAll(Arrays.asList("d"));
        for (String s : tabSet) {
            System.out.println(s);
        }
        System.out.println("---------");
        // Object[] tab = tabSet.toArray();
        // for (Object s : tab) {
        //     System.out.println(s);
        // }
        
        String[] tab = tabSet.toArray(new String[0]);
        for (String s : tab) {
            System.out.println(s);
        }
        tabSet.add("f");
        tabSet.add("g");
        tabSet.add("h");
        tabSet.add("i");
        tabSet.add("j");
        tabSet.add("k");
        tabSet.add("l");
        System.out.println("---------");
        System.out.println(tabSet.size());
        for (String s : tabSet) {
            System.out.println(s);
        }
    }
}
