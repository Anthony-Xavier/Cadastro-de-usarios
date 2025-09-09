package xavier.app.Login.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xavier.app.Login.model.User;
import xavier.app.Login.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login(){
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
}
