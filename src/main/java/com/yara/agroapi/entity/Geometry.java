package com.yara.agroapi.entity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Geometry implements Serializable {
  private final static long serialVersionUID = 7702L;

  private String type;
  private ArrayList<ArrayList<ArrayList>> coordinates;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ArrayList<ArrayList<ArrayList>> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(ArrayList<ArrayList<ArrayList>> coordinates) {
    this.coordinates = coordinates;
  }

}
