
//package vipQueueMain;
//import vipQueueArray.vipQueue;

public class vipQueueTest {
	public static void main(String[] args) {
	
		
		vipQueue Q = new vipQueue(10); //declaring a vipQueue of size 10
		for (int i=0; i<40; i++){
			if (!Q.isFull()) {
				Q.enqueue(i); //a "regular" enqueue
			} 
			if (!Q.isFull()) {
				Q.enqueueVip(i*i); //a vipEnqueue
			}
			Q.print();
		}
		while (!Q.isEmpty()) {
			System.out.printf("->%d", Q.dequeue());
		}
			
		
		/**
		vipQueue V = new vipQueue(5);
		System.out.println("Should be: false  Is: " + V.isFull());
		System.out.println("Should be: true  Is: " + V.isEmpty());
		V.enqueue(1);
		V.print();
		V.enqueue(2);	
		V.print();
		V.enqueue(3);
		V.vipEnqueue(343);
		System.out.println("Should be: false  Is: " + V.isEmpty());
		System.out.println("Should be: false  Is: " + V.isFull());
		V.enqueue(4);
		V.enqueue(5);
		V.print();
		V.enqueue(5);
		System.out.println("Should be: true  Is: " + V.isFull());
		V.print();
		System.out.println(V.dequeue());
		System.out.println(V.dequeue());
		V.print();
		**/
		
		
	}
}
