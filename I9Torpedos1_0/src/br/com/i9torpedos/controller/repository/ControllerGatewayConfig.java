/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.controller.repository;

import br.com.i9torpedos.model.domain.GatewayConfig;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.repository.BaseRepository;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.GatewayException;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 */
public class ControllerGatewayConfig implements Serializable, ControllerGatewayRepository<GatewayConfig> {

    @Override
    public GatewayConfig saveOrUpdate(SerialModemGateway gateway, Integer modem) throws PersistenceException {
        
        GatewayConfig gwd = new GatewayConfig();
        Properties props = new Properties();
        try {
            gwd.setFabricante(gateway.getManufacturer());
            gwd.setModelo(gateway.getModel());
            gwd.setSerialNumero(gateway.getSerialNo());
            gwd.setSwVersion(gateway.getSwVersion());
            gwd.setNivelBateria(String.valueOf(gateway.getBatteryLevel()) + " %");
            gwd.setNivelSinal(String.valueOf(gateway.getSignalLevel()) + " dBm");
            gwd.setPin1(gateway.getSimPin());
            gwd.setPin2(gateway.getSimPin2());
            gwd.setImsi(gateway.getImsi());
            gwd.setIdConfigMoldem(gateway.getGatewayId());

            if (modem == 1) {
                props.load(getClass().getResourceAsStream("/modem1.properties"));
                gwd.setPortaMoldem(props.getProperty("sms.service.port"));

            } else if (modem == 2) {
                props.load(getClass().getResourceAsStream("/modem2.properties"));
                gwd.setPortaMoldem(props.getProperty("sms.service.port"));

            }

            String status = String.valueOf(gateway.getStatus());

            if (status.equalsIgnoreCase("true")) {
                gwd.setStatus("ONLINE");

            } else if (status.equalsIgnoreCase("false")) {
                gwd.setStatus("OFFILINE");

            }
            gwd.setProtocolo(String.valueOf(gateway.getProtocol()));

        } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
            Logger.getLogger(ControllerGatewayConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new BaseRepository<GatewayConfig>(GatewayConfig.class).saveOrUpdate(gwd);

    }

    @Override
    public void toRemove(GatewayConfig obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<GatewayConfig> findall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GatewayConfig findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
