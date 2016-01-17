/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.sms.util;

import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.sms.DServiceModem1;
import br.com.i9torpedos.model.service.sms.DServiceModem2;
import java.util.LinkedList;
import java.util.Queue;
import org.smslib.AGateway;
import org.smslib.ICallNotification;

/**
 *
 * @author Junior
 */
public class CallNotification implements ICallNotification {

    private final Queue<SendSMSMessage> sms = new LinkedList<>();
    private final SendSMSMessage smsMessage = new SendSMSMessage();
    private static final String MENSAGEM = "Desculpe por não Atender numero apenas para SMSMarketing";

    /**
     *
     * @param gateway
     * @param callerId
     */
    @Override
    public void process(AGateway gateway, String callerId) {
        if (gateway.getGatewayId().equalsIgnoreCase("moldem.com4")) {

            System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);

            /*smsMessage.setNumero("+5519" + callerId);
            smsMessage.setMensagem(MENSAGEM);
            sms.add(smsMessage);
            DServiceModem1 md1 = new DServiceModem1(sms, 1000);
            md1.run();
            sms.clear();*/
            
            sendSMSCallNotification(callerId, 1);
            
        } else if (gateway.getGatewayId().equalsIgnoreCase("moldem.com11")) {

            System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);

            sendSMSCallNotification(callerId, 2);
        }

    }

    public void sendSMSCallNotification(String callerId, Integer modem) {
        if (modem == 1) {
            smsMessage.setNumero(callerId);
            smsMessage.setMensagem(MENSAGEM);
            sms.add(smsMessage);
            DServiceModem1 md1 = new DServiceModem1(sms, 1000);
            md1.run();
            sms.clear();
        } else if (modem == 2) {
            smsMessage.setNumero(callerId);
            smsMessage.setMensagem(MENSAGEM);
            sms.add(smsMessage);
            DServiceModem2 md2 = new DServiceModem2(sms, 1000);
            //md2.getFilaSMS().add(smsMessage);
            md2.run();
            sms.clear();
        }
    }
}
