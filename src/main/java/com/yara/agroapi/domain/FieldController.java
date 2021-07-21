package com.yara.agroapi.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yara.agroapi.entity.Fields;
import com.yara.agroapi.entity.GeoJson;
import com.yara.agroapi.repository.FieldsRepository;
import com.yara.agroapi.service.FieldService;
import com.yara.agroapi.service.WeatherService;
import java.net.URI;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("fields")
public class FieldController {
  @Autowired
  private FieldsRepository fieldsRepository;
  @Autowired
  private WeatherService weatherService;
  @Autowired
  private FieldService fieldService;

  @GetMapping
  public Iterable<Fields> getFields(){
    return this.fieldsRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Fields> find(@PathVariable("id") String id) {
    Optional<Fields> fields = fieldService.find(id);
    return ResponseEntity.of(fields);
  }

  @PostMapping
  public ResponseEntity<Fields> create(@RequestBody Fields fields) {
    Fields createdField = fieldService.create(fields);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(createdField.getId())
        .toUri();
    return ResponseEntity.created(location).body(createdField);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Fields> update(
      @PathVariable("id") String id,
      @RequestBody Fields updatedField) {

    Optional<Fields> updated = fieldService.update(id, updatedField);

    return updated
        .map(value -> ResponseEntity.ok().body(value))
        .orElseGet(() -> {
          Fields createdField = fieldService.create(updatedField);
          URI location = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(createdField.getId())
              .toUri();
          return ResponseEntity.created(location).body(createdField);
        });
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Fields> delete(@PathVariable("id") String id) {
    fieldService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/{id}/weather")
  public Object getFieldWeatherHistory(@PathVariable String id) throws JsonProcessingException {
    Optional<Fields> fields = fieldService.find(id);

    GeoJson geoJson = ResponseEntity.of(fields).getBody().getBoundaries().getGeoJson();

    Object response = this.weatherService.getFieldWeatherHistory(ResponseEntity.of(fields).getBody());

    return response;
  }





}

