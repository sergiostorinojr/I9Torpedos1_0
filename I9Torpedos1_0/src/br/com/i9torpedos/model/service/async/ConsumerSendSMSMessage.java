/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.async;

import br.com.i9torpedos.controller.repository.ControllerSMS;
import br.com.i9torpedos.controller.repository.ControllerSendSMSMessage;
import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.repository.BaseRepository;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class ConsumerSendSMSMessage implements Runnable {

    private final Bridge ponte;
    private final Random random;
    private static final Integer TEMPO = 1000;
    private final ControllerSMS<SendSMSMessage> repo;

    public ConsumerSendSMSMessage(Bridge ponte) {
        this.ponte = ponte;
        this.random = new Random();
        this.repo = new ControllerSendSMSMessage();
    }

    @Override
    public void run() {

        try {
            while (true) {

               // Thread.sleep(random.nextInt(TEMPO));

                //new BaseRepository<SendSMSMessage>(SendSMSMessage.class).saveOrUpdate((SendSMSMessage) ponte.get());
                repo.saveOrUpdate((SendSMSMessage) ponte.get());
                System.err.println("POnte Persistindo ");

            }
        } catch (InterruptedException | PersistenceException ex) {
            Logger.getLogger(ConsumerSendSMSMessage.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
