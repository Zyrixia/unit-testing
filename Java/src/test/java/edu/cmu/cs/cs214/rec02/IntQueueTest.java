package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
       mQueue = new ArrayIntQueue();
    }

    @Test
    public void testIsEmptyOnNewQueue() {
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testIsEmptyOnNonEmptyQueue() {
        mQueue.enqueue(10);
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        assertTrue(mQueue.enqueue(20));
        assertEquals(1, mQueue.size());
    }


    @Test
    public void testPeekOnEmptyQueue() {
        assertNull(mQueue.peek());
        mQueue.enqueue(20);
    }

    @Test
    public void testPeekOnNonEmptyQueue() {
        mQueue.enqueue(20);
        assertEquals(20, (int)mQueue.peek());
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testDequeueOnEmptyQueue() {
        assertNull(mQueue.dequeue());
    }
    @Test
    public void testDequeueOnNonEmptyQueue() {
        mQueue.enqueue(10);
        mQueue.enqueue(20);
        assertEquals(10, (int)mQueue.dequeue());
        assertEquals(20, (int)mQueue.dequeue());
    }

    @Test
    public void testEnqueuePastCapacity() {
        for (int i = 1; i <= 20; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(20, mQueue.size());
    }

    @Test
    public void testClear() {
        mQueue.enqueue(10);
        mQueue.enqueue(20);
        mQueue.clear();

        assertTrue(mQueue.isEmpty());
        assertNull(mQueue.peek());
    }

    @Test
    public void testCircularList() {
        for (int i = 0; i < 10; i++) {
            mQueue.enqueue(i);
        }
        for (int i = 0; i < 5; i++) {
            mQueue.dequeue();
        }
        for (int i = 10; i < 15; i++) {
            mQueue.enqueue(i);
        }
        assertEquals(5, (int)mQueue.dequeue());
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }


}
