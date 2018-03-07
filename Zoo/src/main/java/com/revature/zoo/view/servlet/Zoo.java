package com.revature.zoo.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.revature.zoo.dao.AnimalDAOImpl;
import com.revature.zoo.model.Animal;

/**
 * Servlet implementation class Welcome
 */
public class Zoo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Zoo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Zoo doGet");
		List<Animal> animals=new ArrayList<Animal>();
		try {
			animals = new AnimalDAOImpl().viewAnimal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(animals);
		response.setContentType("application/json;charset=utf-8");
		JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
		//for (Animal animal: animals) {
        for(int i=0; i< animals.size(); i++) {
			JSONObject container=  new JSONObject();
			JSONArray entry=  new JSONArray();
			JSONObject name =  new JSONObject();
			JSONObject species =  new JSONObject();
			name.put("ID", animals.get(i).getAnimalID());
			name.put("name", animals.get(i).getName());
			name.put("species", animals.get(i).getSpecies());
			//entry.add(name);
			//entry.add(species);
			container.put(i, name);
			array.add(container);
		}
        json.put("AnimalArray", array);
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter(); 
        pw.print(json.toString());
        pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Zoo doPost");
		String name = request.getParameter("name");
		String species = request.getParameter("species");
		Animal animal = new Animal(name, species);
		boolean success= false;
		try {
			success = new AnimalDAOImpl().addAnimal(animal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=utf-8");
		JSONObject container = new JSONObject();
		container.put("InsertResponse", success);
		PrintWriter pw = response.getWriter(); 
        pw.print(container);
        pw.close();
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("Zoo doPut");
		String name = request.getParameter("name");
		String species = request.getParameter("species");
		int id = Integer.parseInt(request.getParameter("ID"));
		Animal animal = new Animal(id, name, species);
		boolean success= false;
		try {
			success = new AnimalDAOImpl().updateAnimal(animal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=utf-8");
		JSONObject container = new JSONObject();
		container.put("UpdateResponse", success);
		PrintWriter pw = response.getWriter(); 
        pw.print(container);
        pw.close();
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("Zoo doDelete");
		String name = request.getParameter("name");
		String species = request.getParameter("species");
		int id = Integer.parseInt(request.getParameter("ID"));
		Animal animal = new Animal(id, name, species);
		boolean success= false;
		try {
			success = new AnimalDAOImpl().deleteAnimal(animal);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.setContentType("application/json;charset=utf-8");
		JSONObject container = new JSONObject();
		container.put("DeleteResponse", success);
		PrintWriter pw = response.getWriter(); 
        pw.print(container);
        pw.close();
	}

}
