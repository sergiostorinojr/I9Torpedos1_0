/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.controller.repository;

import br.com.i9torpedos.model.domain.ReceivedSMSMessage;
import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.repository.BaseRepository;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Junior
 */
public class ControllerReceivedSMSMessage implements Serializable, ControllerSMS<ReceivedSMSMessage> {

    @Override
    public  ReceivedSMSMessage saveOrUpdate(ReceivedSMSMessage smsMessage) throws PersistenceException {
        return new BaseRepository<ReceivedSMSMessage>(ReceivedSMSMessage.class).saveOrUpdate(smsMessage);
    }

    @Override
    public void toRemove(ReceivedSMSMessage obj) {
    }

    @Override
    public Collection<ReceivedSMSMessage> findall() {
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ReceivedSMSMessage findById(Long id) {
        return null;
    }

}
