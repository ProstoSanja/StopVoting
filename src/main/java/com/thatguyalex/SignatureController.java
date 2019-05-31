package com.thatguyalex;

import com.thatguyalex.model.ProcessData;
import com.thatguyalex.model.SigningSessionData;
import org.digidoc4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@RestController
public class SignatureController {



    @Autowired
    private SigningSessionData session;

    @PostMapping("/sign/start")
    public ProcessData startSign(String cert) {
        try {
            X509Certificate userCert = processCert(cert);

            Configuration config = Configuration.getInstance();
            config.setPreferAiaOcsp(true);
            config.setTspSource("http://dd-at.ria.ee/tsa");

            PrintWriter writer = new PrintWriter(session.getFilePath(false), "UTF-8");
            writer.println(session.getName());
            writer.close();

            session.setContainer(ContainerBuilder
                    .aContainer()
                    .withDataFile(session.getFilePath(false), "text/plain")
                    .withConfiguration(config)
                    .build()
            );

            session.setDataToSign(SignatureBuilder
                    .aSignature(session.getContainer())
                    .withSignatureDigestAlgorithm(DigestAlgorithm.SHA256)
                    .withSigningCertificate(userCert)
                    .withSignatureProfile(SignatureProfile.LT)
                    .buildDataToSign()
            );

            String ss = DatatypeConverter.printHexBinary(MessageDigest.getInstance("SHA-256").digest(session.getDataToSign().getDataToSign()));
            return new ProcessData("request_signature", ss);//org.apache.commons.codec.digest.DigestUtils.sha256Hex(ss));
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ProcessData("error", "failed_creation");
        }
    }

    @PostMapping("/sign/finish")
    public ProcessData finishSign(String signature) {
        try {
            Signature finalSignature = session.getDataToSign().finalize(DatatypeConverter.parseHexBinary(signature));
            session.getContainer().addSignature(finalSignature);
            session.getContainer().saveAsFile(session.getFilePath(true));
            return new ProcessData("success", new ProcessData.AuthData(session.getName(), session.doesFileExist()));
        } catch (Exception exception) {
            return new ProcessData("error", "failed_signature");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Resource> getFile() {
        try {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(session.getFilePath(true)));
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + session.getFileName(true) + "\"")
                    .body(resource);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
    @PostMapping("/auth")
    public ProcessData getFile(String cert) {
        try {
            processCert(cert);
            return new ProcessData("success", new ProcessData.AuthData(session.getName(), session.doesFileExist()));
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ProcessData("error", "auth_failed");
        }
    }

    private X509Certificate processCert(String cert) throws Exception {
        X509Certificate userCert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
                new ByteArrayInputStream(DatatypeConverter.parseHexBinary(cert))
        );
        session.setNameFromDN(userCert.getSubjectDN().getName());
        return userCert;
    }

}
