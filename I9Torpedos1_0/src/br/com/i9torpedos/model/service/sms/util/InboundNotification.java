/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.sms.util;

import br.com.i9torpedos.model.domain.ReceivedModem1Info;
import br.com.i9torpedos.model.domain.ReceivedModem2Info;
import br.com.i9torpedos.model.domain.ReceivedSMSMessage;
import br.com.i9torpedos.model.service.async.Bridge;
import br.com.i9torpedos.model.service.async.BridgeReceivedSMSMessage;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.repository.BaseRepository;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message;
import org.smslib.TimeoutException;

/**
 *
 * @author Junior
 */
public class InboundNotification implements IInboundMessageNotification {

    private Bridge<ReceivedSMSMessage> ponte;

    public InboundNotification() {
        this.ponte = new BridgeReceivedSMSMessage<>();
    }

    /**
     *
     * @param gateway
     * @param msgType
     * @param msg
     */
    @Override
    public void process(AGateway gateway, Message.MessageTypes msgType, InboundMessage msg) {
        if (msgType == Message.MessageTypes.INBOUND) {
            if (gateway.getGatewayId().equalsIgnoreCase("moldem.com4")) {
                System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
                //System.out.println(msg);
                persistenceInboundNotificationMD1(msg);
                try {
                    gateway.deleteMessage(msg);
                } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                    Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (gateway.getGatewayId().equalsIgnoreCase("moldem.com11")) {
                System.err.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
                //System.out.println(msg);

                persistenceInboundNotificationMD2(msg);
                try {
                    gateway.deleteMessage(msg);
                } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                    Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (msgType == Message.MessageTypes.STATUSREPORT) {

            if (gateway.getGatewayId().equalsIgnoreCase("moldem.com4")) {
                System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
                System.out.println(msg);
            } else if (gateway.getGatewayId().equalsIgnoreCase("moldem.com11")) {
                System.err.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());

            }

        }

    }

    public void persistenceInboundNotificationMD2(InboundMessage msg) {

        if (msg != null) {
            ReceivedModem2Info config = new ReceivedModem2Info();

            config.setGwId(msg.getGatewayId());

            config.setUUID(msg.getUuid());
            config.setEncoding(msg.getEncoding().toString());
            config.setDispachadoViaSMSC(msg.getSmscNumber());
            config.setMessage(msg.getText());
            config.setPduData(msg.getPduUserData());
            config.setPduData(msg.getPduUserData());
            config.setOriginador(msg.getOriginator());
            config.setIndiceMemoria(msg.getMemIndex());
            config.setmPartMemIndex(msg.getMpMemIndex());
            config.setMemLocation(msg.getMemLocation());
            config.setSourceDestiPort(msg.getSrcPort());

            try {
                new BaseRepository<ReceivedModem2Info>(ReceivedModem2Info.class).saveOrUpdate(config);
            } catch (PersistenceException ex) {
                Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void persistenceInboundNotificationMD1(InboundMessage msg) {
        if (msg != null) {
            ReceivedModem1Info config = new ReceivedModem1Info();

            config.setGwId(msg.getGatewayId());

            config.setUUID(msg.getUuid());
            config.setEncoding(msg.getEncoding().toString());
            config.setDispachadoViaSMSC(msg.getSmscNumber());
            config.setMessage(msg.getText());
            config.setPduData(msg.getPduUserData());
            config.setPduData(msg.getPduUserData());
            config.setOriginador(msg.getOriginator());
            config.setIndiceMemoria(msg.getMemIndex());
            config.setmPartMemIndex(msg.getMpMemIndex());
            config.setMemLocation(msg.getMemLocation());
            config.setSourceDestiPort(msg.getSrcPort());

            try {
                new BaseRepository<ReceivedModem1Info>(ReceivedModem1Info.class).saveOrUpdate(config);
            } catch (PersistenceException ex) {
                Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
