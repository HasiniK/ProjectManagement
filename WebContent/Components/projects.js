/**
 * 
 */
 
 var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT"; 
 
 $.ajax(
{ 
 	url : "ProjectsAPI", 
 	type : type, 
 	data : $("#formProject").serialize(), 
 	dataType : "text", 
 	complete : function(response, status) 
 	{ 
 		onItemSaveComplete(response.responseText, status); 
 	} 
});


$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
 	$("#alertSuccess").text(""); 
 	$("#alertSuccess").hide(); 
 	$("#alertError").text(""); 
 	$("#alertError").hide(); 
 	
	// Form validation-------------------
	var status = validateProjectForm(); 
	if (status != true) 
 	{ 
 		$("#alertError").text(status); 
		$("#alertError").show(); 
 		return; 
 	} 
 	
	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT"; 
 	$.ajax( 
 	{ 
		 url : "ProjectsAPI", 
 		 type : type, 
	 	 data : $("#formProject").serialize(), 
 		 dataType : "text", 
 		 complete : function(response, status) 
 		 { 
	 		 onItemSaveComplete(response.responseText, status); 
 		 } 
 	}); 
});


function onItemSaveComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully saved."); 
 			$("#alertSuccess").show(); 
			$("#divItemsGrid").html(resultSet.data); 
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
 		$("#hidProjectIDSave").val(""); 
 		$("#formProject")[0].reset(); 
}



$(document).on("click", ".btnUpdate", function(event)
{ 
	$("#hidProjectIDSave").val($(this).data("itemid")); 
 	$("#projectTitle").val($(this).closest("tr").find('td:eq(0)').text()); 
 	$("#projectDescription").val($(this).closest("tr").find('td:eq(1)').text()); 
 	$("#projectProposalLink").val($(this).closest("tr").find('td:eq(2)').text()); 
	$("#projectVideoLink").val($(this).closest("tr").find('td:eq(3)').text()); 
});


$(document).on("click", ".btnRemove", function(event)
{ 
 	$.ajax( 
 	{ 
 		url : "ProjectsAPI", 
 		type : "DELETE", 
 		data : "projectID=" + $(this).data("itemid"),
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemDeleteComplete(response.responseText, status); 
 		} 
 	}); 
});



function onItemDeleteComplete(response, status)
{ 
	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
 		{ 
 			$("#alertSuccess").text("Successfully deleted."); 
 			$("#alertSuccess").show(); 
 			$("#divItemsGrid").html(resultSet.data); 
 		} else if (resultSet.status.trim() == "error") 
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
 