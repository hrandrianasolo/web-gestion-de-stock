<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:common>

	<jsp:body>
        <h1
			style="padding: 1em 0; margin: 0 7em 2.5em 7em; background: #117a8b; color: white; font-size: 1.5em; border-radius: 30px; text-align: center">
			Modifier article
		</h1>
    	
    	<form method="post" action="editarticle" style="margin-left: 30%">
			
			<input type="hidden" id="id" name="id"
				value="<c:out value="${article.id}" />" />
			
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
	               <input type="number" min="0" id="stock" name="stock"
						value="<c:out value="${article.stock}"/>" required>
                </div>

                <div class="col-md-3">
                	<label for="seuil">Seuil<span class="requis">*</span></label>
                </div>
                <div class="col-md-9">
	               <input type="number" min="0" id="seuil" name="seuil"
						value="<c:out value="${article.seuil}"/>" required>
                </div>
                
                <div class="col-md-3">
	                <label for="prix">Prix<span class="requis">*</span></label>
    			</div>
                <div class="col-md-9">
	                <input type="number" min="0" step="any" id="prix"
						name="prix" value="<c:out value="${article.seuil}"/>" required>
                </div>
                
                <div class="col-md-3">
					<label for="istVenduEnDetail">Vendu en detail<span
						class="requis">*</span></label>
                </div>
                <div class="col-md-9">
	                <input type="number" max="1" min="0"
						id="istVenduEnDetail" name="istVenduEnDetail"
						value="<c:out value="${article.istVenduEnDetail ? 1 : 0}"/>"
						required> 0 : Non | 1 : Oui
                </div>
				
				<div class="col-md-3">
                	<label for="codebar">Codebar<span
						class="requis">*</span></label>
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
						<option value="${article.fournisseur.id}" selected>${article.fournisseur.nom}</option>
						<c:forEach var="fournisseur" items="${fournisseurs}">
							<c:if test="${fournisseur.id != article.fournisseur.id}">
									<option value="${fournisseur.id}"  > ${fournisseur.nom}</option>	
							</c:if>
						</c:forEach>
					</select>
                </div>
                
			</div>
		   	     
            <input type="submit" value="Modifier"
				style="padding: 0.2em 0.5em; color: white; background: #d6a028; border-radius: 30px;"
				class="sansLabel" />
            <br />

		</form>

	</jsp:body>

</t:common>