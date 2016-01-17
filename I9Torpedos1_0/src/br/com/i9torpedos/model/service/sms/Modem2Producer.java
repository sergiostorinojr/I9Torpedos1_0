/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.sms;

import br.com.i9torpedos.model.service.sms.util.CallNotification;
import br.com.i9torpedos.model.service.sms.util.GatewayStatusNotification;
import br.com.i9torpedos.model.service.sms.util.InboundNotification;
import br.com.i9torpedos.model.service.sms.util.OrphanedMessageNotification;
import br.com.i9torpedos.model.service.sms.util.OutboundNotification;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smslib.AGateway;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 */
public class Modem2Producer implements Serializable, ModemProducer {
    
private static Log log = LogFactory.getLog(Modem2Producer.class);
private static final String PIN1 = "1010";
private static final String PIN2 = "2073";

        @Override
	public SerialModemGateway getGatewayConfig() {
		SerialModemGateway gateway = null;

		if(gateway == null){
			try {
					Properties props = new Properties();
					props.load(getClass().getResourceAsStream("/modem2.properties"));
                                        gateway = new SerialModemGateway(props.getProperty("sms.service.idMoldem"),
												   	props.getProperty("sms.service.port"), 
													Integer.parseInt(props.getProperty("sms.service.taxaTransmissao")), 
													props.getProperty("sms.service.fabricante"),  
													props.getProperty("sms.service.modelo"));
					gateway.setInbound(Boolean.parseBoolean(props.getProperty("sms.service.inbound")));
					gateway.setOutbound(Boolean.parseBoolean(props.getProperty("sms.service.outbound")));
					gateway.setSimPin(PIN1);
                                        gateway.setSimPin2(PIN2);
                                        gateway.setProtocol(AGateway.Protocols.PDU);
                                        
                                        InboundNotification inboundNotification = new InboundNotification();
                // Create the notification callback method for inbound voice calls.
                CallNotification callNotification = new CallNotification();
                //Create the notification callback method for gateway statuses.
                GatewayStatusNotification statusNotification = new GatewayStatusNotification();
                OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
                OutboundNotification outboundNotification = new OutboundNotification();

                Service.getInstance().setOutboundMessageNotification(outboundNotification);
                Service.getInstance().setInboundMessageNotification(inboundNotification);
                Service.getInstance().setCallNotification(callNotification);
                Service.getInstance().setGatewayStatusNotification(statusNotification);
                Service.getInstance().setOrphanedMessageNotification(orphanedMessageNotification);
                                        
					return gateway;
				
			} catch (IOException e) {
					
					log.fatal("Falha ao Carregar Arquivo .properties SMSMoldem1Producer " + e.getMessage());
					
                                        return null;
					
			}


		}
		return gateway;

	}

}

