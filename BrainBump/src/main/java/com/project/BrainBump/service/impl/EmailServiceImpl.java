package com.project.BrainBump.service.impl;

import com.project.BrainBump.models.Confirmation;
import com.project.BrainBump.models.Member;
import com.project.BrainBump.models.UserProfile;
import com.project.BrainBump.repo.ConfirmationRepo;
import com.project.BrainBump.repo.MemberRepo;
import com.project.BrainBump.service.EmailService;
import com.project.BrainBump.service.ProblemService;
import com.project.BrainBump.service.UserProfileService;
import com.project.BrainBump.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

import static com.project.BrainBump.utils.EmailUtils.getVerificationEmailMessage;
import static com.project.BrainBump.utils.EmailUtils.getVerificationUrl;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    ConfirmationRepo confirmationRepo;
    @Autowired
    MemberRepo memberRepo;
    @Autowired
    private UserProfileService userProfileService;
    @Value("${EMAIL}")
    String from;
    @Value("${HOST}")
    String host;
    @Override
    public String verifyAccount(String token) {
        Confirmation  confirmation = confirmationRepo.findByToken(token);
        if(confirmation==null){
            throw new RuntimeException("the token is wrong");
        }
        Member member = memberRepo.findByEmail(confirmation.getMember().getEmail());
        member.setEnabled(true);
        UserProfile userProfile = userProfileService.createNewProfile(member);
        member.setUserProfile(userProfile);
        memberRepo.save(member);
        return "your account is verified successfully";
    }

    @Override
    public void sendSimpleMailMessage(String name, String to, String token) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom(from);
            message.setSubject("verifying new account");
            message.setText(getVerificationEmailMessage(name,host,token));
            mailSender.send(message);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void sendHtmlEmail(String name, String to, String token) {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            Context context = new Context();
            context.setVariable("url",getVerificationUrl(host,token));
            context.setVariable("name",name);
            String text = templateEngine.process("emailTemplate",context);
            helper.setTo(to);
            helper.setPriority(1);
            helper.setFrom(from);
            helper.setSubject("VERIFYING NEW ACCOUNT");
            helper.setText(text,true);
            mailSender.send(message);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void sendResolveProblemMail() {
        List<UserProfile> users= userProfileService.getAllUsersProfile();
        for (UserProfile user : users) {
            if (problemService.getReminders(user.getUserProfileId()).isEmpty()) {
                return;
            } else {
                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message);
                    Member member = user.getMember();
                    String to = member.getEmail();
                    helper.setTo(to);
                    helper.setPriority(1);
                    helper.setFrom(from);
                    helper.setSubject("PROBLEMS TO SOLVE TODAY");
                    String text = EmailUtils.getProblemsToSolveMessage(member.getMemberName(), problemService.getReminders(user.getUserProfileId()));
                    helper.setText(text);
                    mailSender.send(message);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
//        System.out.println("helllllllllllllllllo");
    }


}
