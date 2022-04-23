package com.reportserver.report.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Funzione che si occupa delle invio di email contenti errori
     *
     * @param to
     * @param oggetto
     * @param Descrizione
     */
    public void sendEmail(String to, String oggetto, String Descrizione) {
    }
}
