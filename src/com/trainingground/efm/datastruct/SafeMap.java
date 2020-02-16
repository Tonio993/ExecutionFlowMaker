package com.trainingground.efm.datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class SafeMap<K, V> {

	HashMap<K, V> safeMap;
	HashMap<K, Semaphore> semaphoreMap;
	Semaphore semaphore;

	public SafeMap() {
		this((Map<K, V>) null);
	}

	public SafeMap(SafeMap<K, V> safeMap) {
		this(safeMap.safeMap);
	}

	public SafeMap(Map<K, V> map) {
		safeMap = map != null ? new HashMap<>(map) : new HashMap<>();
		semaphoreMap = new HashMap<>();
		semaphore = new Semaphore(1);
	}

	public SafeEntry<K, V> acquire(K key) throws InterruptedException {
		semaphore.acquire();
		if (!semaphoreMap.containsKey(key)) {
			semaphoreMap.put(key, new Semaphore(1));
		}
		semaphore.release();
		semaphoreMap.get(key).acquire();
		if (!safeMap.containsKey(key)) {
			safeMap.put(key, null);
		}
		return new SafeEntry<K, V>(this, key);
	}

}
