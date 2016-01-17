/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.sms;

import br.com.i9torpedos.controller.repository.ControllerGatewayConfig;
import br.com.i9torpedos.controller.repository.ControllerGatewayRepository;
import br.com.i9torpedos.controller.sms.ControllerModemGateway;
import br.com.i9torpedos.model.domain.GatewayConfig;
import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.async.ConsumerModem2Info;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;
import br.com.i9torpedos.model.service.async.BridgeModem;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.i9torpedos.model.service.async.Bridge;
import org.smslib.Message;

/**
 *
 * @author Junior
 */
public class DServiceModem2 implements Runnable, Serializable {

    private Queue<SendSMSMessage> filaSMS = new LinkedList<>();
    private Integer TEMPO = null;
    private static Log log = LogFactory.getLog(DServiceModem2.class);
    private Random random;
    private ControllerGatewayRepository<GatewayConfig> repo;
    private Bridge<SendSMSMessage> ponte;
    private SerialModemGateway modem2;

    public DServiceModem2(Queue<SendSMSMessage> filaSMS, Integer tempo) {
        this.modem2 = new ControllerModemGateway().getModem2();
        this.filaSMS = new LinkedList<>();
        this.filaSMS.addAll(filaSMS);
        this.TEMPO = tempo;
        this.random = new Random();
        this.repo = new ControllerGatewayConfig();
        this.ponte = new BridgeModem();

        try {
            Service.getInstance().addGateway(modem2);
            Service.getInstance().startService();

            repo.saveOrUpdate(modem2, 2);

            log.info("Serviço moldem 2 Iniciado. DServiceModem2");

        } catch (SMSLibException ex) {
            log.fatal("Falha na API SMSLib - SMS DServiceModem2 " + ex.getMessage());

        } catch (IOException ex) {
            log.fatal("Falha na validação do GatewayMoldem2 - SMS DServiceModem2 " + ex.getMessage());

        } catch (InterruptedException ex) {
            log.fatal("Falha ao Inciiar ou Parar Serviço SMS DServiceModem2 " + ex.getMessage());

        } catch (PersistenceException ex) {
            Logger.getLogger(DServiceModem2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DServiceModem2() {
        this.repo = new ControllerGatewayConfig();
        this.ponte = new BridgeModem();
    }

    @Override
    public void run() {

        try {
            while (!filaSMS.isEmpty()) {

                SendSMSMessage smsMessage = getFilaSMS().peek();

                OutboundMessage msg = new OutboundMessage(smsMessage.getNumero(), smsMessage.getMensagem());
                msg.setEncoding(Message.MessageEncodings.ENCUCS2);
                
                Thread.sleep(TEMPO);
                Service.getInstance().sendMessage(msg);
                log.warn(msg);

                getPonte().set(smsMessage);
                new Thread(new ConsumerModem2Info(getPonte(), msg)).start();
                log.info("SMS para o numero: " + smsMessage.getNumero() + " Enviado com sucesso");

                SendSMSMessage poll = getFilaSMS().poll();
                boolean contains = getFilaSMS().contains(poll);
                if (contains) {
                    getFilaSMS().remove(poll);
                }

                

            }
        } catch (SMSLibException ex) {
            log.fatal("Falha na API SMSLib - SMS DServiceModem2 " + ex.getMessage());

        } catch (IOException ex) {
            log.fatal("Falha na validação do GatewayMoldem1 - SMS DServiceModem2 " + ex.getMessage());

        } catch (InterruptedException ex) {
            log.fatal("Falha ao Inciiar ou Parar Serviço SMS DServiceModem2 " + ex.getMessage());

        }

    }

    /**
     * @return the filaSMS
     */
    public Queue<SendSMSMessage> getFilaSMS() {
        return filaSMS;
    }

    /**
     * @param filaSMS the filaSMS to set
     */
    public void setFilaSMS(Queue<SendSMSMessage> filaSMS) {
        this.filaSMS.addAll(filaSMS);
    }

    /**
     * @return the TEMPO
     */
    public Integer getTEMPO() {
        return TEMPO;
    }

    /**
     * @param TEMPO the TEMPO to set
     */
    public void setTEMPO(Integer TEMPO) {
        this.TEMPO = TEMPO;
    }

    /**
     * @return the random
     */
    public Random getRandom() {
        return random;
    }

    /**
     * @param random the random to set
     */
    public void setRandom(Random random) {
        this.random = random;
    }

    /**
     * @return the repo
     */
    public ControllerGatewayRepository<GatewayConfig> getRepo() {
        return repo;
    }

    /**
     * @param repo the repo to set
     */
    public void setRepo(ControllerGatewayRepository<GatewayConfig> repo) {
        this.repo = repo;
    }

    /**
     * @return the ponte
     */
    public Bridge<SendSMSMessage> getPonte() {
        return ponte;
    }

    /**
     * @param ponte the ponte to set
     */
    public void setPonte(Bridge<SendSMSMessage> ponte) {
        this.ponte = ponte;
    }

}
