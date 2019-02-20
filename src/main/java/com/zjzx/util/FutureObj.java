package com.zjzx.util;

import java.io.Serializable;

public class FutureObj<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T  value = null ;
	private Boolean success = null;
	public void setValue(T value){
		this.value = value;
	}
	
	public T getValue(){
		while(true){
			if(this.value !=null){
				return this.value;
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setSuccess(boolean flag){
		this.success = flag;
	}
	
	public Boolean getSuccess(){
		while (true) {
			if(this.success !=null){
				return this.success;
			}
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
