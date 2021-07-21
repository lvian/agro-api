package com.yara.agroapi;

import com.yara.agroapi.repository.FieldsRepository;
import com.yara.agroapi.entity.Fields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AgroApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgroApiApplication.class, args);
  }
}
