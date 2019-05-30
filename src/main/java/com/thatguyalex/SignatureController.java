package com.thatguyalex;

import com.thatguyalex.model.ProcessData;
import com.thatguyalex.model.SigningSessionData;
import org.digidoc4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.Charset;
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
            X509Certificate userCert = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
                    new ByteArrayInputStream(DatatypeConverter.parseHexBinary(cert))
            );
            session.setNameFromDN(userCert.getSubjectDN().getName());

            /*
            ASN1OctetString akiOc = ASN1OctetString.getInstance(userCert.getExtensionValue("1.3.6.1.5.5.7.1.1"));
            AuthorityInformationAccess aia = AuthorityInformationAccess.getInstance(akiOc.getOctets());
            String ocspurl = aia.getAccessDescriptions()[0].getAccessLocation().getName().toString();
            System.out.println(ocspurl);
             */

            Configuration config = Configuration.getInstance();
            config.setPreferAiaOcsp(true);
            config.setTspSource("http://dd-at.ria.ee/tsa");
            //config.setOcspSource(ocspurl);

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
            return new ProcessData("requestSignature", ss);//org.apache.commons.codec.digest.DigestUtils.sha256Hex(ss));
        } catch (Exception exception) {
            return new ProcessData("error", "failed_creation");
        }
    }

    @PostMapping("/sign/finish")
    public ProcessData finishSign(String signature) {
        try {
            Signature finalSignature = session.getDataToSign().finalize(DatatypeConverter.parseHexBinary(signature));
            session.getContainer().addSignature(finalSignature);
            session.getContainer().saveAsFile(session.getFilePath(true));
            return new ProcessData("success", session.getName());
        } catch (Exception exception) {
            return new ProcessData("error", "failed_signature");
        }
    }

}
