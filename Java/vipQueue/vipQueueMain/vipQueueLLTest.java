//import vipQueueMain.vipQueue;


//package vipQueueMain;
//import vipQueueArray.vipQueue;

public class vipQueueLLTest {
	public static void main(String[] args) {

		vipQueue V = new vipQueue(5);

		
		V.enqueueVip(343);		
		System.out.println(V.count);		
		V.enqueueVip(232);
		V.enqueue(4);
		System.out.println(V.count);
		System.out.println(V.first.data);
		System.out.println(V.first.next.data);
		V.print();
		
		System.out.println(V.dequeue());
		V.print();
		System.out.println(V.dequeue());
		V.print();
		
		//V.print();
		
		System.out.print(V.dequeue());
		
		System.out.println("Should be: true  Is: " + V.isFull());
		System.out.println("Should be: false  Is: " + V.isEmpty());
		V.dequeue();
		V.print();
		V.dequeue();
		V.print();
		System.out.println("Should be: false  Is: " + V.isFull());
		System.out.println("Should be: true  Is: " + V.isEmpty());
		V.print();
		V.enqueue(2);	
		V.print();
		V.enqueue(3);

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

		
		
		
		
	}
}
