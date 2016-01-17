/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.serverSocket;

import br.com.i9torpedos.model.domain.SendSMSMessage;
import br.com.i9torpedos.model.service.async.ConsumerSendSMSMessage;
import br.com.i9torpedos.model.service.async.BridgeSendSMSMessage;
import br.com.i9torpedos.model.service.exception.SocketServerException;
import br.com.i9torpedos.model.service.sms.DServiceModem1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Random;
import br.com.i9torpedos.model.service.async.Bridge;

/**
 *
 * @author Junior
 */
public class DSSocketSIM1  implements Runnable, Serializable {

    private ServerSocket server;
    private static final Integer PORTA_SERVERSOCKET = 1531;
    private Queue<SendSMSMessage> listaSMS;
    private static final Log log = LogFactory.getLog(DSSocketSIM1.class);
    private final Boolean conectou = true;
    private Random random;
    private final Bridge<SendSMSMessage> ponte; 
    

    public DSSocketSIM1() throws SocketServerException {
        this.listaSMS = new LinkedList<>();
        this.random  = new Random();
        this.ponte = new BridgeSendSMSMessage();

        if (server == null) {

            try {
                this.server = new ServerSocket(PORTA_SERVERSOCKET);
                log.info("Socket Server Iniciado com Sucesso porta: " + PORTA_SERVERSOCKET);

            } catch (IOException ex) {

                log.fatal("Falha ao iniciar o Servidor Socket na porta: " + PORTA_SERVERSOCKET + " " + ex.getMessage());
                throw new SocketServerException("Falha ao iniciar o Servidor Socket na porta: " + PORTA_SERVERSOCKET, ex);

            }
        }
    }

    @Override
    public void run() {

        try {
            while (true) {
                Socket socket = server.accept();
                log.info("Cliente conectado  " + socket.getInetAddress());

                try {

                    PrintWriter printWriter
                            = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(conectou);
                    log.info("Sinal de autenticação enviado para o Cliente  " + new Date().toString());

                    //Faz verificação no fluxo de entrada do Objeto
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                    try {

                        //faz a leitura do Objeto de entrada
                        Object readObject = objectInputStream.readObject();

                        if (readObject instanceof SendSMSMessage) {

                            try {

                                SendSMSMessage smsMessage = (SendSMSMessage) readObject;
                                
                               // Thread.sleep(random.nextInt(10000));
                                
                                
                                ponte.set(smsMessage);
                                Thread t = new Thread(new ConsumerSendSMSMessage(ponte), "PONTE_ASYNC_DSSOCKETSIM1");
                                t.start();
                                
                                listaSMS.add(smsMessage);

                                objectInputStream.close();
                                socket.close();

                                if (listaSMS.size() > 0 && !listaSMS.isEmpty()) {

                                    DServiceModem1 md1 = new DServiceModem1(listaSMS, 1000);
                                    new Thread(md1, "MODEM 1").start();
                                    listaSMS.clear();

                                }

                            } catch (InterruptedException ex) {
                                Logger.getLogger(DSSocketSIM1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DSSocketSIM1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(DSSocketSIM1.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(DSSocketSIM1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DSSocketSIM1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                server.close();
            } catch (IOException ex) {
                Logger.getLogger(DSSocketSIM1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
