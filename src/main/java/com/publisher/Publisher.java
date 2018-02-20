package com.publisher;


public interface Publisher<T, B, K, V>
{
	void publish(T t,B b, K k, V v);
}
