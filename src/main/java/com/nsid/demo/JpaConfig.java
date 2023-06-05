package com.nsid.demo;

import com.nsid.demo.controllers.ConjurController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

import javapasswordsdk.*;
import javapasswordsdk.exceptions.*;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class JpaConfig {

    @Autowired
    public Environment env;
    @Bean
    public DataSource datasource() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {


        PSDKPassword password = null;
        String username = null, pass = null;
        char[] content = null;
        char[] pass_c = null;
          /*
        StringBuilder sbf = new StringBuilder("");
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

            if (password != null) {

                sbf.append(pass_c);
            }
            else
            {

                sbf.append("");
                username = "username";
            }
        }
        pass = sbf.toString();
        */
        //StringBuilder sbf = new StringBuilder("");
        //sbf.append(pass_c);


        long startTime = System.nanoTime();
        String getData;
        pass =  ConjurController.getSecret(env);
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000 ;


        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/world")
                .username("javadb")
                .password(pass)
                .build();

    }
}
