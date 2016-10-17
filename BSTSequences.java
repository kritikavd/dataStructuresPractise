import java.util.ArrayList;
import java.util.LinkedList;

public class BSTSequences {

	public static void main(String argv[]){
		
		
		System.out.println("inside main");
		TreeNode root = null;
		root=TreeNode.insertNodeToBST(root, 20);
		root=TreeNode.insertNodeToBST(root, 25);
		root=TreeNode.insertNodeToBST(root, 10);
		root=TreeNode.insertNodeToBST(root, 15);
		root=TreeNode.insertNodeToBST(root, 5);
		if(root!=null){
			root.display();
		}
		
		ArrayList<LinkedList<Integer>> results= findSequences(root);
		for(LinkedList<Integer> resultList: results){
			System.out.println();
			for(Integer i:resultList){
				System.out.print(i+", ");
			}
		}
		
	}
	static ArrayList<LinkedList<Integer>> findSequences(TreeNode root){
		
		ArrayList<LinkedList<Integer>> results= new ArrayList<LinkedList<Integer>>();
		LinkedList<Integer> prefix= new LinkedList<Integer>();
		
		if(root==null)
		{
			results.add(new LinkedList<Integer>());
			return results;
		}
		
		prefix.add(root.val);
		ArrayList<LinkedList<Integer>> firstList= findSequences(root.left);
		ArrayList<LinkedList<Integer>> secondList = findSequences(root.right);
		
		for(LinkedList<Integer> first:firstList){
			for(LinkedList<Integer> second:secondList){
				results.addAll(weaveLists(first, second, prefix));
			}
		}
		
		return results;
	}
	
	static ArrayList<LinkedList<Integer>> weaveLists(LinkedList<Integer> first,LinkedList<Integer> second, LinkedList<Integer> prefix){
		ArrayList<LinkedList<Integer>> results = new ArrayList<LinkedList<Integer>>();
		
		if(first.isEmpty()){
			LinkedList<Integer> result= (LinkedList<Integer>)prefix.clone();
			result.addAll(result.size(), second);
			results.add(result);
			return results;
		}
		if(second.isEmpty()){
			LinkedList<Integer> result= (LinkedList<Integer>)prefix.clone();
			result.addAll(result.size(), first);
			results.add(result);
			return results;
		}
		
		Integer firstValue = first.removeFirst();
		prefix.addLast(firstValue);
		ArrayList<LinkedList<Integer>> result1= weaveLists(first, second, prefix);
		results.addAll(result1);
		first.addFirst(prefix.removeLast());
		
		firstValue = second.removeFirst();
		prefix.addLast(firstValue);
		ArrayList<LinkedList<Integer>> result2= weaveLists(first, second, prefix);
		results.addAll(result2);
		second.addFirst(prefix.removeLast());
		
		return results;
	}
}



class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int v){
		this.val=v;
	}
	
	static TreeNode insertNodeToBST(TreeNode root,int value){
		
		if(root==null){
			return new TreeNode(value);
		}else{
			if(value<=root.val){
				root.left= insertNodeToBST(root.left,value);
			} else{
				root.right=insertNodeToBST(root.right ,value);
			}
			return root;
		}	
	}
	
	void display(){
		
			System.out.print(this.val);
			if(null!=this.left){
				this.left.display();
			} 
			if(null!=this.right){
				this.right.display();
			}
	}
	
	
}
