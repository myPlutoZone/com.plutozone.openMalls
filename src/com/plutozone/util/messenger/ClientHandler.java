/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.openMalls
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: ClientHandler.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240617144853][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.util.messenger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2024-06-17
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class ClientHandler extends Thread {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);
	
	@SuppressWarnings("rawtypes")
	protected static Vector handler = new Vector();
	
	/** Client */
	protected Socket client;
	
	/** Input/Output Object Stream */
	protected ObjectInputStream objectInputStream;
	protected ObjectOutputStream objectOutputStream;
	
	/**
	 * @param client [클라이언트]
	 * 
	 * @since 2017-06-14
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	@SuppressWarnings("unchecked")
	public ClientHandler(Socket client) {
		this.client = client;
		
		try {
			objectOutputStream	= new ObjectOutputStream(client.getOutputStream());
			objectInputStream	= new ObjectInputStream(client.getInputStream());
		}
		catch (IOException e) {
			logger.error("[" + this.getClass().getName() + ".ClientHandler()] " + e.getMessage(), e);
		}
		handler.addElement(this);
	}
	
	/**
	 * @since 2017-06-14
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public void run() {
		
		MessageObject messageObject = null;
		
		while (true) {
			
			try {
				if (objectOutputStream != null)
				messageObject = (MessageObject) objectInputStream.readObject();
			}
			catch (Exception e) {
				exit(this);
				logger.error("[" + this.getClass().getName() + ".run()] " + e.getMessage(), e);
				return;
			}
			
			if (messageObject.getControl() == -1) {
				broadcast(messageObject);
				exit(this);
				return;
			}
			else {
				broadcast(messageObject);
			}
		}
	}
	
	/**
	 * @param messageObject [메시지 객체]
	 * 
	 * @since 2017-06-14
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public void broadcast(MessageObject messageObject) {
		int countClient = handler.size();
		
		for (int nLoop = 0; nLoop < countClient; nLoop++) {
			
			try {
				ClientHandler clientHandler = (ClientHandler) handler.elementAt(nLoop);
				clientHandler.objectOutputStream.writeObject(messageObject);
				clientHandler.objectOutputStream.flush();
				clientHandler = null;
			}
			catch (IOException e) {
				logger.error("[" + this.getClass().getName() + ".broadcast()] " + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * @param clientHandler [클라이언트 핸들러]
	 * 
	 * @since 2017-06-14
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public void exit(ClientHandler clientHandler) {
		
		int numClient = handler.indexOf(clientHandler);
		
		if (numClient >= 0) {
			handler.removeElementAt(numClient);
			
			try {
				if (clientHandler.objectOutputStream != null) clientHandler.objectOutputStream.close();
				if (clientHandler.objectInputStream != null) clientHandler.objectInputStream.close();
				if (clientHandler.client != null) clientHandler.client.close();
			}
			catch (IOException e) {
				logger.error("[" + this.getClass().getName() + ".exit()] " + e.getMessage(), e);
			}
		}
	}

}
