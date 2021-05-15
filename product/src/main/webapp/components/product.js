/**
 * 
 */
$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

//--------SAVE-------
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
	// Form validation-------------------
	var status = validateItemForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	 }
	// If valid------------------------
	var type = ($("#btnSaveProduct").val() == "") ? "POST" : "PUT";
	 $.ajax(	{
					 url : "productAPI",
					 type : type,
					 data : $("#formProduct").serialize(),
					 dataType : "text",
					 complete : function(response, status)
					 {
						 location.reload(true);
						 onItemSaveComplete(response.responseText, status);
					
					 }
				 }
			); 
});

//------UPDATE------
$(document).on("click", ".btnUpdate", function(event)
{
	$("#btnSaveProduct").val($(this).data("pID"));
	 $("#txtName").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#txtCode").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#txtQty").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#txtPrice").val($(this).closest("tr").find('td:eq(4)').text());
	 $("#txtDesc").val($(this).closest("tr").find('td:eq(5)').text());
	});
	
	$(document).on("click", ".btnRemove", function(event)
	{
		 $.ajax( {
					url : "productAPI",
					type : "DELETE",
					data : "productId=" + $(this).data("pID"),
					dataType : "text",
			 		complete : function(response, status)
			 		{
						location.reload(true);
						onItemDeleteComplete(response.responseText, status);
					 }
	 	          }
				);
});

function validateItemForm() 
{ 
	// for Product Name....
	if ($("#txtName").val().trim() == "") 
	 { 
	 	return "Please Enter the product Name!!!"; 
	 } 

	// for Product Code....
	if ($("#txtCode").val().trim() == "") 
	 { 
	 	return "Please Enter the product Code!!!"; 
	 }

	// for Product Qty....
	if ($("#txtQty").val().trim() == "") 
	 { 
	 	return "Please Enter the product Qty!!!"; 
	 } 

	// for Product Price....
	if ($("#txtPrice").val().trim() == "") 
	 { 
	 	return "Please Enter the product Price!!!"; 
	 }

	// for Product Desc....
	if ($("#txtDesc").val().trim() == "") 
	 { 
	 	return "Please Enter the product Desc!!!"; 
	 }
  return true; 
} 

function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully saved.");
			 $("#alertSuccess").show();
			 $("#divProductGrid").html(resultSet.data);
		 } 
		 else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
	} 
	else if (status == "error")
	{
		 $("#alertError").text("Error while saving.");
		 $("#alertError").show();
	 }
	 else
	 {
		 $("#alertError").text("Unknown error while saving..");
		 $("#alertError").show();
	 } 
	
	 $("#btnSaveProduct").val("");
	 $("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	 {
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
	 	 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 $("#divProductGrid").html(resultSet.data);
	 	 } 
	 	 else if (resultSet.status.trim() == "error")
	 	 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
	 	 }
	 } 
	 else if (status == "error")
	 {
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
	 } 
     else
	 {
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
	 }
}   