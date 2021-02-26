package com.test;

public class ItemTest {

	public static void main(String[] args) {
		Item item1 = new Item("Item1");
		Item item2 = new Item("Item2");
		Item item3 = new Item("Item3");
		Item item4 = new Item("Item4");
		//item1.item = item2;

		setItem(item1,item2);
		setItem(item1,item3);
		setItem(item1,item4);

		
		Item find = findItem(item1, "Item4");
		System.out.println(find.name);
	}
	
	static void setItem(Item thisItem,Item otherItem) {
		if (thisItem.item != null) {
			setItem(thisItem.item, otherItem);
		} else {
			thisItem.item = otherItem;
		}
	}
	
	//setItem2 : 錯誤的設計
	static void setItem2(Item thisItem,Item otherItem) {
		if (thisItem != null) {
			setItem2(thisItem.item, otherItem);
		} else {
			thisItem = otherItem;
		}
	}

	static Item findItem(Item item, String name) {
		if (item.name.equals(name)) {
			return item;
		} else {
			return findItem(item.item, name);
		}
	}

}

class Item {
	String name;
	Item item;
	
	public Item(Item item) {
		this.item=item;
	}

	public Item(String name) {
		this.name = name;
	}
}
