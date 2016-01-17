/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "SendModem2Info")
public class SendModem2Info extends SMSConfig implements Serializable {

    public SendModem2Info() {
    }

}
