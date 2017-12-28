package lovin.localTest;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by bixin on 2017/10/18.
 */
public class CollectionUtilTest {
    public static void main(String[] args) {
        // 反向迭代
        ArrayList<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("hello");
        list.add("world");
        list.add("test");
        ListIterator<String> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }
}
