package service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.product;

@Path("/seller")
public class productService {
	product pro = new product();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readProduct()
	{
		return pro.readProductDetails();
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertProduct( @FormParam("productName") String name,
					@FormParam("productCode") String code,
					@FormParam("productQty") String qty,
					@FormParam("productPrice") String price,
					@FormParam("productDesc") String desc ) 
	{
			
			return pro.insertProductDetails(name, code, qty, price, desc);
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateProduct(String productDetails) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject obj = new JsonParser().parse(productDetails).getAsJsonObject(); 
		//Read the values from the JSON object
		 String id = obj.get("productId").getAsString(); 
		 String name = obj.get("productName").getAsString(); 
		 String code = obj.get("productCode").getAsString(); 
		 String qty = obj.get("productQty").getAsString(); 
		 String price = obj.get("productPrice").getAsString(); 
		 String desc = obj.get("productDesc").getAsString();
		 String response = pro.updateProductDetails(id, name, code, qty, price, desc); 
		return response; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteProduct(String productDetails) 
	{ 
		 Document doc = Jsoup.parse(productDetails, " ", Parser.xmlParser()); 
		 String id = doc.select("productId").text(); 
		 String status = pro.deleteProductDetails(id); 
		return status; 
	}
}
