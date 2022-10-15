package com.mcarn.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.mcarn.model.Simple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class HelloController {

  private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

  private final XmlMapper xmlMapper;

  @Autowired
  public HelloController() {
    this.xmlMapper = new XmlMapper();
  }

  @PostMapping("/")
  public void upload(@RequestParam("file") MultipartFile file) throws IOException {
    Optional.ofNullable(file.getOriginalFilename())
        .ifPresent(fileName -> logger.info(String.format("File received: %s", fileName)));

    Simple value = xmlMapper.readValue(file.getInputStream(), Simple.class);

    value.getDescription().forEach((k, v) -> logger.info(String.format("%s: %s", k, v)));
  }
}
