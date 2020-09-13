<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<title>Caisse</title>
</head>
<body>
	<div id="body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3" style="padding-left: 0;">
					<nav id="sideNav"
						style="position: fixed; height: 100%; width: 24%; background: #e8e8e8; text-align: center; padding: 2em 1.5em;">
						<h1
							style="background: #117a8b; color: white; font-size: 1.5em; border-radius: 30px; padding: 1em 0;">Caisse</h1>
						<hr style="margin: 1em 0 6em 0;">
						
						<p
							style="background: #575e5f; font-size: 1em; border-radius: 30px; padding: 1em 0;">
							<a style="color: white;" href="/gestiondestock/caisse/achat">Achat en cours</a>
						</p>
						<p
							style="background: #575e5f; font-size: 1em; border-radius: 30px; padding: 1em 0;">
							<a style="color: white;" href="/gestiondestock/caisse/achat?totalachat=1">Total</a>
						</p>

					</nav>
				</div>
				<div class="col-md-9">
					<div id="mainContent" style="padding: 2em 0;">
						<jsp:doBody />
					</div>
				</div>


			</div>
		</div>
	</div>

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

</body>
</html>