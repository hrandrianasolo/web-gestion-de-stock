package com.gestiondestock.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondestock.model.Article;
import com.gestiondestock.service.ArticleService;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet(name = "ArticleController", urlPatterns = "/articles")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleService articleService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ArticleController() {
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

		if (articleService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();

			articleService = new ArticleService(em);
		}

		if (request.getParameter("delete") != null) {
			Integer id = Integer.parseInt(request.getParameter("delete"));
			if (id != null) {
				articleService.remove(id);
			}
		}
		
		List<Article> articles = new ArrayList<Article>();
		if (request.getParameter("filtreseuil") != null) {
			Integer filtreseuil = Integer.parseInt(request.getParameter("filtreseuil"));
			if (filtreseuil == 1) {
				articles = articleService.findAllByStockAndSeuil();
			}
		}
		else {
			articles = articleService.findAll();
		}

		request.setAttribute("articles", articles);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
