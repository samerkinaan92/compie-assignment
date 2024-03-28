package com.example.compieassignment.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws IOException {
        log.info(String.format("handling message from session id %s with message %s", session.getId(), message.getPayload()));
		// Handle incoming messages here
        String receivedMessage = (String) message.getPayload();
		// Process the message and send a response if needed
        session.sendMessage(new TextMessage("Received: " + receivedMessage));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info(String.format("established connection with session id: %s", session.getId()));
		// Perform actions when a new WebSocket connection is established
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info(String.format("closed connection with session id: %s", session.getId()));
		// Perform actions when a WebSocket connection is closed
    }
}
