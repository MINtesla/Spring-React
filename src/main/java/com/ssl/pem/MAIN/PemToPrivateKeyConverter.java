package com.ssl.pem.MAIN;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class PemToPrivateKeyConverter {

    public void convertPEMtoPK8(MultipartFile multipartFile, String pk8FilePath) {
        try {
            // Create a temporary file to store the PEM content from MultipartFile
            File tempPemFile = File.createTempFile("temp", ".pem");
            multipartFile.transferTo(tempPemFile);

            // Command to execute openssl
            String[] command = {
                    "openssl", "pkcs8", "-topk8", "-inform", "PEM",
                    "-outform", "DER", "-in", tempPemFile.getAbsolutePath(), "-out", pk8FilePath, "-nocrypt"
            };

            // Create process builder
            ProcessBuilder pb = new ProcessBuilder(command);
            Process process = pb.start();

            // Read output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Conversion successful.");
            } else {
                System.out.println("Conversion failed with exit code: " + exitCode);
            }

            // Clean up the temporary file
            if (!tempPemFile.delete()) {
                System.out.println("Failed to delete temporary file: " + tempPemFile.getAbsolutePath());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



//    public static void convertPEMtoPK8(String pemFilePath, String pk8FilePath) {
//        try {
//            // Command to execute openssl
//            String[] command = {
//                    "openssl", "pkcs8", "-topk8", "-inform", "PEM",
//                    "-outform", "DER", "-in", pemFilePath, "-out", pk8FilePath, "-nocrypt"
//            };
//
//            // Create process builder
//            ProcessBuilder pb = new ProcessBuilder(command);
//            Process process = pb.start();
//
//            // Read output
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // Wait for the process to complete
//            int exitCode = process.waitFor();
//            if (exitCode == 0) {
//                System.out.println("Conversion successful.");
//            } else {
//                System.out.println("Conversion failed with exit code: " + exitCode);
//            }
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        String pem = "";
//
//
//        String pemFilePath = "/Users/ashutoshsingh/pgdb_gcp/client-key.pem";
//        String pk8FilePath = "/Users/ashutoshsingh/pgdb_gcp/client-key.pk8";
//
//
//        try {
//            convertPEMtoPK8(pemFilePath, pk8FilePath);
//         //   System.out.println("Private Key: " + privateKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
