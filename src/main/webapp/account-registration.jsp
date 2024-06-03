<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Lançamentos financeiros</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h1 >Lançamentos financeiros </h1>
			</div>
			<div class="col-md-2" style="line-height: 3.5;"><a href="financial">Consultar</a></div>
		</div>
		<hr />
		<div class="row">

			<div class="box-header with-border">
				<h3 class="box-title"><%= (request.getAttribute("id") == null ) ? "Cadastrar" : "Alterar" %> Conta</h3>
			</div>

			<form class="row g-3 needs-validation" action="<%= (request.getAttribute("id") == null ) ? "add" : "update" %>">
			
				<input type="hidden" name="id" value="<% if(request.getAttribute("id") != null) { out.print(request.getAttribute("id")); } %>">

				<div class="col-md-3">
					<label class="form-label">Data</label> 
					<input type="date" name="date" class="form-control" value="<% if(request.getAttribute("date") != null) { out.print(request.getAttribute("date")); } %>" required>
				</div>
				
				<div class="col-md-3">
					<label class="form-label">Tipo de lançamento</label> 
					<select name="type" class="form-select" required>
						<option selected disabled value=""> -- Selecione --</option>
						<option value="credito" <%= (request.getAttribute("type") != null && request.getAttribute("type").equals("credito")) ? "selected" : "" %>>Crédito</option>
						<option value="debito" <%= (request.getAttribute("type") != null && request.getAttribute("type").equals("debito")) ? "selected" : "" %>>Débito</option>
					</select>
				</div>

				<div class="col-md-6">
					<label class="form-label">Descrição</label> 
					<input type="text" name="description" class="form-control" value="<% if(request.getAttribute("description") != null) { out.print(request.getAttribute("description")); } %>" required>
				</div>

				<div class="col-md-4">
					<label class="form-label">Pessoa (PF ou PJ)</label> 
					<input type="text" name="person" class="form-control" value="<% if(request.getAttribute("person") != null) { out.print(request.getAttribute("person")); } %>" required>
				</div>
				
				<div class="col-md-4">
					<label class="form-label">Valor</label> 
					<input type="text" name="value" class="form-control" value="<% if(request.getAttribute("value") != null) { out.print(request.getAttribute("value")); } %>" required>
				</div>
				
				<div class="col-12">
					<button class="btn btn-primary" type="submit">Salvar</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>