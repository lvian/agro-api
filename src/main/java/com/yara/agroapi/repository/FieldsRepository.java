package com.yara.agroapi.repository;

import com.yara.agroapi.entity.Fields;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldsRepository extends CrudRepository<Fields, String> {
}
