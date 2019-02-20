package com.zjzx.util;
/**
 * 异步执行方法
 * @author Thinkpad
 *
 */
public abstract class AsyncUtil {
	abstract public void runMethod();
	public void excute(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				runMethod();
			}
		}).start();
	}
}
