import java.util.ArrayList;
import java.util.LinkedList;

class Node{
	Node left;
	Node right;
	int data;
	
	Node (int data){
		this.data=data;
	}
}
public class Solution {

	
	static ArrayList<LinkedList<Integer>> allArrays(Node root){
		 ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
		if(root==null){
			
			result.add(new LinkedList<Integer>());
			return result;
			
		}
		
		LinkedList<Integer> prefixList = new LinkedList<Integer>();
		prefixList.add(root.data);
		
		ArrayList<LinkedList<Integer>> leftLists = allArrays(root.left);
		ArrayList<LinkedList<Integer>> rightLists = allArrays(root.right);
		
		for(LinkedList<Integer> leftList : leftLists){
			for(LinkedList<Integer> rightList: rightLists){
				
				weaveLists(leftList,rightList,result,prefixList);
			}
		}
		
		return result;
	}
	
	static void weaveLists(LinkedList<Integer> leftList,LinkedList<Integer> rightList,ArrayList<LinkedList<Integer>> results,LinkedList<Integer> prefixList ){
		
		if(leftList.size()==0 || rightList.size()==0){
			LinkedList<Integer> result = (LinkedList<Integer>)prefixList.clone();
			result.addAll(leftList);
			result.addAll(rightList);
			results.add(result);
			return ;

		}
		
		prefixList.addLast(leftList.removeFirst());
		weaveLists(leftList,rightList,results,prefixList);
		leftList.addFirst(prefixList.removeLast());
		
		prefixList.addLast(rightList.removeFirst());
		weaveLists(leftList,rightList,results,prefixList);
		rightList.addFirst(prefixList.removeLast());
		
		
	}
	
	public static void main(String args[]){
		
		Node root = new Node(2);
		root.left= new Node (1);
		root.right=new Node(3);
		
		 ArrayList<LinkedList<Integer>> result = allArrays( root);
		 
		 for(LinkedList<Integer> ll : result){
			 for(int i=0;i<ll.size();i++){
				 System.out.print(ll.get(i)+" ");
			 }
			 System.out.println();
		 }
	}
	
	
}
