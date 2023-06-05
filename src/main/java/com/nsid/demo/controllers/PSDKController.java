package com.nsid.demo.controllers;

//cyberark
//please import cyberark sdk first
import java.util.Arrays;
import javapasswordsdk.*;
import javapasswordsdk.exceptions.*;
import org.springframework.core.env.Environment;

public class PSDKController {
    public static String getPSDK(Environment env) {
        PSDKPassword password = null;
        String username = null, pass = null;
        char[] content = null;
        char[] pass_c = null;
        try {
            PSDKPasswordRequest passRequest = new PSDKPasswordRequest();

            // Set request properties
            passRequest.setAppID(env.getProperty("PSDKPassword.AppID"));
            passRequest.setSafe(env.getProperty("PSDKPassword.Safe"));
            passRequest.setFolder(env.getProperty("PSDKPassword.Folder"));
            passRequest.setObject(env.getProperty("PSDKPassword.Object"));
            passRequest.setReason("request secure configuration");


            // Get password object
            password = javapasswordsdk.PasswordSDK.getPassword(passRequest);

            // Get password content
            pass_c = password.getSecureContent();
            //pass = content.toString();
            username = password.getUserName();

            //test only!
            System.out.println(password.getUserName());
            System.out.println(pass_c);


        } catch (PSDKException ex) {
            ex.printStackTrace();
        } finally {
            if (content != null) {
                // Clean the returned object
                //Arrays.fill(content, (char) 0);
            }
            if (password != null) {
                // Dispose of resources used by this PSDKPassword object
                //password.dispose();
            }
        }

        //set to application properties
        StringBuilder sbf = new StringBuilder("");
        sbf.append(pass_c);
        return  "username : "+username+ " password : "+ sbf +" ";
    }
}
