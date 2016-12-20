import java.util.LinkedList;

abstract class Animal{
	
	private int order;
	String name;
	
	Animal(String s){
		this.name=s;
	}
	
	public int getOrder(){
		return this.order;
	}
	
	void setOrder(int order){
		this.order = order;
	}
	
	boolean isOlderThan(Animal animal){
		return this.order<animal.getOrder();
	}
	
}

class Cat extends Animal{
	Cat(String s){
		super(s);
	}
}

class Dog extends Animal{
	Dog(String s){
		super(s);
	}
}

class AnimalQueue{
	
	LinkedList<Cat> cats;
	LinkedList<Dog> dogs;
	int order=0; 
	
	AnimalQueue(){
		cats=new LinkedList<Cat>();
		dogs = new LinkedList<Dog>();
	}
	
	void enqueue(Animal animal){
		
		animal.setOrder(order);
		order++;
		
		if(animal instanceof Dog){
			dogs.add((Dog)animal);
		}
		
		if(animal instanceof Cat){
			cats.add((Cat) animal);
		}
		
	}
	
	
	public Cat dequeueCats(){
		return cats.remove();
	}
	
	public Dog dequeueDogs(){
		return dogs.remove();
	}
	public Animal dequeueAny(){
		if(dogs.isEmpty()){
			return dequeueCats();
		}
		
		if(cats.isEmpty()){
			return dequeueDogs();
		}
		
		if(dogs.peek().isOlderThan(cats.peek())){
			return dequeueDogs();
		} else  {
			return dequeueCats();
		}
	}
}

public class Solution {
	
	public static void main(String args[]){
	
	AnimalQueue aq= new AnimalQueue();
	Dog dog1 = new Dog("hacker");
	Dog dog2 = new Dog("duffy");
	
	Cat cat1= new Cat("Katy");
	Cat cat2= new Cat("Kitty");

		aq.enqueue(dog1);
		aq.enqueue(dog2);
		aq.enqueue(cat1);
		aq.enqueue(cat2);
		
		System.out.println(aq.dequeueAny().name);
	
		System.out.println(aq.dequeueCats().name);
		System.out.println(aq.dequeueDogs().name);
	
	}
	

}


