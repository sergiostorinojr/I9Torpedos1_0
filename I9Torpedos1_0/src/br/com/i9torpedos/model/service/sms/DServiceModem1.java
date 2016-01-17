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
import br.com.i9torpedos.model.service.async.ConsumerModem1Info;
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
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import br.com.i9torpedos.model.service.async.Bridge;
import org.smslib.Message;

/**
 *
 * @author Junior
 */
public class DServiceModem1 implements Runnable, Serializable {

    private Queue<SendSMSMessage> filaSMS;
    private Integer TEMPO = null;
    private static Log log = LogFactory.getLog(DServiceModem1.class);
    private Random random;
    private ControllerGatewayRepository<GatewayConfig> repo;
    private Bridge<SendSMSMessage> ponte;
    private SerialModemGateway modem1;

    public DServiceModem1(Queue<SendSMSMessage> filaSMS, Integer tempo) {
        this.modem1 = new ControllerModemGateway().getModem1();
        this.filaSMS = new LinkedList<>();
        this.filaSMS.addAll(filaSMS);
        this.TEMPO = tempo;
        this.random = new Random();
        this.repo = new ControllerGatewayConfig();
        this.ponte = new BridgeModem();

        try {
            Service.getInstance().addGateway(modem1);
            Service.getInstance().startService();

            repo.saveOrUpdate(modem1, 1);

            log.info("Serviço moldem 1 Iniciado. DServiceModem1");

        } catch (SMSLibException ex) {
            log.fatal("Falha na API SMSLib - SMS DServiceModem1 " + ex.getMessage());

        } catch (IOException ex) {
            log.fatal("Falha na validação do GatewayMoldem1 - SMS DServiceModem1 " + ex.getMessage());

        } catch (InterruptedException ex) {
            log.fatal("Falha ao Inciiar ou Parar Serviço SMS DServiceModem1 " + ex.getMessage());

        } catch (PersistenceException ex) {
            Logger.getLogger(DServiceModem1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DServiceModem1() {
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
                new Thread(new ConsumerModem1Info(getPonte(), msg)).start();
                log.info("SMS para o numero: " + smsMessage.getNumero() + " Enviado com sucesso");

                SendSMSMessage poll = getFilaSMS().poll();
                boolean contains = getFilaSMS().contains(poll);
                if (contains) {

                    getFilaSMS().remove(poll);
                }

                

            }

        } catch (InterruptedException ex) {
            log.fatal("Falha ao Inciiar ou Parar Serviço SMS DServiceModem1 " + ex.getMessage());

        } catch (TimeoutException | GatewayException | IOException ex) {
            Logger.getLogger(DServiceModem1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the filaSMS
     */
    public Queue<SendSMSMessage> getFilaSMS() {
        return filaSMS;
    }

    /**
     * @param fila
     */
    public void setFilaSMS(Queue<SendSMSMessage> fila) {
        System.err.println("Vindo: " + fila.size());
        this.filaSMS.addAll(filaSMS);
        System.err.println("Depois: " + filaSMS.size());
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

    /**
     * @return the modem1
     */
    public SerialModemGateway getModem1() {
        return modem1;
    }

    /**
     * @param modem1 the modem1 to set
     */
    public void setModem1(SerialModemGateway modem1) {
        this.modem1 = modem1;
    }

}
