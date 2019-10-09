package com.leetCode;

import org.junit.Test;

import java.util.*;

/**
 * Created by Calm on 2018/8/23
 */
public class LeetCodeTest {

    public static void main(String[] args) {

    }

    /**
     * 两句话中的不常见单词
     */
    @Test
    public static void testOne(){
        String A = "this apple is sweet";
        String B = "this apple is sour";
        String[] splitA = A.split(" ");
        String[] splitB = B.split(" ");
        Map<String,Integer> map = new HashMap<>();
        for (String strA :
                splitA) {
            map.put(strA,map.getOrDefault(strA,0) + 1);
        }
        for (String strB :
                splitB) {
            map.put(strB,map.getOrDefault(strB,0) + 1);
        }
        List<String> list = new LinkedList<>();
        for (String str:
                map.keySet()) {
            if(map.get(str) == 1){
                list.add(str);
            }
        }
        String[] result = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(result));
    }


    /**
     * 螺旋矩阵
     */
    @Test
    public void testTwo(){

    }




}
