/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.jpa;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Junior
 */
public class EntityManagerUtil implements Serializable {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    private static EntityManager em;
    private static final Log log = LogFactory.getLog(EntityManagerUtil.class);
    
    public static EntityManager getEntityManager() {
        
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
            log.info("EntityManager Criado com sucesso!!");
            
            return em;
            
        }
        return em;
        
    }
    
    public void closeEntityManager(EntityManager em) {
        
        if (em != null && em.isOpen()) {
            getEntityManager().close();
            emf.close();
            log.info("EntityManager fechado com sucesso!!");
        }
        
    }
    
}
