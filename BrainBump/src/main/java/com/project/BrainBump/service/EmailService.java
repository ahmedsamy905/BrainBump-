package com.project.BrainBump.service;

public interface EmailService {
    String verifyAccount(String token);
    void sendSimpleMailMessage(String name,String to ,String token);
    void sendHtmlEmail(String  name,String to ,String token);
    void sendResolveProblemMail();
}
