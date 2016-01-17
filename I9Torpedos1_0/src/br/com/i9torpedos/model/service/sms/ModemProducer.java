/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.sms;

import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 */
public interface ModemProducer {
    
    public SerialModemGateway getGatewayConfig();
    
}
