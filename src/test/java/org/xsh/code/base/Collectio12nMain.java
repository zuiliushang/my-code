package org.xsh.code.base;

import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;


public class Collectio12nMain {
	
	@Test
	public void testArrayDeque() {
		Deque<Integer> deque = new ArrayDeque<>();
		deque.add(1);
		deque.add(2);
		System.out.println(deque.peek());
		System.out.println(deque.pop());
		
		deque = new ArrayDeque<>();
		deque.add(1);
		deque.add(2);
		System.out.println(deque.poll());
		System.out.println(deque.pop());
	}
	
	@Test
	public void testBlock() {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
		new Thread(()->{
			while(true) {
				//System.out.println(queue.offer( (int) (System.currentTimeMillis()&0xffff)));
				try {
					queue.put( (int) (System.currentTimeMillis()&0xffff));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("invoke");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(()->{
			while(true) {
				int i=0;
				if (i < 15) {
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				i++;
				System.out.println(queue.poll());
			}
		}).start();
		while(true) {}
	}
	
/*	@Test
	public void testRemoveBothE(List src,List oth) {
		//src大集合
		//oth小集合
		LinkedList result = new LinkedList<>(src);//大集合用linkedList
		HashSet oths = new HashSet<>(oth);//
		Iterator iterator = result.iterator();
		while(iterator.hasNext()) {
			if(oths.contains(iterator.next()))
				iterator.remove();
		}
		
	}
	*/
	@Test
	public void testzxcIdensMap() {
		IdentityHashMap<Integer, String> map = new IdentityHashMap<>();
		Integer a = Integer.MAX_VALUE-1;//1
		Integer b = Integer.MAX_VALUE-1;//1
		map.put(a, "100");
		map.put(b, "100");
		System.out.println(map.size());
		System.out.println(a==b);
	}
	
	public static void main(String[] args) {
		System.out.println(11);
	}
}
