package com.unity.jasper.reportsdemo.controller;

import com.unity.jasper.reportsdemo.controller.service.ReportService;
import com.unity.jasper.reportsdemo.entity.Address;
import com.unity.jasper.reportsdemo.entity.AddressRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ReportService reportService;


    @GetMapping(path = "/ad")
    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }


    @GetMapping(path = "/print/{format}")
    public ResponseEntity<byte[]> printReport(@PathVariable(name = "format") String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }



}
