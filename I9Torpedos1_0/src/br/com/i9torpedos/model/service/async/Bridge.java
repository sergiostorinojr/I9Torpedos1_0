/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.async;

/**
 *
 * @author Junior
 * @param <T>
 */
public interface Bridge<T> {

    public void set(T obj) throws InterruptedException;

    public T get() throws InterruptedException;

}
