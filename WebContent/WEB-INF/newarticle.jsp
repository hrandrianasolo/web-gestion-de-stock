<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:common>

	<jsp:body>
        <h1
			style="padding: 1em 0; margin: 0 7em 2.5em 7em; background: #117a8b; color: white; font-size: 1.5em; border-radius: 30px; text-align: center">
			Nouvelle article
		</h1>
    	
    	<form method="post" action="newarticle" style="margin-left: 30%">
			<div class="form-group row">
				<div class="col-md-3">
                	<label for="nom">Nom<span class="requis">*</span></label>
            	</div>
                <div class="col-md-9">
	               <input type="text" id="nom" name="nom"
						value="<c:out value="${article.nom}" />" required>
                </div>
			
				<div class="col-md-3">
                	<label for="stock">Stock<span class="requis">*</span></label>
                </div>
                <div class="col-md-9">
	               <input type="number" id="stock" min="0" name="stock"
						value="<c:out value="${article.stock}"/>" required>
                </div>
            
				<div class="col-md-3">
                	<label for="seuil">Seuil<span class="requis">*</span></label>
    			</div>
                <div class="col-md-9">
	               <input type="number" id="seuil" min="0" name="seuil"
						value="<c:out value="${article.seuil}"/>" required>
                </div>
              
				<div class="col-md-3"> 
                	<label for="prix">Prix<span class="requis">*</span></label>
                </div>
                <div class="col-md-9">
	                <input type="number" step="any" min="O" id="prix" name="prix"
						value="<c:out value="${article.seuil}"/>" required>
                </div>
			
				<div class="col-md-3">
	            	<label for="istVenduEnDetail">Vendu en detail<span
						class="requis">*</span></label>
    			</div>
                <div class="col-md-9">
	                <input type="number" max="1" min="0"
						id="istVenduEnDetail" name="istVenduEnDetail"
						value="<c:out value="${article.istVenduEnDetail}"/>" required> 0 : Non | 1 : Oui
                </div>
		   	
			   	<div class="col-md-3">
	            	<label for="codebar">Codebar<span class="requis">*</span></label>
				</div>
                <div class="col-md-9">
	                <input type="number" min="0" id="codebar"
						name="codebar" value="<c:out value="${article.codebar}"/>"
						required>
                </div>
                
                <div class="col-md-3">
                	<label for="fournisseur">Fournisseur<span
						class="requis">*</span></label>
                </div>
                <div class="col-md-9">
					<select id="fournisseur" name="fournisseur" required>
						<option value="" selected></option>
						<c:forEach var="fournisseur" items="${fournisseurs}">
							<option value="${fournisseur.id}"> ${fournisseur.nom}</option>
						</c:forEach>
					</select>
                </div>

		   	</div>     

            <input type="submit" value="Nouveau"
				style="color: white; background: green; font-size: 1em; border-radius: 30px;"
				class="sansLabel" />
            <br />
        
        </form>

	</jsp:body>

</t:common>