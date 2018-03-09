package com.revature.controllers;

public class Person {
  private String name;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public Person() {}
  public Person(String name) {
    this.name = name;
  }

  public String toString() {
    return this.name;
  }
}