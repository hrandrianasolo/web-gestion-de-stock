<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:commoncaisse>

	<jsp:body>
        
        
        <h1
			style="padding: 1em 0; margin: 0 7em 2.5em 7em; background: #117a8b; color: white; font-size: 1.5em; border-radius: 30px; text-align: center">
			Achat en cours
		</h1>
    	
		<p>
		<button class="btn btn-primary" style="background: #117a8b" type="button"
				data-toggle="collapse" data-target="#collapseAjouter"
				aria-expanded="false" aria-controls="collapseAjouter">
				Ajouter dans panier
		</button>
		<button class="btn btn-primary" style="background: #117a8b"
				type="button" data-toggle="collapse" data-target="#collapseRetour"
				aria-expanded="false" aria-controls="collapseRetour">
				Retour article
		</button>
		</p>
		
		<div class="collapse" id="collapseAjouter">
			<form method="post" action="achat" style="text-align: center">
				<div class="form-group row">
	                
	                <div class="col-md-3">
	                	<input type="submit" value="Ajouter article"
							style="color: white; background: green; border-radius: 30px;"
							class="btn" />
	                </div>
	                <div class="col-md-9">
		               <input style="width: 100%; height: 100%" type="number"
							min="0" id="codebar" name="codebar" alt="Codebar" required>
	                </div>
	                </div>
			</form>
		</div>
		
		<div class="collapse" id="collapseRetour">
			<form method="post" action="achat" style="text-align: center">
				<div class="form-group row">
	                
	                <div class="col-md-3">
	                	<input type="submit" value="Retour article"
							style="color: white; background: green; border-radius: 30px;"
							class="btn" />
	                </div>
	                <div class="col-md-9">
		               <input style="width: 100%; height: 100%" type="number"
							min="0" id="codebarretour" name="codebarretour" alt="Codebar"
							required>
	                </div>
	                </div>
			</form>
		</div>
		    	
		<table class="table table-striped" style="text-align: center">
			<thead>
				<tr>
					<th scope="col">Nom</th>
					<th scope="col">Nombre d'article</th>
					<th scope="col">Prix unitaire</th>
					<th scope="col">Prix total</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="articleVendu"
					items="${currentTransactionCaisse.articleVendus}">
					<tr>
						<td>${articleVendu.article.nom}</td>
						<td>${articleVendu.nombre}</td>
						<td>${articleVendu.article.prix}</td>
						<td>${articleVendu.article.prix * articleVendu.nombre}</td>
						
						<td>
						<a class="btn"
							style="color: white; background: #c74747; border-radius: 30px;"
							href="?deleteone=${articleVendu.article.id}">Supprimer un</a>
						<a class="btn"
							style="color: white; background: #d02f2f; border-radius: 30px;"
							href="?deleteall=${articleVendu.article.id}">Supprimer tous</a>
							
						</td>
					</tr>			 				
 				</c:forEach>
			</tbody>
		</table>

	</jsp:body>

</t:commoncaisse>