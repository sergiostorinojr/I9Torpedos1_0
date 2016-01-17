/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9torpedos.model.service.sms.util;

import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;

/**
 *
 * @author Junior
 */
public class GatewayStatusNotification implements IGatewayStatusNotification
	{

    /**
     *
     * @param gateway
     * @param oldStatus
     * @param newStatus
     */
    @Override
		public void process(AGateway gateway, AGateway.GatewayStatuses oldStatus, AGateway.GatewayStatuses newStatus)
		{
			System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
		}
	}
