package com.unity.jasper.reportsdemo.controller.service;

import com.unity.jasper.reportsdemo.entity.Address;
import com.unity.jasper.reportsdemo.entity.AddressRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class ReportService {

    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<byte[]> exportReport(String reportFormat) throws FileNotFoundException, JRException {

        Random random = new Random();
        int x = random.nextInt(50000);
        int y = random.nextInt(60000);

        List<Address> addresses = addressRepository.findAllByDistrictContaining("Alberta");
        File file = ResourceUtils.getFile("classpath:address-report.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(addresses);
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("created by", "Unity");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);

        byte [] data = JasperExportManager.exportReportToPdf(jasperPrint);

        HttpHeaders httpHeaders = new HttpHeaders();
        //httpHeaders.add(HttpHeaders.CONTENT_TYPE,"application/pdf");
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION,"filename="+System.currentTimeMillis()+"_adr.pdf");
//        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename="+x+"ad_report"+y+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(data) ;
    }

}



//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\address.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\"+x+"add"+y+".pdf");
//        }