package sbt.edu.sharedcounter.combiningtree;

import sbt.edu.sharedcounter.SharedCounter;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CombiningTreeCounter implements SharedCounter {
    private Node[] leaf;
    int threadID = -1;
    private Node root;

    private int getThreadId() {
       // System.out.println("Thread Name " + Thread.currentThread().getName());

        String[] tokens = Thread.currentThread().getName().split("-");
        //System.out.println("Thread's N" + tokens[tokens.length - 1]);
        if (tokens[tokens.length - 1].equals("main")) {
            threadID = 0;
        } else {
            threadID = Integer.parseInt(tokens[tokens.length - 1].trim());
        }
        return threadID;
    }

    public CombiningTreeCounter(int width) {
        Node[] nodes = new Node[width - 1];
        nodes[0] = new Node();
        root = nodes[0];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(nodes[(i - 1) / 2]);  //stores tree into array
        }
        leaf = new Node[(width + 1) / 2];
        for (int i = 0; i < leaf.length; i++) {
            leaf[i] = nodes[nodes.length - i - 1];
        }
    }

    public long get() {
        return root.getResult();
    }

    public long getAndIncrement() throws Exception {
        {
            Stack<Node> stack = new Stack<Node>();
            threadID = getThreadId();
            Node myLeaf = leaf[(threadID) / 2];
            Node node = myLeaf;
            // precombining phase 
            while (node.precombine()) {
                node = node.getParent();
            }
            Node stop = node;
            // combining phase 
            node = myLeaf;
            long combined = 1;
            while (node != stop) {
                combined = node.combine(combined);
                stack.push(node);
                node = node.getParent();
            }
            // operation phase 
            long prior = stop.op(combined);
            // distribution phase 
            while (!stack.empty()) {
                node = stack.pop();
                node.distribute(prior);
            }
            return prior;

        }
    }
}
class Node {
        private boolean locked = false;
        private Status status;
        private long firstValue, secondValue;
        private long result;
        private  Node parent;
        //private final  Lock lock;// = new ReentrantLock();
        private final Object condition; //= /*new Object();*/lock.newCondition();
        public Node() {
            status = Status.ROOT;
            //lock = new ReentrantLock();
            condition = new Object();// lock.newCondition();
        }

        public Node(Node parent) {
            this.parent = parent;
            status = Status.IDLE;
            //lock = new ReentrantLock();
            condition = new Object();//lock.newCondition();
        }


        public synchronized boolean precombine() throws Exception {
            while (locked) {
                synchronized (condition) {
                    condition.wait();
                }
            }
            switch (status) {
                case IDLE:
                    status = Status.FIRST;
                    return true;
                case FIRST:
                    //lock.lock();
                    locked = true;
                    status = Status.SECOND;
                    return false;
                case ROOT:
                    return false;
                default:
                    throw new Exception("unexpected Node state " + status);
            }
        }

        public long  combine(long combined) throws Exception {
            while (locked) {
                synchronized (condition) {
                    condition.wait();
                }
            }
            //lock.lock();
            locked = true;
            firstValue = combined;
            switch (status) {
                case FIRST:
                    return firstValue;
                case SECOND:
                    return firstValue + secondValue;
                default:
                    throw new Exception("unexpected Node state " + status);
            }
        }

        public void distribute(long prior) throws Exception {
            switch (status) {
                 case FIRST:
                    status = Status.IDLE;
                    //lock.unlock();
                     locked = false;
                     break;
                case SECOND:
                     result = prior + firstValue;
                    status = Status.RESULT;
                     break;
                default:
                    throw new Exception("unexpected Node state");
            }
             //if (locked)
            synchronized (condition) {
                //condition.signalAll();

                condition.notifyAll();
            }
        }

         long op(long combined) throws Exception {
            switch (status) {
                case ROOT:
                     long prior = result;
                     result += combined;
                     return prior;
                case SECOND:
                     secondValue = combined;
                     //condition.signalAll();
                     //lock.unlock();
                     locked = false;
                     //condition.signalAll();//
                     synchronized (condition){
                         condition.notifyAll(); // wake up waiting threads
                     }
                     while (status != Status.RESULT) {
                         synchronized (condition) {
                             condition.wait();
                         }
                     }
                     //condition.signalAll();
                     //lock.unlock();
                     locked = false;
                     //condition.signalAll();//
                     synchronized (condition) {
                         condition.notifyAll();
                     }
                     status = Status.IDLE;
                     return result;
                default:
                     throw new Exception("unexpected Node state");
            }
        }

    public Node getParent() {
            return parent;
    }

    public long getResult() {
            return result;
    }
}

