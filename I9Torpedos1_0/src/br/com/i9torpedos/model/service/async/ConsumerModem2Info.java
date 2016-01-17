/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.async;

import br.com.i9torpedos.controller.repository.ControllerModem2Info;
import br.com.i9torpedos.controller.repository.ControllerModemRepository;
import br.com.i9torpedos.model.domain.SendModem2Info;
import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.OutboundMessage;

/**
 *
 * @author Junior
 */
public class ConsumerModem2Info implements Runnable {

    private final Bridge ponte;
    private final OutboundMessage msg;
    private final Random random;
    private final ControllerModemRepository<SendModem2Info> repo;
    private static final Integer TEMPO = 1000;

    public ConsumerModem2Info(Bridge ponte, OutboundMessage msg) {
        this.ponte = ponte;
        this.msg = msg;
        this.repo = new ControllerModem2Info();
        this.random = new Random();
    }

    @Override
    public void run() {

        try {
            while (true) {

                Thread.sleep(random.nextInt(TEMPO));
                
                repo.saveOrUpdate((SendSMSMessage) ponte.get(), msg);
                System.err.println("POnte Persistindo ");

            }
        } catch (InterruptedException | PersistenceException ex) {
            Logger.getLogger(ConsumerModem2Info.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
