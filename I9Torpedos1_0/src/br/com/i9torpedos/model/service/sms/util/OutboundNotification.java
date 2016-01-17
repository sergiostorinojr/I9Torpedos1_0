/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.sms.util;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

/**
 *
 * @author Junior
 */
public class OutboundNotification implements IOutboundMessageNotification
	{

    /**
     *
     * @param gateway
     * @param msg
     */
    @Override
		public void process(AGateway gateway, OutboundMessage msg)
		{
			System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
			System.out.println(msg);
                        System.err.println("Outbound handler called from Gateway: ");
                        
		}
	}
