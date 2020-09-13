<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:common>

	<jsp:body>
        <h1
			style="padding: 1em 0; margin: 0 7em 2.5em 7em; background: #117a8b; color: white; font-size: 1.5em; border-radius: 30px; text-align: center">
			Fournisseur
		</h1>
    	
    	<p style="margin-bottom: 1em;text-align: center">
			<a class="btn"
				style="color: white; background: green; font-size: 1em; border-radius: 20px;"
				href="/gestiondestock/newfournisseur">Ajouter un fournisseur</a>
		</p>
    	    	
		<table class="table table-striped" style="text-align: center">
			<thead>
				<tr>
					<th scope="col">Nom</th>
					<th scope="col">Adresse</th>
					<th scope="col">Codepostal</th>
					<th scope="col">Téléphone</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="fournisseur" items="${fournisseurs}">
					<tr>
						<td>${fournisseur.nom}</td>
						<td>${fournisseur.adresse}</td>
						<td>${fournisseur.codePostal}</td>
						<td>${fournisseur.telephone}</td>
						<td>
						<a class="btn"
							style="color: white; background: #d6a028;border-radius: 20px;"
							href="/gestiondestock/editfournisseur?edit=${fournisseur.id}">Modifier</a>
						<a class="btn"
							style="color: white; background: #d02f2f;border-radius: 20px;"
							href="?delete=${fournisseur.id}">Supprimer</a>
						</td>
					</tr>			 				
 				</c:forEach>
			</tbody>
		</table>

	</jsp:body>

</t:common>