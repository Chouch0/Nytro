package nytro.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.AccountBean;

public class LogFilter implements Filter {
	
	//private final String[] inaccessibile = {"footer.html", "header.jsp"};
	private final String[] noRuolo = {"Login","LoginForm","RecuperaPassword","RegistrazioneForm","RegistrazioneUtente","VerificaUsername","contatti.jsp", "error.jsp", "index.jsp", "loginForm.jsp", "registrazioneForm.jsp", "image"};
	private final String[] adminRuolo = {"AggiornaProfilo","Catalogo","CatalogoCasaEditrice","ListaCaseEditrici","RegistrazioneCasaEditrice", "RimozioneAccount","ListaGiocatori","Logout","Profilo","VerificaUsername","Videogioco","contatti.jsp", "error.jsp", "index.jsp","profilo.jsp", "listaCadeEditrici.jsp", "listaGiocatori.jsp", "registrazioneCasaEditrice.jsp", "rimozioneAccount.jsp","videogioco.jsp", "image"};
	private final String[] giocatoreRuolo = {"AggiornaProfilo","AggiungiFriend","Catalogo","CatalogoCasaEditrice","Friendlist","GestoreCarrello","Libreria","ListaCaseEditrici","Logout","Profilo","RimuoviFriend","VerificaUsername","Videogioco","contatti.jsp", "error.jsp", "index.jsp","profilo.jsp", "carrello.jsp", "catalogo.jsp", "catalogoCasaEditrice.jsp", "friendlist.jsp", "libreria.jsp", "listaCadeEditrici.jsp", "videogioco.jsp", "image"};
	private final String[] aziendaRuolo = {"AggiornaProfilo","Logout","Profilo","Pubblicazioni","VerificaUsername","Videogioco","contatti.jsp", "error.jsp", "index.jsp", "profilo.jsp", "catalogoCasaEditrice.jsp", "pubblicazioni.jsp", "videogioco.jsp", "image"};
    
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String dest = ((HttpServletRequest) request).getRequestURI();
		System.out.println("Destinazione (filtro):" + dest);
		AccountBean account = (AccountBean) ((HttpServletRequest) request).getSession().getAttribute("account");
		
		if(dest.contains("/css/") || dest.contains("/js/")|| dest.contains("/img/")) {
			chain.doFilter(request, response);	
			return ;
		}
			
		
		if(account==null || account.getUsername()==null) {
			System.out.println("Account nullo");
			for(String x : noRuolo) {
				System.out.println(dest +" contiene "+x+" ?");
				if(dest.contains(x)){
					System.out.println("\tSì, faccio il chain.doFilter(.,.)");
					chain.doFilter(request, response);	
					return ;
				}
			}
		} else {
			System.out.println("Account loggato");
			int ruolo = account.getRuolo();
			if(ruolo==0) {
				System.out.println("Ruolo: "+ruolo);
				for(String x : adminRuolo) {
					System.out.println(dest +" contiene "+x+" ?");
					if(dest.contains(x)){
						System.out.println("\tSì, faccio il chain.doFilter(.,.)");
						chain.doFilter(request, response);	
						return ;
					}
				}
			} else if(ruolo==1) {
				System.out.println("Ruolo: "+ruolo);
				for(String x : giocatoreRuolo) {
					System.out.println(dest +" contiene "+x+" ?");
					if(dest.contains(x)){
						System.out.println("\tSì, faccio il chain.doFilter(.,.)");
						chain.doFilter(request, response);	
						return ;
					}
				}
			} else if(ruolo==2) {
				System.out.println("Ruolo: "+ruolo);
				for(String x : aziendaRuolo) {
					System.out.println(dest +" contiene "+x+" ?");
					if(dest.contains(x)) {
						System.out.println("\tSì, faccio il chain.doFilter(.,.)");
						chain.doFilter(request, response);	
						return ;
					}
				}
			}
		}
		
		System.out.println("Destinazione (filtro): rindirizzamento ad index.jsp");
		((HttpServletResponse) response).sendRedirect("/NYTRO/jsp/index.jsp");
		//((HttpServletRequest) request).getRequestDispatcher("jsp/index.jsp").forward((HttpServletRequest) request, (HttpServletResponse) response);
		return ;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}







