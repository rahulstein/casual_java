package solutions;

import java.util.Scanner;

public class QHEAP1 {

	private int[] heap;
	private int count;
	
	public QHEAP1(int Q) {
		heap = new int[Q];
		count = 0;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int Q = in.nextInt();
		QHEAP1 o = new QHEAP1(Q);
		for(int i = 0; i < Q; i++){
			int queryType = in.nextInt();
			
			switch(queryType){
			case 1:o.insert(in.nextInt()); break;
			case 2:o.delete(in.nextInt()); break;
			case 3:o.print(); break;
			default:return;
			}
		}
	}

	public void print() {
		if(count>0)
			System.out.println(heap[0]);
		else
			System.out.println("No elements.");
	}

	public void delete(int element) {
		int curr = search(element,0);
		int delelem = heap[curr];
		heap[curr] = heap[count-1];
		count--;
		while(curr <= (count-1)/2){
			int left = 2*curr + 1;
			int right = 2*curr + 2;
			if(left<=count && heap[curr]>heap[left]){
				swap(curr,left);
				curr = left;
			} else if(right<=count && heap[curr]>heap[right]){
				swap(curr,right);
				curr = right;
			} else {
				break;
			}
			
		}
	}

	/**
	 * returns position of element
	 * @param element
	 * @param i starting position of search
	 * @return position of element in heap
	 */
	private int search(int element, int curr) {
		if(curr>count){
			return 0;
		}
		int left = 2*curr +1, right = left+1;
		if(element==heap[curr]){
			return curr;
		}
		else {
			int found = search(element,left);
			if(found!=0){
				return found;
			} else{
				return search(element,right);
			}
		}
	}

	public void insert(int element) {
		heap[count++] = element;
		int curr = count - 1;
		while(curr > 0){
			int par = (curr - 1)/2;
			if(heap[par] > heap[curr]){
				swap(par,curr);
				curr = par;
			}
			else{
				break;
			}
		}
	}

	private void swap(int oldp, int newp) {
		int temp = heap[oldp];
		heap[oldp] = heap[newp];
		heap[newp] = temp;
	}
	
}