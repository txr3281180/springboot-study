package com.txr.spbbasic.model.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BookList {
	private List<Book> bookList;
	private int index;
	
	public BookList() {
		bookList = new ArrayList<Book>();
	}
	
	//添加书籍
	public void addBook(Book book) {
		bookList.add(book);
	}
	
	//删除数据
	public void deleteBook(Book book) {
		int bookIndex = bookList.indexOf(book);
		bookList.remove(bookIndex);
	}
	
//	//判断是否有下一本书
//	public boolean hasNext() {
//		if(EleIndex >= bookList.size()) {
//			return false;
//		}
//		return true;
//	}
//	
//	//获得下一本书
//	public Book getNext() {
//		return bookList.get(EleIndex++);
//	}
	
//	public List<Book> getBookList() {
//		return bookList;
//	}
	
	public Iterator Iterator() {
		return new Itr();
	}
	
	private class Itr implements Iterator{   //内部类实现

		@Override
		public boolean hasNext() {
			if(index >= bookList.size()) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return bookList.get(index++);
		}
	}
}
