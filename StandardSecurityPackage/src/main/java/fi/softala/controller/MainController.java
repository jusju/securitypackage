package fi.softala.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fi.softala.bean.User;
import fi.softala.dao.UserDao;

/**
 * Sovelluksen controller.
 * 
 * @author Inka Haltiapuu, Jukka Juslin
 *
 */

@Controller
public class MainController {
	
	@Inject
	private UserDao dao;

	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Spring Security - autentikointi ja autorisointidemo");
		model.setViewName("hello");
		return model;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView userPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Käyttäjä-sivu");
		model.addObject("message",
				"Tälle sivulle pääsee vain ROLE_USER -roolilla.");
		model.setViewName("user");
		return model;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Admin-sivu");
		model.addObject("message",
				"Tälle sivulle pääsee vain ROLE_ADMIN -roolilla.");
		model.setViewName("admin");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Virheellinen käyttäjänimi tai salasana.");
		}
		if (logout != null) {
			model.addObject("msg", "Olet kirjautunut ulos.");
		}
		model.addObject("title", "Sisäänkirjautuminen");
		model.setViewName("login");
		return model;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView showRegistration() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		model.addObject("title", "Rekisteröityminen");
		model.addObject("message", "Rekisteröidy antamalla käyttäjänimi ja salasana.");
		model.setViewName("registration");
		return model;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String saveUser(@Valid User user,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		user.setRole("ROLE_USER");
		getDao().saveUser(user);
		model.addAttribute("success", " Rekisteröinti tehty tiedoilla: " + user.toString());
		return "success";
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public String forgotPassword(@Valid User user,
			BindingResult result, ModelMap model) {

		user.setRole("ROLE_USER");
		getDao().saveUser(user);
		model.addAttribute("success", " Rekisteröinti tehty tiedoilla: " + user.toString());
		return "forgotpassword";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied() {

		ModelAndView model = new ModelAndView();
		// printataan konsolille sisäänkirjautuneen käyttäjän tietoja
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("title", "403 - Ei oikeuksia sivulle");
			model.addObject("message", "403 - Ei oikeuksia sivulle.");
		}
		model.setViewName("403");
		return model;
	}
}