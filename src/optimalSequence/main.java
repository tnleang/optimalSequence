package optimalSequence;

import java.util.ArrayList;
import java.util.LinkedList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Hello");
		
	}

	// information container for each test
	public static class node {
		String name;
		int cost;
		double posibility;
		double ratio;
		int sumCost;
		double sumPosibility;
		
		node(){
			name = "";
			cost = 0;
			posibility = 0;
			ratio = 0;
			sumCost = 0;
			sumPosibility = 0;
		}
		
		node(String n, int c, double prob){
			name = n;
			cost = c;
			posibility = prob;
			ratio = 0;
			sumCost = 0;
			sumPosibility = 0;
		}
	}
	
	
	/*    Ex: array of linkedLists that contain tests
	 *    testLists[0]		testLists[1]		testLists[2]
	 *    	T1					T5					T7
	 *    	|					|					|
	 *    	T2					T6					T8
	 *    	|										|
	 *    	T3										T9
	 *    	|
	 *    	T4
	 *
	 *
	 * Find the optimal sequence in a string and return it
	 *
	 * 	1. Let OP denotes an optimal sequence and set it to the empty string;
	 *	2. While ( the linked lists are not completely empty), Do the following four steps:
	 *	3.  For each test L[i][j], compute the ratio R[i][j] as follows:
   	 *			R[i][j] =  ((∑_(k=0)^j▒〖C[i][k]〗))/((∑_(k=0)^j▒〖P[i][k]〗) )
	 *	4. Choose the test with the smallest R ratio and has no predecessor test with the same R ratio; 
	 *	5. Add this test with its predecessors tests to the optimal sequence OP;
	 *	6. Remove this test and its predecessors from its linked list.
	 *	7.Return OP.
	 */
	public static LinkedList<node> findOptimalSequence (ArrayList<LinkedList<node>> testLists){
		int lowestI =0;
		int lowestJ =0;
		double lowestRatio; 
		LinkedList<node> op = new LinkedList<node>();
		
		while(!testLists.isEmpty()) {
			lowestRatio = Double.POSITIVE_INFINITY; //initial with high number that actual data will never be higher
			//  calculate the ratio
			calculateRatio(testLists);
			//  find the smallest ratio
			for( int i =0; i < testLists.size(); i++ ) {
				LinkedList<node> testList = testLists.get(i); // the whole list on each list
				for ( int j =0; j < testList.size(); j++) {
					node test = testList.get(j);
					if (lowestRatio > test.ratio) {
						lowestRatio = test.ratio;
						lowestI = i;
						lowestJ = j;
					}
				}
			}
			//  add to op
			LinkedList<node> tempTestList = testLists.get(lowestI);
			for (int i =0; i <= lowestJ; i++) {
				op.add(tempTestList.get(i));
				testLists.get(lowestI).removeFirst();
			}
			//  remove the added tests from the from its linked list
//			for (int i =0; i <= lowestJ; i++) {
//				testLists.get(lowestI).removeFirst();
//			}
		}
		
		return op;
	}
	
	
	// calculation for ratio
	//   R[i][j] =  ((∑_(k=0)^j▒〖C[i][k]〗))/((∑_(k=0)^j▒〖P[i][k]〗) )
	public static void calculateRatio (ArrayList<LinkedList<node>> testsLists) {
		int prevSumCost = 0;
		double prevSumposibility = 0;
		for(LinkedList<node> list : testsLists) {
			for(node test: list) {
				if (test == list.getFirst()) {  //case of the first test in the list
					test.sumCost = test.cost;
					test.sumPosibility = test.posibility;
				}else {
					test.sumCost = prevSumCost;
					test.sumPosibility = prevSumposibility;
				}
				
				test.ratio = test.sumCost / test.sumPosibility;
				prevSumCost = test.sumCost;
				prevSumposibility = test.sumPosibility;
			}
		}
	}
	
	public static void calculateTestListRatio (LinkedList<node> tests) {
		int prevSumCost = 0;
		double prevSumposibility = 0;
		for(node test: tests) {
			if (test == tests.getFirst()) {  //case of the first test in the list
				test.sumCost = test.cost;
				test.sumPosibility = test.posibility;
			}else {
				test.sumCost = prevSumCost;
				test.sumPosibility = prevSumposibility;
			}
			
			test.ratio = test.sumCost / test.sumPosibility;
			prevSumCost = test.sumCost;
			prevSumposibility = test.sumPosibility;
		}
	}
	
	// Display current test
	public static void printTestList ( LinkedList<node> list) {
		for(node n: list) {
			System.out.println("T -> " + n.name + ": " + n.cost + " " + n.posibility);
		}
	}
	
	public static void printAll (ArrayList<LinkedList<node>> lists ) {
		for (LinkedList<node> list : lists) {
			printTestList (list);
		}
	}
		
}
