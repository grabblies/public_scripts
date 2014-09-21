import java.util.Random;


public class DynamicDictionaryTest {
	private static Random rand = new Random(10);
	
	public static void main(String[] args) {
		
		BSTDD library = new BSTDD();
		AVLDD dict = new AVLDD();
		
		/**  // for BST
		for(int i = 0; i < 30; i++) {
			int tmpkey = rand.nextInt(100);
			//System.out.println(Integer.toString(tmpkey));
			library.insert(tmpkey, Integer.toString(tmpkey));
		}
		
		library.print();
		System.out.println("input complete");
		System.out.println("min: " + library.findMin());
		System.out.println("max: " + library.findMax());
		System.out.println("finding 8:" + library.find(8));
		System.out.println("finding 72:" + library.find(72));	
		System.out.println("removing 70:");
		library.remove(70);
		library.print();
		
		int[] test = new int[15];
		System.out.print("[ ");
		for (int i = 0; i < test.length; i++) {
			test[i] = rand.nextInt(50);
			System.out.print(test[i] + ", ");
		}
		System.out.println(" ]");
		
		

		for (int y = 0; y <test.length; y++) {
			dict.insert(test[y], Integer.toString(test[y]));
		}
		
		//insert a bunch of stuff

		for (int i = 0; i < 100; i += 2) {
			int k = rand.nextInt(400) - 100;
			dict.insert(k, Integer.toString(k)); System.out.println("height when inserting " + k + ": " +dict.height());
			
		}**/
		
		dict.insert(20, "20"); System.out.println("height when inserting 20: " +dict.height());
		dict.insert(15, "15"); System.out.println("height when inserting 15: " +dict.height());
		dict.insert(25, "25"); System.out.println("height when inserting 25: " +dict.height());
		dict.insert(10, "10"); 	System.out.println("height when inserting 10: " +dict.height());
		//dict.insert(8, "8"); 	System.out.println("height when inserting 8: " +dict.height());
		dict.insert(12, "12"); 	System.out.println("height when inserting 12: " +dict.height());
		//dict.insert(30, "30"); 	System.out.println("height when inserting 8: " +dict.height());
		dict.print();
				/**
		//dict.insert(6, "6");
		//dict.insert(3, "3");
		//dict.insert(1, "1");
		//dict.print();
		
		//remove some things
		dict.remove(5);
		dict.print();
		
		/**
		dict.print();
		System.out.println("Dict max: " + dict.findMax());
		System.out.println("Dict min: " + dict.findMin());
		System.out.println("Dict height: " + dict.height());
		System.out.println("removing 5");
		dict.remove(5);
		System.out.println("Dict height: " + dict.height());
		**/
		
		
	}

}
