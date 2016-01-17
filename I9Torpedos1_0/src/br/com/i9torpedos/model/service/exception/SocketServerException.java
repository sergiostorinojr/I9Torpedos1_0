/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.exception;

/**
 *
 * @author Junior
 */
public class SocketServerException extends Exception {

    public SocketServerException(String msg) {
        super(msg);
    }

    public SocketServerException(String msg, Exception e) {
        super(msg, e);
    }

}
