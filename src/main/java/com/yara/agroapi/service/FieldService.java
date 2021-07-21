package com.yara.agroapi.service;

import com.yara.agroapi.entity.Boundaries;
import com.yara.agroapi.entity.Fields;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FieldService {
  private final CrudRepository<Fields, String> fieldRepository;

  public FieldService(CrudRepository<Fields, String> fieldRepository) {
    this.fieldRepository = fieldRepository;
  }

  public List<Fields> findAll() {
    List<Fields> list = new ArrayList<>();
    Iterable<Fields> items = fieldRepository.findAll();
    items.forEach(list::add);
    return list;
  }

  public Optional<Fields> find(String id) {
    return fieldRepository.findById(id);
  }

  public Fields create(Fields fields) {
    Fields field = Fields.builder()
            .id(fields.getId())
            .name(fields.getName())
            .created(fields.getCreated())
            .updated(fields.getUpdated())
            .countryCode(fields.getCountryCode())
            .boundaries(fields.getBoundaries())
            .build();

    return fieldRepository.save(field);
  }

  public Optional<Fields> update( String id, Fields newField) {
    // Only update an fields if it can be found first.
    return fieldRepository.findById(id)
        .map(oldField -> {
          Fields updated = oldField.updateWith(newField);
          return fieldRepository.save(updated);
        });
  }

  public void delete(String id) {
    fieldRepository.deleteById(id);
  }
}
