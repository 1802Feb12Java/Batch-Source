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
 * Servlet implementation class ZooAjax
 */
public class ZooAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZooAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("ZooAjax doGet");
		List<Animal> animals=new ArrayList<Animal>();
		try {
			animals = new AnimalDAOImpl().viewAnimal();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		System.out.println("ZooAjax doPost");
		doGet(request, response);
	}

}
