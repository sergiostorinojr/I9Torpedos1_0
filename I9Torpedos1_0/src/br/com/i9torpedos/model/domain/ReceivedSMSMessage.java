/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "ReceivedSMSMessage")
@SequenceGenerator(name = "ReceivedSMSMessage_SEQ", sequenceName = "ReceivedSMSMessage_SEQ", allocationSize = 1, initialValue = 1)
public class ReceivedSMSMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ReceivedSMSMessage_SEQ")
    private Long id;
    @Column(name = "numero", length = 40)
    private String numero;
    @Column(name = "mensagem", length = 2000)
    private String mensagem;

    public ReceivedSMSMessage() {
    }

    public ReceivedSMSMessage(String numero, String mensagem) {
        this.numero = numero;
        this.mensagem = mensagem;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
