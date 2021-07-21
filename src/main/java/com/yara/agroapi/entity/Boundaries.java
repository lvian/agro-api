package com.yara.agroapi.entity;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import java.util.Date;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;


/**
 * Boundaries.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "tblboundaries")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Boundaries {

  @Id
  @Column(name = "id", columnDefinition = "varchar")
  private String id;

  @CreationTimestamp
  @Column(name = "created", columnDefinition = "timestamp")
  private Date created;

  @UpdateTimestamp
  @Column(name = "updated", columnDefinition = "timestamp")
  private Date updated;

  @Type(type = "json")
  @Column(name = "geojson", columnDefinition = "jsonb")
  private GeoJson geoJson;

  public String getId() {
    return id;
  }

  public void setId(String id) { this.id = id; }

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

  public GeoJson getGeoJson() { return geoJson; }

  public void setGeoJson(GeoJson geoJson) { this.geoJson = geoJson; }
}



