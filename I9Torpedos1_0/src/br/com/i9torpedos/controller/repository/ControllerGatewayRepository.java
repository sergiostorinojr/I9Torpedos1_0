/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.controller.repository;

import br.com.i9torpedos.model.service.exception.PersistenceException;
import java.util.Collection;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 * @param <T>
 */
public interface ControllerGatewayRepository<T> {

    public T saveOrUpdate(SerialModemGateway gateway, Integer modem) throws PersistenceException;

    public void toRemove(T obj);

    public Collection<T> findall();

    public T findById(Long id);

}
