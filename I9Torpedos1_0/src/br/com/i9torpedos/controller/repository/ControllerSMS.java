/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.controller.repository;

import br.com.i9torpedos.model.service.exception.PersistenceException;
import java.util.Collection;

/**
 *
 * @author Junior
 * @param <T>
 */
public interface ControllerSMS<T> {

    public T saveOrUpdate(T obj) throws PersistenceException;

    public void toRemove(T obj);

    public Collection<T> findall();

    public T findById(Long id);

}
