package controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.product;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/productAPI")
public class productAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private product pObj;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productAPI() {
        super();
        pObj = new product();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String output = pObj.insertProductDetails(  request.getParameter("txtName"),
													request.getParameter("txtCode"),
													request.getParameter("txtQty"),
													request.getParameter("txtPrice"),
													request.getParameter("txtDesc") 
												  );
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map paras = getParasMap(request);
		 String output = pObj.updateProductDetails(   	paras.get("btnSaveProduct").toString(),
														paras.get("txtName").toString(),
														paras.get("txtCode").toString(),
														paras.get("txtQty").toString(),
														paras.get("txtPrice").toString(),
														paras.get("txtDesc").toString()
												   );
		 response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = pObj.deleteProductDetails(paras.get("productId").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
		{
			 Map<String, String> map = new HashMap<String, String>();
			 try
			 {
				 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				 String queryString = scanner.hasNext() ?
				 scanner.useDelimiter("\\A").next() : "";
				 scanner.close();
				 String[] params = queryString.split("&");
				 for (String param : params)
				 { 
				
					 String[] p = param.split("=");
					 map.put(p[0], p[1]);
				 }
			 }
			catch (Exception e)
			{ 
				
			}
		return map;
	}
}
