package com.ssl.pem.file;
import java.io.*;
import java.nio.file.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
//    public static String saveFile(String fileName, MultipartFile multipartFile)
//            throws IOException {
//        Path uploadPath = Paths.get("Files-Upload");
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        String fileCode = RandomStringUtils.randomAlphanumeric(8);
//
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ioe) {
//            throw new IOException("Could not save file: " + fileName, ioe);
//        }
//
//        return fileCode;
//    }
//
//    public static String saveFile(String fileName, MultipartFile multipartFile)
//            throws IOException, CertificateException {
//        Path uploadPath = Paths.get("Files-Upload");
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        String fileCode = RandomStringUtils.randomAlphanumeric(8);
//        Path outputPath = uploadPath.resolve(fileCode + "-" + fileName);
//
//        try (InputStream pemStream = multipartFile.getInputStream();
//             PemReader pemReader = new PemReader(new InputStreamReader(pemStream));
//             OutputStream derStream = Files.newOutputStream(outputPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
//
//            byte[] pemContent = pemReader.readPemObject().getContent();
//
//            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
//            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(pemContent));
//            byte[] derContent = cert.getEncoded();
//
//            derStream.write(derContent);
//        } catch (IOException | CertificateException e) {
//            throw new IOException("Could not save file: " + fileName, e);
//        }
//
//        return fileCode;
//    }

}
