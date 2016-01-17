/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.async;

public class BridgeSendSMSMessage<T> implements Bridge<T> {

    private T t;
    private Boolean disponivel = false;

    @Override
    public synchronized void set(T obj) throws InterruptedException {
        while (disponivel) {
            System.err.println("Esperando.... set ponte");
            wait();
        }
        this.t = obj;
        disponivel = true;
        notifyAll();
    }

    @Override
    public synchronized T get() throws InterruptedException {
        while (!disponivel) {
            System.err.println("Esperando.... get ponte");
            wait();
        }
        disponivel = false;
        notifyAll();
        return t;
    }

}
