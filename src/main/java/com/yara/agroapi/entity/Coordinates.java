package com.yara.agroapi.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Coordinates implements Serializable {
  private final static long serialVersionUID = 7702L;

  private Double x;
  private Double y;

  public Double getX() {
    return x;
  }

  public void setX(Double x) {
    this.x = x;
  }

  public Double getY() {
    return y;
  }

  public void setY(Double y) {
    this.y = y;
  }
}
