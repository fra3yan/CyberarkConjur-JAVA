package com.nsid.demo.controllers;

import com.cyberark.conjur.api.Token;
import lombok.val;
import org.springframework.core.env.Environment;
import com.cyberark.conjur.api.Conjur;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class ConjurController {

    /*
    cara ambil dari env variable open shift
    @Value("$(conjur.auth.login)")
    private String conjurAuthLogin;


     */
    public static String getSecret(Environment env) throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, KeyManagementException {

        long startTime = System.nanoTime();
        final String SSL_CERTIFICATE = "CONJUR_SSL_CERTIFICATE";
        System.out.println("--------------------------------------------");
        System.out.println(env.getProperty(SSL_CERTIFICATE));
        val tlsCaString = env.getProperty(SSL_CERTIFICATE);
        val certificateFactory = CertificateFactory.getInstance("X.509");
        val inputStream = new FileInputStream("D:\\temp\\nsid-master.cer");
        val certificate = certificateFactory.generateCertificate(inputStream);
        val keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null);
        keyStore.setCertificateEntry("conjurTlsCaPath", certificate);
        System.out.println("--------------------------------------------");
        val trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
        System.out.println("--------------------------------------------");
        trustManagerFactory.init(keyStore);
        System.out.println("init(keyStore)");
        val sslContext = SSLContext.getInstance("TLS");
        System.out.println("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        System.out.println("init");
        Conjur Conjur = new Conjur( sslContext);
        String secret = Conjur.variables().retrieveSecret("Vault/NSIDN/cityapp/laundry-db/password");
        long endTime   = System.nanoTime();
        long totalTime = (endTime - startTime)/1000000 ;
        System.out.print("Conjur Execution Time in ms : ");
        System.out.println(totalTime);
        return secret;
    }
}
