package vipQueueArray;
 

/**
*defines a class vipQueue with array implementation
*@author BDavis
**/

public class vipQueue {
	
	/** count along array **/
	private int count;
	
	/** array **/	
	private int[] queue;
	
	/** length of array **/
	private int len;
	
	/** 
	 * constructor of vipQueues
	 * @pre length must be an integer
	 * @param length is the maximum length of the queue
	 * **/
	public vipQueue(int length) {
		queue = new int[length];
		this.count = 0;
		this.len = queue.length;
	}
	
	/** 
	 * enqueue-er of integers
	 * @pre enqueued number must be an integer
	 * @param number is number to enqueue 
	**/
	public void enqueue(int number) {
		if (count == len) {
			System.out.println("Queue is full");	
		} else {
			this.queue[this.count] = number;
			count++;
		}
	}
	
	/** 
	 * enqueue-er of VIPintegers
	 * @pre enqueued number must be an integer
	 * @param number is number to enqueue at beginning of queue
	**/
	public void enqueueVip(int number) {
		if (count == len) {
			System.out.println("Queue is full");
		} else {
			for (int i = len-1; i > 0; i --) {
				queue[i] = queue[i-1];
			}
			queue[0] = number;
			count++;
		}
	}
	
	/** 
	 * dequeue-er of integers 
	 * @return number dequeued 
	**/
	public int dequeue() {
		int tmp = this.queue[0];
		for (int i = 0; i < count-1; i++) {
			queue[i]= queue[i+1];
		}
		count--;
		return tmp;
	}
	
	/** 
	 * prints the current contents of the queue  
	**/
	public void print() {
		for (int i = 0; i < this.count; i++) {
			System.out.print(queue[i] + ", ");
		}
		System.out.println();
	}
	
	/** 
	 * tells whether queue is full
	 * @return boolean indicating full or not 
	**/	
	public boolean isFull() {
		return count == this.len;
	}
	
	/** 
	 * tells whether queue is empty
	 * @return boolean indicating empty or not 
	**/
	public boolean isEmpty() {
		return count == 0;
	}
}
