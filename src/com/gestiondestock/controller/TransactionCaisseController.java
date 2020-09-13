package com.gestiondestock.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gestiondestock.model.Article;
import com.gestiondestock.model.ArticleVendu;
import com.gestiondestock.model.TransactionCaisse;
import com.gestiondestock.service.ArticleService;
import com.gestiondestock.service.TransactionCaisseService;

/**
 * Servlet implementation class TransactionCaisseController
 */
@WebServlet(name = "TransactionCaisseController", urlPatterns = "/caisse/achat")
public class TransactionCaisseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TransactionCaisseService transactionCaisseService;
	private ArticleService articleService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public TransactionCaisseController() {
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

		if (transactionCaisseService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();

			transactionCaisseService = new TransactionCaisseService(em);
		}

		TransactionCaisse transactionCaisse = (TransactionCaisse) getServletContext()
				.getAttribute("currentTransactionCaisse");
		if (transactionCaisse == null) {
			transactionCaisse = new TransactionCaisse();
			transactionCaisse.setArticleVendus(new ArrayList<ArticleVendu>());
		}

		if (request.getParameter("deleteone") != null) {
			Integer id = Integer.parseInt(request.getParameter("deleteone"));
			if (id != null) {
				for (ArticleVendu tmp : transactionCaisse.getArticleVendus()) {
					if (tmp.getArticle().getId() == id) {
						tmp.setNombre(tmp.getNombre() - 1);
					}
				}
			}
		}

		else if (request.getParameter("deleteall") != null) {
			Integer id = Integer.parseInt(request.getParameter("deleteall"));
			if (id != null) {
				ArticleVendu toRemove = new ArticleVendu();
				for (ArticleVendu tmp : transactionCaisse.getArticleVendus()) {
					if (tmp.getArticle().getId() == id) {
						toRemove = tmp;
						break;
					}
				}

				transactionCaisse.getArticleVendus().remove(toRemove);
			}
		} else if (request.getParameter("totalachat") != null && !transactionCaisse.getArticleVendus().isEmpty()) {
			transactionCaisseService.save(transactionCaisse);
			for (ArticleVendu tmp : transactionCaisse.getArticleVendus()) {
				Article reference = new Article(tmp.getArticle().getId(), tmp.getArticle().getNom(),
						tmp.getArticle().getStock(), tmp.getArticle().getSeuil(), tmp.getArticle().getCodebar(),
						tmp.getArticle().getPrix(), tmp.getArticle().getIstVenduEnDetail(),
						tmp.getArticle().getFournisseur());

				reference.setStock(reference.getStock() - tmp.getNombre());
				articleService.update(articleService.findById(tmp.getArticle().getId()), reference);
			}
			transactionCaisse = new TransactionCaisse();
			transactionCaisse.setArticleVendus(new ArrayList<ArticleVendu>());
		}

		getServletContext().setAttribute("currentTransactionCaisse", transactionCaisse);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/caisseachat.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (transactionCaisseService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();

			transactionCaisseService = new TransactionCaisseService(em);
		}
		if (articleService == null) {
			EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
			EntityManager em = emf.createEntityManager();

			articleService = new ArticleService(em);
		}

		TransactionCaisse transactionCaisse = (TransactionCaisse) getServletContext()
				.getAttribute("currentTransactionCaisse");
		if (transactionCaisse == null) {
			transactionCaisse = new TransactionCaisse();
			transactionCaisse.setArticleVendus(new ArrayList<ArticleVendu>());
		}

		if (request.getParameter("codebar") != null) {

			ArticleVendu articleVendu = new ArticleVendu();
			Optional<Article> articleTmp = articleService
					.findByCodebar(Integer.parseInt(request.getParameter("codebar")));
			if (articleTmp.isPresent()) {
				articleVendu.setArticle(articleTmp.get());
			}

			if (transactionCaisse.getArticleVendus().isEmpty() && articleTmp.isPresent()) {
				articleVendu.setNombre(1);
				transactionCaisse.getArticleVendus().add(articleVendu);
			}

			else if (articleTmp.isPresent()) {
				Boolean isAddArticleComplete = false;

				for (ArticleVendu tmp : transactionCaisse.getArticleVendus()) {
					if (tmp.getArticle().getId() == articleVendu.getArticle().getId()
							|| tmp.getArticle().getCodebar() == articleVendu.getArticle().getCodebar()) {
						tmp.setNombre(tmp.getNombre() + 1);
						isAddArticleComplete = true;
					}
				}

				if (!isAddArticleComplete) {
					articleVendu.setNombre(1);
					transactionCaisse.getArticleVendus().add(articleVendu);
				}
			}

			else {
				getServletContext().setAttribute("erreurcodebar", "Le codebar ne correspond a aucun article.");
			}

		} else if(request.getParameter("codebarretour") != null) {
			Integer codebar = Integer.parseInt(request.getParameter("codebarretour"));
			Article article = articleService.findByCodebar(codebar).get();
			article.setStock(article.getStock()+1);
			articleService.save(article);
		}

		request.setAttribute("currentTransactionCaisse", transactionCaisse);

		doGet(request, response);
	}

}
