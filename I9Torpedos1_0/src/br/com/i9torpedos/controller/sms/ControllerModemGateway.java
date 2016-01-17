/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.controller.sms;

import br.com.i9torpedos.model.service.sms.Modem1Producer;
import br.com.i9torpedos.model.service.sms.Modem2Producer;
import java.io.Serializable;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 */
public class ControllerModemGateway implements Serializable {

    public SerialModemGateway getModem1() {
        return new Modem1Producer().getGatewayConfig();
    }

    public SerialModemGateway getModem2() {
        return new Modem2Producer().getGatewayConfig();
    }

}
