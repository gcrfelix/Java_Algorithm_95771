/**
 * The Queue is a first in first out data structure. This Queue holds Java
 * Object references. It will grow dynamically as long as memory is available.
 * 
 * @author zhaoru
 *
 */
public class Queue {
	private int front, rear, size, num;
	private Object[] queue;

	/**
	 * Constructor Create an empty queue.
	 */
	public Queue() {
		size = 10;
		num = 0;
		front = 0;
		rear = 0;
		queue = new Object[size];
	}

	/**
	 * Boolean method returns true on empty queue, false otherwise.
	 * Pre-condition: None Post-condition: a boolean value is returned BigTheta:
	 * 1 (no case)
	 * 
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return num == 0;
	}

	/**
	 * Boolean method returns true if queue is currently at capacity, false
	 * otherwise. If full returns true and additional enqueue calls are made,
	 * the queue will expand in size. Pre-condition: None Post-condition: a
	 * boolean value is returned BigTheta: 1 (no case)
	 * 
	 * @return true if queue is at current capacity, false otherwise
	 */
	public boolean isFull() {
		return num == size;
	}

	/**
	 * Object method removes and returns reference in front of queue.
	 * Pre-condition: queue not empty Post-condition: first in object is
	 * returned BigTheta: 1 (no case)
	 * 
	 * @return object in front of queue.
	 */
	public Object deQueue() {
		Object answer = queue[front];
		num--;
		if (!isEmpty())
			front = (front + 1) % size;
		return answer;
	}

	/**
	 * Add an object reference to the rear of the queue. Pre-condition: Memory
	 * is available for doubling queue capacity when full. Post-condition: an
	 * object reference is added to the rear of the queue. The queue may be
	 * doubled size. Best case BigTheta: 1 Worst case BigTheta: n (size of the
	 * queue)
	 * 
	 * @param x
	 *            x - is an object to be added to the rear of the queue.
	 */
	public void enQueue(Object x) {
		if (isFull())
			queue = doubleSize();
		if (!isEmpty())
			rear = (rear + 1) % size;
		num++;
		queue[rear] = x;
	}

	/**
	 * Method getFront returns the front of the queue without removing it.
	 * Pre-condition: queue not empty Post-condition: the queue front is
	 * returned without removal BigTheta: 1 (no case)
	 * 
	 * @return the queue front without removal.
	 */
	public Object getFront() {
		return queue[front];
	}

	/**
	 * The toString method returns a String representation of the current queue
	 * contents.
	 * 
	 * @return a string representation of the queue.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = front; i < rear + 1; i++) {
			sb.append(queue[i]);
		}
		return sb.toString();
	}

	/**
	 * helper method to double the size of the queue. BigTheta: n (size of the
	 * queue)
	 * 
	 * @return the new queue with doubled size
	 */
	private Object[] doubleSize() {
		size *= 2;
		Object[] newQueue = new Object[size];
		System.out.println("[Queue size overflowed thus doubled from " + size
				/ 2 + " to " + size + "]");
		/*
		 * copy from front to old end, and move rest of old queue to append to
		 * the new queue. finally fix the rear index
		 */
		for (int i = front; i < size / 2; i++) {
			newQueue[i] = queue[i];
		}
		for (int j = size / 2; j < size / 2 + rear + 1; j++) {
			newQueue[j] = queue[j - size / 2];
		}
		rear += size / 2;
		return newQueue;
	}
}
