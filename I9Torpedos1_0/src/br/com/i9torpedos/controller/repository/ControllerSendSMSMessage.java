/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.controller.repository;

import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.repository.BaseRepository;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Junior
 */
public class ControllerSendSMSMessage implements Serializable, ControllerSMS<SendSMSMessage> {

    @Override
    public  SendSMSMessage saveOrUpdate(SendSMSMessage smsMessage) throws PersistenceException {
        return new BaseRepository<SendSMSMessage>(SendSMSMessage.class).saveOrUpdate(smsMessage);
    }

    @Override
    public void toRemove(SendSMSMessage obj) {
    }

    @Override
    public Collection<SendSMSMessage> findall() {
        return null;
    }

    @Override
    public SendSMSMessage findById(Long id) {
        return null;
    }

}
