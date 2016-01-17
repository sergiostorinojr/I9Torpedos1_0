/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.controller.repository;

import br.com.i9torpedos.model.domain.SendModem1Info;
import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.repository.BaseRepository;
import java.io.Serializable;
import java.util.Collection;
import org.smslib.OutboundMessage;

/**
 *
 * @author Junior
 */
public class ControllerModem1Info implements Serializable, ControllerModemRepository<SendModem1Info> {

    
    /**
     *
     * @param smsMessage
     * @param msg
     * @return
     * @throws PersistenceException
     */
    @Override
    public SendModem1Info saveOrUpdate(SendSMSMessage smsMessage, OutboundMessage msg) throws PersistenceException {
        SendModem1Info config = new SendModem1Info();

        config.setGatewayID(msg.getGatewayId());

        config.setMessageId(String.valueOf(msg.getMessageId()));
        config.setMessageUUID(msg.getUuid());
        config.setEncoding(msg.getEncoding().toString());
        config.setDataGerada(msg.getDate().toString());
        config.setSmscRefNum(msg.getRefNo());
        config.setNumDestinatario(smsMessage.getNumero());
        if (msg.getDispatchDate() == null) {

            config.setDataExapedicao("Não há data prevista para expedição");

        } else {
            config.setDataExapedicao(msg.getDispatchDate().toString());

        }
        config.setMsgStatus(msg.getMessageStatus().toString());
        config.setCausaFalha(String.valueOf(msg.getFailureCause()));
        config.setPeriodoValido(String.valueOf(msg.getValidityPeriod()));
        config.setRelatorioStatus(msg.getStatusReport());
        config.setPortaDestino(String.valueOf(msg.getSrcPort()));
        config.setFlashSMS(msg.getFlashSms());
        config.setMensagem(smsMessage.getMensagem());
        config.setPduData(msg.getPduUserData());

        if (msg.getScheduledDeliveryDate() == null) {

            config.setEntregaPrevista("SEM DATA PREVISTA");
        } else {
            config.setEntregaPrevista(msg.getScheduledDeliveryDate().toString());

        }
        return new BaseRepository<SendModem1Info>(SendModem1Info.class).saveOrUpdate(config);
    }

    @Override
    public void toRemove(SendModem1Info obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<SendModem1Info> findall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SendModem1Info findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
