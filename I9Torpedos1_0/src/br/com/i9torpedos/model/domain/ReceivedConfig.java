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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SequenceGenerator(name = "ReceivedConfig_SEQ", sequenceName = "ReceivedConfig_SEQ", allocationSize = 1, initialValue = 1)
public abstract class ReceivedConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ReceivedConfig_SEQ")
    private Long id;
    @Column(name = "gatewayID", length = 100)
    private String gwId;
    @Column(name = "msgUUID", length = 100)
    private String UUID;
    @Column(name = "encoding", length = 20)
    private String encoding;
    @Column(name = "dispachoSMSc", length = 40)
    private String dispachadoViaSMSC;
    @Column(name = "message", length = 2000)
    private String message;
    @Column(name = "pduData", length = 2000)
    private String pduData;
    @Column(name = "originator", length = 100)
    private String originador;
    @Column(name = "indiceMemoria")
    private Integer indiceMemoria;
    @Column(name = "mPartMemIndex", length = 20)
    private String mPartMemIndex;
    @Column(name = "memLocation", length = 10)
    private String memLocation;
    @Column(name = "sourceDestiPort")
    private Integer sourceDestiPort;

    public ReceivedConfig() {
    }

    public ReceivedConfig(Long id, String gwId, String UUID, String encoding, String dispachadoViaSMSC, String message, String pduData, String originador, Integer indiceMemoria, String mPartMemIndex, String memLocation, Integer sourceDestiPort) {
        this.id = id;
        this.gwId = gwId;
        this.UUID = UUID;
        this.encoding = encoding;
        this.dispachadoViaSMSC = dispachadoViaSMSC;
        this.message = message;
        this.pduData = pduData;
        this.originador = originador;
        this.indiceMemoria = indiceMemoria;
        this.mPartMemIndex = mPartMemIndex;
        this.memLocation = memLocation;
        this.sourceDestiPort = sourceDestiPort;
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

    /**
     * @return the gwId
     */
    public String getGwId() {
        return gwId;
    }

    /**
     * @param gwId the gwId to set
     */
    public void setGwId(String gwId) {
        this.gwId = gwId;
    }

    /**
     * @return the UUID
     */
    public String getUUID() {
        return UUID;
    }

    /**
     * @param UUID the UUID to set
     */
    public void setUUID(String UUID) {
        this.UUID = UUID;
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
     * @return the dispachadoViaSMSC
     */
    public String getDispachadoViaSMSC() {
        return dispachadoViaSMSC;
    }

    /**
     * @param dispachadoViaSMSC the dispachadoViaSMSC to set
     */
    public void setDispachadoViaSMSC(String dispachadoViaSMSC) {
        this.dispachadoViaSMSC = dispachadoViaSMSC;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
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
     * @return the originador
     */
    public String getOriginador() {
        return originador;
    }

    /**
     * @param originador the originador to set
     */
    public void setOriginador(String originador) {
        this.originador = originador;
    }

    /**
     * @return the indiceMemoria
     */
    public Integer getIndiceMemoria() {
        return indiceMemoria;
    }

    /**
     * @param indiceMemoria the indiceMemoria to set
     */
    public void setIndiceMemoria(Integer indiceMemoria) {
        this.indiceMemoria = indiceMemoria;
    }

    /**
     * @return the mPartMemIndex
     */
    public String getmPartMemIndex() {
        return mPartMemIndex;
    }

    /**
     * @param mPartMemIndex the mPartMemIndex to set
     */
    public void setmPartMemIndex(String mPartMemIndex) {
        this.mPartMemIndex = mPartMemIndex;
    }

    /**
     * @return the memLocation
     */
    public String getMemLocation() {
        return memLocation;
    }

    /**
     * @param memLocation the memLocation to set
     */
    public void setMemLocation(String memLocation) {
        this.memLocation = memLocation;
    }

    /**
     * @return the sourceDestiPort
     */
    public Integer getSourceDestiPort() {
        return sourceDestiPort;
    }

    /**
     * @param sourceDestiPort the sourceDestiPort to set
     */
    public void setSourceDestiPort(Integer sourceDestiPort) {
        this.sourceDestiPort = sourceDestiPort;
    }

}
