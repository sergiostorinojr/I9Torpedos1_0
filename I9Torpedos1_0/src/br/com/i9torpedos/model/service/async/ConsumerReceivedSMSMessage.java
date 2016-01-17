/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.async;

import br.com.i9torpedos.controller.repository.ControllerReceivedSMSMessage;
import br.com.i9torpedos.controller.repository.ControllerSMS;
import br.com.i9torpedos.model.domain.ReceivedSMSMessage;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.repository.BaseRepository;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class ConsumerReceivedSMSMessage implements Runnable {

    private final Bridge ponte;
    private final Random random;
    private static final Integer TEMPO = 1000;
    private final ControllerSMS<ReceivedSMSMessage> repo;

    public ConsumerReceivedSMSMessage(Bridge ponte) {
        this.ponte = ponte;
        this.random = new Random();
        this.repo = new ControllerReceivedSMSMessage();
    }

    @Override
    public void run() {

        try {
            while (true) {

                Thread.sleep(random.nextInt(TEMPO));

                new BaseRepository<ReceivedSMSMessage>(ReceivedSMSMessage.class).saveOrUpdate((ReceivedSMSMessage) ponte.get());
                //repo.saveOrUpdate((SendSMSMessage) ponte.get());
                System.err.println("POnte Persistindo ");

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ConsumerReceivedSMSMessage.class.getName()).log(Level.SEVERE, null, ex);

        } catch (PersistenceException ex) {
            Logger.getLogger(ConsumerReceivedSMSMessage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
