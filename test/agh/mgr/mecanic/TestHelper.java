package agh.mgr.mecanic;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestHelper {
    public static boolean areEqualEdges(List<List<Integer>> expected, List<List<Integer>> actual){

        boolean equals = true;

        for(int i=0; i<expected.size(); i++){
            if(!expected.get(i).containsAll(actual.get(i))){
                equals = false;
            }
        }

        return equals;

    }

    @Test
    public void testEqualEdges() throws Exception {
        List<List<Integer>> expected = new LinkedList<List<Integer>>();
        List<List<Integer>> actual = new LinkedList<List<Integer>>();

        expected.add(new LinkedList<Integer>(Arrays.asList(1,2,3)));
        expected.add(new LinkedList<Integer>(Arrays.asList(4,5,6)));
        expected.add(new LinkedList<Integer>(Arrays.asList(7,8,9)));

        actual.add(new LinkedList<Integer>(Arrays.asList(1, 2, 3)));
        actual.add(new LinkedList<Integer>(Arrays.asList(4,5,6)));
        actual.add(new LinkedList<Integer>(Arrays.asList(7,8,9)));

        assertTrue(areEqualEdges(expected, actual));
    }
    @Test
    public void testNotEqualEdges() throws Exception {
        List<List<Integer>> expected = new LinkedList<List<Integer>>();
        List<List<Integer>> actual = new LinkedList<List<Integer>>();

        expected.add(new LinkedList<Integer>(Arrays.asList(1,2,3)));
        expected.add(new LinkedList<Integer>(Arrays.asList(4,5,6)));
        expected.add(new LinkedList<Integer>(Arrays.asList(7,8,9)));

        actual.add(new LinkedList<Integer>(Arrays.asList(1, 2, 3)));
        actual.add(new LinkedList<Integer>(Arrays.asList(4,5,6)));
        actual.add(new LinkedList<Integer>(Arrays.asList(7,8,10)));

        assertFalse(areEqualEdges(expected, actual));
    }

}
