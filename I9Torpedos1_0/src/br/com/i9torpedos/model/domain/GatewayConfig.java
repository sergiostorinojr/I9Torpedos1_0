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
@Table(name = "gatewayConfig")
@SequenceGenerator(name = "GatewayConfig_SEQ", sequenceName = "GatewayConfig_SEQ", allocationSize = 1, initialValue = 1)
public class GatewayConfig  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GatewayConfig_SEQ")
    private Long id;
    @Column(name = "fabricante", length = 80)
    private String fabricante;
    @Column(name = "modelo", length = 80)
    private String modelo;
    @Column(name = "serialNumero", length = 100)
    private String serialNumero;
    @Column(name = "imsi", length = 80)
    private String imsi;
    @Column(name = "nivelSinal", length = 10)
    private String nivelSinal;
    @Column(name = "nivelBateria", length = 10)
    private String nivelBateria;
    @Column(name = "pin1", length = 20)
    private String pin1;
    @Column(name = "pin2", length = 20)
    private String pin2;
    @Column(name = "status", length = 20)
    private String status;
    @Column(name = "swVersion", length = 100)
    private String swVersion;
    @Column(name = "idConfigModem", length = 80)
    private String idConfigMoldem;
    @Column(name = "portaModem", length = 10)
    private String portaMoldem;
    @Column(name = "protocolo", length = 100)
    private String protocolo;

    public GatewayConfig() {
    }

    public GatewayConfig(String fabricante, String modelo, String serialNumero, String imsi, String nivelSinal, String nivelBateria, String pin1, String pin2, String status, String swVersion, String idConfigMoldem, String portaMoldem, String protocolo) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.serialNumero = serialNumero;
        this.imsi = imsi;
        this.nivelSinal = nivelSinal;
        this.nivelBateria = nivelBateria;
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.status = status;
        this.swVersion = swVersion;
        this.idConfigMoldem = idConfigMoldem;
        this.portaMoldem = portaMoldem;
        this.protocolo = protocolo;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the serialNumero
     */
    public String getSerialNumero() {
        return serialNumero;
    }

    /**
     * @param serialNumero the serialNumero to set
     */
    public void setSerialNumero(String serialNumero) {
        this.serialNumero = serialNumero;
    }

    /**
     * @return the imsi
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * @param imsi the imsi to set
     */
    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    /**
     * @return the nivelSinal
     */
    public String getNivelSinal() {
        return nivelSinal;
    }

    /**
     * @param nivelSinal the nivelSinal to set
     */
    public void setNivelSinal(String nivelSinal) {
        this.nivelSinal = nivelSinal;
    }

    /**
     * @return the nivelBateria
     */
    public String getNivelBateria() {
        return nivelBateria;
    }

    /**
     * @param nivelBateria the nivelBateria to set
     */
    public void setNivelBateria(String nivelBateria) {
        this.nivelBateria = nivelBateria;
    }

    /**
     * @return the pin1
     */
    public String getPin1() {
        return pin1;
    }

    /**
     * @param pin1 the pin1 to set
     */
    public void setPin1(String pin1) {
        this.pin1 = pin1;
    }

    /**
     * @return the pin2
     */
    public String getPin2() {
        return pin2;
    }

    /**
     * @param pin2 the pin2 to set
     */
    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the swVersion
     */
    public String getSwVersion() {
        return swVersion;
    }

    /**
     * @param swVersion the swVersion to set
     */
    public void setSwVersion(String swVersion) {
        this.swVersion = swVersion;
    }

    /**
     * @return the idConfigMoldem
     */
    public String getIdConfigMoldem() {
        return idConfigMoldem;
    }

    /**
     * @param idConfigMoldem the idConfigMoldem to set
     */
    public void setIdConfigMoldem(String idConfigMoldem) {
        this.idConfigMoldem = idConfigMoldem;
    }

    /**
     * @return the portaMoldem
     */
    public String getPortaMoldem() {
        return portaMoldem;
    }

    /**
     * @param portaMoldem the portaMoldem to set
     */
    public void setPortaMoldem(String portaMoldem) {
        this.portaMoldem = portaMoldem;
    }

    /**
     * @return the protocolo
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
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
