package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //response.getWriter().append("Hello, World!");
    String jsonString = "{\"name\":\"Mehrab Rahman\"}";

    response.setContentType("application/json");

    ObjectMapper mapper = new ObjectMapper();
    Person me = mapper.readValue(jsonString, Person.class);

    //send back Person
    mapper.writeValue(response.getOutputStream(), me);
  }
}