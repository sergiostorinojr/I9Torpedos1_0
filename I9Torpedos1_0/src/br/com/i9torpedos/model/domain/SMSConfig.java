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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Junior
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "SMSConfig_SEQ", sequenceName = "SMSConfig_SEQ", allocationSize = 1, initialValue = 1)
public abstract class SMSConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMSConfig_SEQ")
    private Long id;
    @Column(name = "gatewayID", length = 100)
    private String gatewayID;
    @Column(name = "messageId", length = 40)
    private String messageId;
    @Column(name = "messageUUID", length = 200)
    private String messageUUID;
    @Column(name = "encoding", length = 10)
    private String encoding;
    @Column(name = "dataGerada", length = 80)
    private String dataGerada;
    @Column(name = "smscRefNum")
    private String smscRefNum;
    @Column(name = "numDestinatario", length = 40)
    private String numDestinatario;
    @Column(name = "dataExapedicao", length = 80)
    private String dataExapedicao;
    @Column(name = "msgStatus", length = 40)
    private String msgStatus;
    @Column(name = "causaFalha", length = 4000)
    private String causaFalha;
    @Column(name = "periodoValido", length = 80)
    private String periodoValido;
    @Column(name = "relatorioStatus")
    private Boolean relatorioStatus;
    @Column(name = "portaDestino", length = 10)
    private String portaDestino;
    @Column(name = "flashSMS")
    private Boolean flashSMS;
    @Column(name = "mensagem", length = 2000)
    private String mensagem;
    @Column(name = "pduData", length = 2000)
    private String pduData;
    @Column(name = "entregaPrevista", length = 200)
    private String entregaPrevista;

    public SMSConfig() {
    }

    public SMSConfig(String gatewayID, String messageId, String messageUUID, String encoding, String dataGerada, String smscRefNum, String numDestinatario, String dataExapedicao, String msgStatus, String causaFalha, String periodoValido, Boolean relatorioStatus, String portaDestino, Boolean flashSMS, String mensagem, String pduData, String entregaPrevista) {
        this.gatewayID = gatewayID;
        this.messageId = messageId;
        this.messageUUID = messageUUID;
        this.encoding = encoding;
        this.dataGerada = dataGerada;
        this.smscRefNum = smscRefNum;
        this.numDestinatario = numDestinatario;
        this.dataExapedicao = dataExapedicao;
        this.msgStatus = msgStatus;
        this.causaFalha = causaFalha;
        this.periodoValido = periodoValido;
        this.relatorioStatus = relatorioStatus;
        this.portaDestino = portaDestino;
        this.flashSMS = flashSMS;
        this.mensagem = mensagem;
        this.pduData = pduData;
        this.entregaPrevista = entregaPrevista;
    }

    /**
     * @return the gatewayID
     */
    public String getGatewayID() {
        return gatewayID;
    }

    /**
     * @param gatewayID the gatewayID to set
     */
    public void setGatewayID(String gatewayID) {
        this.gatewayID = gatewayID;
    }

    /**
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * @return the messageUUID
     */
    public String getMessageUUID() {
        return messageUUID;
    }

    /**
     * @param messageUUID the messageUUID to set
     */
    public void setMessageUUID(String messageUUID) {
        this.messageUUID = messageUUID;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * @return the dataGerada
     */
    public String getDataGerada() {
        return dataGerada;
    }

    /**
     * @param dataGerada the dataGerada to set
     */
    public void setDataGerada(String dataGerada) {
        this.dataGerada = dataGerada;
    }

    /**
     * @return the smscRefNum
     */
    public String getSmscRefNum() {
        return smscRefNum;
    }

    /**
     * @param smscRefNum the smscRefNum to set
     */
    public void setSmscRefNum(String smscRefNum) {
        this.smscRefNum = smscRefNum;
    }

    /**
     * @return the numDestinatario
     */
    public String getNumDestinatario() {
        return numDestinatario;
    }

    /**
     * @param numDestinatario the numDestinatario to set
     */
    public void setNumDestinatario(String numDestinatario) {
        this.numDestinatario = numDestinatario;
    }

    /**
     * @return the dataExapedicao
     */
    public String getDataExapedicao() {
        return dataExapedicao;
    }

    /**
     * @param dataExapedicao the dataExapedicao to set
     */
    public void setDataExapedicao(String dataExapedicao) {
        this.dataExapedicao = dataExapedicao;
    }

    /**
     * @return the msgStatus
     */
    public String getMsgStatus() {
        return msgStatus;
    }

    /**
     * @param msgStatus the msgStatus to set
     */
    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }

    /**
     * @return the causaFalha
     */
    public String getCausaFalha() {
        return causaFalha;
    }

    /**
     * @param causaFalha the causaFalha to set
     */
    public void setCausaFalha(String causaFalha) {
        this.causaFalha = causaFalha;
    }

    /**
     * @return the periodoValido
     */
    public String getPeriodoValido() {
        return periodoValido;
    }

    /**
     * @param periodoValido the periodoValido to set
     */
    public void setPeriodoValido(String periodoValido) {
        this.periodoValido = periodoValido;
    }

    /**
     * @return the relatorioStatus
     */
    public Boolean getRelatorioStatus() {
        return relatorioStatus;
    }

    /**
     * @param relatorioStatus the relatorioStatus to set
     */
    public void setRelatorioStatus(Boolean relatorioStatus) {
        this.relatorioStatus = relatorioStatus;
    }

    /**
     * @return the portaDestino
     */
    public String getPortaDestino() {
        return portaDestino;
    }

    /**
     * @param portaDestino the portaDestino to set
     */
    public void setPortaDestino(String portaDestino) {
        this.portaDestino = portaDestino;
    }

    /**
     * @return the flashSMS
     */
    public Boolean getFlashSMS() {
        return flashSMS;
    }

    /**
     * @param flashSMS the flashSMS to set
     */
    public void setFlashSMS(Boolean flashSMS) {
        this.flashSMS = flashSMS;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the pduData
     */
    public String getPduData() {
        return pduData;
    }

    /**
     * @param pduData the pduData to set
     */
    public void setPduData(String pduData) {
        this.pduData = pduData;
    }

    /**
     * @return the entregaPrevista
     */
    public String getEntregaPrevista() {
        return entregaPrevista;
    }

    /**
     * @param entregaPrevista the entregaPrevista to set
     */
    public void setEntregaPrevista(String entregaPrevista) {
        this.entregaPrevista = entregaPrevista;
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
