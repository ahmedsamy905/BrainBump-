package com.project.BrainBump.utils;

import com.project.BrainBump.models.Problem;

import java.util.List;

public class EmailUtils {
    public static String getVerificationEmailMessage(String name ,String host, String token){
        return "hello "+ name +"please click the link below to verify your accountn\n\n\n\n\n"+
                getVerificationUrl(host,token);
    }
    public static String getVerificationUrl (String host,String token){
        return host+"/register?token="+token;
    }
    public static String getProblemsToSolveMessage(String name,List<Problem>problems){
        String pre="hello"+name+"you have to solve this problem so click the link below\n\n\n\n";
        StringBuilder message = new StringBuilder();
        for(int i=0;i<problems.size();i++){
            message.append(pre);
            message.append(problems.get(i).getProblemUrl());
        }
        return message.toString();
    }
}
