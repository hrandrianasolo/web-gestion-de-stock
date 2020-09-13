<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:common>

	<jsp:body>
        <h1
			style="padding: 1em 0; margin: 0 7em 2.5em 7em; background: #117a8b; color: white; font-size: 1.5em; border-radius: 30px; text-align: center">
			Modifier fournisseur
		</h1>
    	
    	<form method="post" action="editfournisseur"
			style="margin-left: 30%">
			
			<input type="hidden" id="id" name="id"
				value="<c:out value="${fournisseur.id}" />" />
			
			<div class="form-group row">
				<div class="col-md-3">
                	<label for="nom">Nom<span class="requis">*</span></label>
            	</div>
                <div class="col-md-9">
	               <input type="text" id="nom" name="nom"
						value="<c:out value="${fournisseur.nom}" />" required>
                </div>
			
				<div class="col-md-3">
                	<label for="adresse">Adresse<span
						class="requis">*</span></label>
                </div>
                <div class="col-md-9">
	               <input type="text" id="adresse" name="adresse"
						value="<c:out value="${fournisseur.adresse}"/>" required>
                </div>
            
				<div class="col-md-3">
                	<label for="seuil">Code Postal<span
						class="requis">*</span></label>
    			</div>
                <div class="col-md-9">
	               <input type="number" id="codePostal" name="codePostal"
						value="<c:out value="${fournisseur.codePostal}"/>" required>
                </div>
              
				<div class="col-md-3"> 
                	<label for="prix">Téléphone<span class="requis">*</span></label>
                </div>
                <div class="col-md-9">
	                <input type="text" id="telephone" name="telephone"
						value="<c:out value="${fournisseur.telephone}"/>" required>
                </div>
                
		   	</div> 
		   	     
            <input type="submit" value="Modifier"
				style="color: white; background: #d6a028;"
				class="btn" />
            <br />

		</form>

	</jsp:body>

</t:common>