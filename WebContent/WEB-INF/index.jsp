<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:common>

	<jsp:body>
        
        
        <h1
			style="padding: 1em 0; margin: 0 7em 2.5em 7em; background: #117a8b; color: white; font-size: 1.5em; border-radius: 30px; text-align: center">
			Article
		</h1>
    	
    	<p style="margin-bottom: 1em;text-align: center;">
			<a class="btn"
				style="color: white; background: green; font-size: 1em; border-radius: 20px;"
				href="/gestiondestock/newarticle">Ajouter un article</a>
			<a class="btn"
				style="color: white; background: #d02f2f; font-size: 1em; border-radius: 20px;"
				href="?filtreseuil=1">Afficher article en desous des seuils</a>
			<a class="btn"
				style="color: white; background: green; font-size: 1em; border-radius: 20px;"
				href="/gestiondestock/articles">Afficher tous</a>
		</p>
    	    	
		<table class="table table-striped" style="text-align: center">
			<thead>
				<tr>
					<th scope="col">Nom</th>
					<th scope="col">Stock</th>
					<th scope="col">Seuil</th>
					<th scope="col">Prix</th>
					<th scope="col">Vendu en detail</th>
					<th scope="col">Codebar</th>
					<th scope="col">Fournisseur</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="article" items="${articles}">
					<tr class="${article.stock <= article.seuil ? 'table-danger' : ''}">
						<td>${article.nom}</td>
						<td>${article.stock}</td>
						<td>${article.seuil}</td>
						<td>${article.prix}</td>
						<td>${article.istVenduEnDetail ? 'Oui' : 'Non'}</td>
						<td>${article.codebar}</td>
						<td>
						
						<!-- Button trigger modal -->
						<button type="button" class="btn"
								style="background: #117a8b; color: white; border-radius: 30px !important;"
								data-toggle="modal"
								data-target="#fournisseurModal${Integer.toString(article.fournisseur.id)}">${article.fournisseur.nom}</button>

						<!-- Modal -->
						<div class="modal fade"
								id="fournisseurModal${Integer.toString(article.fournisseur.id)}"
								tabindex="-1" role="dialog"
								aria-labelledby="fournisseurModalLabel${Integer.toString(article.fournisseur.id)}"
								aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header"
											style="background: #117a8b; color: white; text-align: center;">
										<h5 class="modal-title"
												id="fournisseurModalLabel${Integer.toString(article.fournisseur.id)}">${article.fournisseur.nom}</h5>
										<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<div class="row">
											
											<div class="col-md-3">
							                	<label for="adresse">Adresse</label>
							                </div>
							                <div class="col-md-9">
								               <p>${article.fournisseur.adresse}</p>
							                </div>
							            
											<div class="col-md-3">
							                	<label for="seuil">Code Postal</label>
							    			</div>
							                <div class="col-md-9">
								               <p>${article.fournisseur.codePostal}</p>
							                </div>
							              
											<div class="col-md-3"> 
							                	<label for="prix">Téléphone</label>
							                </div>
							                <div class="col-md-9">
								               <p>${article.fournisseur.telephone}</p>
							                </div>
							                
									   	</div> 									
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Fermer</button>
										<a class="btn" style="color: white; background: #d6a028;"
												href="/gestiondestock/editfournisseur?edit=${article.fournisseur.id}">Modifier</a>
									</div>
								</div>
							</div>
						</div>
						
						</td>
						
						<td>
						<a class="btn"
							style="color: white; background: #d6a028; border-radius: 30px;"
							href="/gestiondestock/editarticle?edit=${article.id}">Modifier</a>
							<a class="btn"
							style="color: white; background: #d02f2f; border-radius: 30px;"
							href="?delete=${article.id}">Supprimer</a>
						</td>
					</tr>			 				
 				</c:forEach>
			</tbody>
		</table>

	</jsp:body>

</t:common>