/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.repository;

import br.com.i9torpedos.model.domain.ReceivedModem2Info;
import br.com.i9torpedos.model.service.exception.PersistenceException;
import br.com.i9torpedos.model.service.jpa.EntityManagerUtil;
import java.util.Collection;
import javax.persistence.EntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Junior
 * @param <T>
 */
public class BaseRepository<T>  {

    private Class<?> clazz;
    private static final Log log = LogFactory.getLog(BaseRepository.class);

    public BaseRepository(Class<?> clazz) {
        this.clazz = clazz;
    }


    public  T saveOrUpdate(T obj) throws PersistenceException {
        EntityManager em = EntityManagerUtil.getEntityManager();
        em.getTransaction().begin();
        Boolean situacao = false;

        if (obj != null) {

            try {
               // if (obj != null) {
                    obj = em.merge(obj);

                    log.info("Dados Atualizado com Sucesso. " + obj.getClass().getName());
                    situacao = true;

                /*} else {
                    em.persist(obj);

                    log.info("Dados Gravado com Sucesso. " + obj.getClass().getName());
                    situacao = true;
                }*/

            } catch (Exception e) {
                situacao = false;
                log.fatal("Falha em Atualizar ou Gravar dados " + e.getMessage());

                //Devida a falha de Persistencia ira Realizar um Rolback
                em.getTransaction().rollback();
                throw new PersistenceException("Falha ao Persistir Dados", e);

            } finally {

                //se for verdadeiro então realiza o commit e fecha meu entitymanager
                if (situacao) {
                    em.getTransaction().commit();
                    //em.flush();
                    // em.clear();
                }
                em.close();
                log.info("EntityManager fechado com Sucesso. Metodo saveOrUpdate");
            }
        }

        return obj;

    }

    public void toRemove(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<T> findall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public T findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the clazz
     */
    private Class<?> getClazz() {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    private void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

}
