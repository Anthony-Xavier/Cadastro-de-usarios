package xavier.app.Login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xavier.app.Login.model.User;
import xavier.app.Login.repository.UserRepository;
import xavier.app.Login.service.CookieService;

import java.io.UnsupportedEncodingException;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(org.springframework.ui.Model model) {
        model.addAttribute("user", new User());
        return "login";
    }


    @GetMapping("/cadastro")
    public String cadastro(org.springframework.ui.Model model) {
        model.addAttribute("user", new User());
        return "cadastro";
    }


    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastroUsuario(@Valid User user, BindingResult result){

        if(result.hasErrors()){
            return "redirect:/cadastro";
        }

        userRepository.save(user);
        
        return "redirect:/login";
    }

    @GetMapping("/")
    public String dashboard(Model model, jakarta.servlet.http.HttpServletRequest request) throws UnsupportedEncodingException {
        // Lê o cookie "userName"
        String nomeUsuario = CookieService.getCookie(request, "userName");

        // Adiciona ao modelo para Thymeleaf
        model.addAttribute("name", nomeUsuario != null ? nomeUsuario : "Visitante");

        return "index"; // renderiza index.html
    }


    @PostMapping("/logar")
    public String loginUsuario(@ModelAttribute User user, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
        User usuarioLogado = this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (usuarioLogado != null) {
            CookieService.setCookie(response,"userId", String.valueOf(usuarioLogado.getId()),10000);
            CookieService.setCookie(response,"userName", String.valueOf(usuarioLogado.getName()),10000);

            return "redirect:/"; // Redireciona para index.html
        } else {
            model.addAttribute("error", "Email ou senha inválidos"); // CORRETO
            return "login"; // volta pra tela de login com a mensagem
        }
    }

}
