package vipQueueMain;

/**
 * 
 * Implements VIPqueue with linkedlist
 * @author B
 *
 */
public class vipQueue {
	
	public int length;
	public int count;
	public Node first;
	public Node current;
	//private Node next_in_line;
	//private Node previous_in_line;
	
	/**
	 * Constructor for vipQueue
	 * @pre must be integer
	 * @param len the length of the queue
	 **/
	public vipQueue(int len){
		this.length = len;
		this.count = 0;
	}
	
	/**
	 * Enqueues a new number in line behind already existing ones
	 * @pre must be integer
	 * @param i is integer to be enqueued
	 **/
	public void enqueue(int i) {
		if (count > length-1) {
			System.out.println("Queue is full");
			return;
		}
		
		Node newNode = new Node(i);
		if (count == 0) {
			first = newNode;
			current = first;
			count++;
		} else {
			current.next = newNode;
			newNode.previous = current;
			current = current.next;
			count++;
		}
		System.out.println(count);
	}
	
	/**
	 * Enqueues new number at the beginning of the queue
	 * @pre must be integer
	 * @param number
	 */
	public void enqueueVip(int number) {
		if (count > length-1) {
			System.out.println("Queue is full");
			return;
		}
		
		Node newVIP = new Node(number);
		
		if (count == 0) {	
			first = newVIP;
			current = first;
			count ++;
		} else {
			first.previous = newVIP;
			newVIP.next = first;
			first = newVIP;
			count++;
		}
	}
	
	/**
	 * Dequeues first number in queue 
	 * @return tmp - the number at the front of the line
	 */
	public int dequeue() {
		if (count != 0) {
			int tmp = first.data;
			first = first.next;
			count--;
			return tmp;
		} else {
			System.out.println("Queue is empty");
			return 0;
		}
	}
	/**
	 * Prints queue as it stands
	 */
	public void print() {
		Node tmp = first;
		int counttmp = count;
		for (int i = 0; i < count; i++) {
			System.out.print(tmp.data + ", ");
			tmp = tmp.next;
		}
		System.out.println();
	}
	
	/**
	 * Returns true/false indicating whether queue is full
	 * @return
	 */
	public boolean isFull() {
		return (count == length);
	}

	/**
	 * Returns true/false indicating whether queue is empty
	 * @return
	 */
	public boolean isEmpty() {
		return (count == 0);
	}

}
