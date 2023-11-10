package com.sh.thread;

public class TimerThread extends Thread{
	
	private int second = 0;
	@Override
	public void run() {
		
		while(true) {
			try {
				//milli second
				sleep(1000);
				second++;
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
		}
	}
	
	public int getSecond() {
		return second;
	}


}