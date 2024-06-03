<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8" %>
<%@ page import="com.app.model.FinancialAccount" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Lançamentos financeiros</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h1 >Lançamentos financeiros </h1>
			</div>
			<div class="col-md-2" style="line-height: 3.5;"><a href="account-registration.jsp">Novo Registro</a></div>
		</div>
		<hr />
		<div class="row">
			<div class="col-md-2" style = "background-color: #59f459; padding: 20px; font-size:22px;">
			R$ <% out.print(request.getAttribute("totalCredit")); %>
			</div>
			
			<div class="col-md-2" style = "background-color: #ff4f4f; padding: 20px; font-size:22px;">
			R$ <% out.print(request.getAttribute("totalDebit")); %>
			</div>
		</div>
		<div class="row">
			<table class="table">
			  <thead>
			    <tr>
			      <th scope="col">Cod.</th>
			      <th scope="col">Data</th>
			      <th scope="col">Descrição</th>
			      <th scope="col">Pessoa</th>
			      <th scope="col">Tipo</th>
			      <th scope="col">Valor</th>
			      <th scope="col"></th>
			    </tr>
			  </thead>
			  <tbody>
			  	<%
			  		ArrayList<FinancialAccount> list = (ArrayList<FinancialAccount>) request.getAttribute("accounts");
			  	
			  		for (int i = 0; i < list.size(); i++) {
			  	%>
			    <tr>
			      <th scope="row"><%= list.get(i).getId() %></th>
			      <td><%= list.get(i).getDate() %></td>
			      <td><%= list.get(i).getDescription() %></td>
			      <td><%= list.get(i).getPerson() %></td>
			      <td><%= list.get(i).getType() %></td>
			      <td><%= list.get(i).getValue() %></td>
			      <td>
			      		<a href="edit?id=<%= list.get(i).getId() %>">Editar</a> | 
			      		<a href="delete?id=<%= list.get(i).getId() %>" onclick="return confirm('Deseja realmente excluir este registro? Esse processo é irreversível.');">Excluir</a>
			      </td>
			    </tr>
			    <% 	} %>
			  </tbody>
			</table>
		</div>
	</div>

</body>
</html>