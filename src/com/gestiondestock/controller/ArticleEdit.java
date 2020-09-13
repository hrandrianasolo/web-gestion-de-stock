package com.gestiondestock.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondestock.forms.ArticleForm;
import com.gestiondestock.model.Article;
import com.gestiondestock.model.Fournisseur;
import com.gestiondestock.service.ArticleService;
import com.gestiondestock.service.FournisseurService;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet(name = "ArticleEdit", urlPatterns = "/editarticle")
public class ArticleEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleService articleService;
	private FournisseurService fournisseurService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ArticleEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (articleService == null || fournisseurService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();
			
			if(articleService == null) {
				articleService = new ArticleService(em);
			}
			
			if(fournisseurService == null) {
				fournisseurService = new FournisseurService(em);
			}
		}

		Article article = new Article();

		if (request.getParameter("edit") != null) {
			Integer id = Integer.parseInt(request.getParameter("edit"));
			if (id != null) {
				article = articleService.findById(id);
			}
		}

		request.setAttribute("article", article);

		List<Fournisseur> fournisseurs = fournisseurService.findAll();
		request.setAttribute("fournisseurs", fournisseurs);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/editarticle.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (articleService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();

			articleService = new ArticleService(em);
		}

		ArticleForm articleForm = new ArticleForm(fournisseurService);

		Article tmp = articleForm.nouveauArticle(request);
		Article article = articleService.findById(tmp.getId());

		articleService.update(article, tmp);

		response.sendRedirect(request.getContextPath() + "/articles");
	}

}
