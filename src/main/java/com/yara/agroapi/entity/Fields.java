package com.yara.agroapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * Fields.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "tblfields")
public class Fields {

  @Id
  @Column(name = "id", columnDefinition = "varchar")
  private String id;

  @Column(name = "name", columnDefinition = "varchar")
  private String name;

  @CreationTimestamp
  @Column(name = "created", columnDefinition = "timestamp")
  private Date created;

  @UpdateTimestamp
  @Column(name = "updated", columnDefinition = "timestamp")
  private Date updated;

  @Column(name = "countrycode", columnDefinition = "varchar")
  private String countryCode;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
      targetEntity = Boundaries.class)
  @JoinColumn(name = "id",referencedColumnName = "id")
  private Boundaries boundaries;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Boundaries getBoundaries() {
    return boundaries;
  }

  public void setBoundaries(Boundaries boundaries) {
    this.boundaries = boundaries;
  }

  public Fields updateWith(Fields fields) {
    return Fields.builder()
        .id(this.id)
        .name(fields.name)
        .created(fields.created)
        .updated(fields.updated)
        .countryCode(fields.countryCode)
        .boundaries(fields.boundaries)
        .build();
  }

}


