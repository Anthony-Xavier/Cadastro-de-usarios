package xavier.app.Login.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import xavier.app.Login.model.User;
import xavier.app.Login.repository.UserRepository;



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
    public String dashboard(){
        return "index";
    }

    @PostMapping("/logar")
    public String loginUsuario(@ModelAttribute User user, Model model) {
        User usuarioLogado = this.userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (usuarioLogado != null) {
            return "redirect:/"; // Redireciona para index.html
        } else {
            model.addAttribute("error", "Email ou senha inválidos"); // CORRETO
            return "login"; // volta pra tela de login com a mensagem
        }
    }

}
