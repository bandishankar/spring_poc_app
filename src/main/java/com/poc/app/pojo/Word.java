package com.poc.app.pojo;

/**
 * POJO for word count REST call
 * 
 * @author bandi shankar
 *
 */

public class Word {
	
	private String word;
	private int count;
	
	
	
	public Word() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Word(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Word [word=" + word + ", count=" + count + "]";
	}
	
	

}
