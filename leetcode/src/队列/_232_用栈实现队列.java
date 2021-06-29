package 队列;

import java.util.Stack;

/**
 * @author goblin
 * @version 1.0.0
 * @className 队列._232_用栈实现队列
 * @description https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * @createTime 2021-03-19 16:01
 */
public class _232_用栈实现队列 {

    public static class  MyQueue {
        Stack<Integer> inStack;
        Stack<Integer> outStack;
        /** Initialize your data structure here. */
        public MyQueue() {
            inStack = new Stack<>();
            outStack = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            inStack.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (outStack.empty()) {
                while (!inStack.empty()) {
                    Integer e = inStack.pop();
                    outStack.push(e);
                }
            }
            return outStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (outStack.empty()) {
                while (!inStack.empty()) {
                    Integer e = inStack.pop();
                    outStack.push(e);
                }
            }
            return outStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return inStack.empty() && outStack.empty();
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());

    }
}
