package com.txr.spbbasic.demo.collection;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by xinrui.tian on 2019/5/24
 */
public class QueueStackTest {


    @Test
    public void test01() {
        //后进先出

        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");

        int len = stack.size();
        for (int i = 0; i < len; i++) {
            String pop = stack.pop();
            System.out.println(pop);
        }

        System.out.println(stack.size());
    }


    @Test
    public void test02() {
        //先进先出
        Queue<String> queue = new PriorityQueue();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");  //1,2,3,4
        // add 方法 源码使用 offer
        queue.offer("5");
        queue.offer("6");
        System.out.println(queue);

        //获取队列中第一个元素，为空报错
        String element = queue.element();  //AbstractQueue.element() 方法 底层源码用的peek
        System.out.println(element);
        //移除第一个
        String remove = queue.remove(); //AbstractQueue.remove() 方法 底层源码用的poll
        System.out.println(remove);

        System.out.println("======================================");
      /*  int len = queue.size();
        for (int i = 0; i < len; i++) {
            String poll = queue.poll();
            System.out.println("获取元素:" + poll);
            String peek = queue.peek();
            System.out.println("队列中第一个元素:" + peek);
        }
*/
        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }

        System.out.println("size" + queue.size());
    }
}
