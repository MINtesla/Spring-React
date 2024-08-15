package com.ssl.pem.MAIN;

import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Service
public class PemToDerConverter {

    public String  convertPemToDer(MultipartFile multipartFile){

        if(!multipartFile.getContentType().equalsIgnoreCase("application/x-x509-ca-cert")){

            return "NONCONVERTABLE";
        }
        String response ="";
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        String newFileName=fileName.substring(0,fileName.length()-3)+"der";
        if(!fileName.substring(fileName.length()-3,fileName.length()).equalsIgnoreCase("pem")){
            return "NONCONVERTABLE";
        }
        String outputFilePath ="/Users/ashutoshsingh/pgdb_gcp/projects/pem/src/main/resources/"+newFileName;
        try (InputStream pemStream = multipartFile.getInputStream();
             PemReader pemReader = new PemReader(new InputStreamReader(pemStream));
             OutputStream derStream = new FileOutputStream(outputFilePath)) {

            byte[] pemContent = pemReader.readPemObject().getContent();

            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(pemContent));
            byte[] derContent = cert.getEncoded();

            derStream.write(derContent);
            response+=newFileName+"|";
            response+=String.valueOf(size);

        }catch(Exception e){

            response="ISSUEWHENCONVERTING";
        }
        return response;
    }
}
