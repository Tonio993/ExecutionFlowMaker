package com.trainingground.efm.datastruct;

import java.util.concurrent.Semaphore;

public class SafeEntry<K, V> {
	
	private SafeMap<K, V> owner;
	private K key;
	private boolean released;

	SafeEntry(SafeMap<K, V> owner, K key) {
		this.owner = owner;
		this.key = key;
		this.released = false;
	}

	public V get() {
		if (released) {
			throw new IllegalStateException("Resource already released");
		}
		return owner.safeMap.get(key);
	}

	public void set(V value) {
		if (released) {
			throw new IllegalStateException("Resource already released");
		}
		owner.safeMap.put(key, value);
	}

	public void release() throws InterruptedException {
		owner.semaphore.acquire();
		Semaphore keySemaphore = owner.semaphoreMap.get(key);
		if (keySemaphore.availablePermits() != 0) {
			owner.semaphoreMap.remove(key);
		}
		owner.semaphoreMap.get(key).release();
		owner.semaphore.release();
		released = true;
	}
}