package br.com.nicoservices.contatospersistence.controls;

import br.com.nicoservices.contatospersistence.models.usuario.InvalidUserException;
import br.com.nicoservices.contatospersistence.models.usuario.Usuario;
import br.com.nicoservices.contatospersistence.models.usuario.UsuarioRepository;
import br.com.nicoservices.contatospersistence.util.ContatosPersistenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = {})
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(){
        return "login";
    }

    @CrossOrigin(origins = "*", allowedHeaders = {})
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView logar(Usuario user){
        try{
            if (validarLogin(user)){
                ModelAndView mv = new ModelAndView("index");
                mv.addObject("usuario", user);
                return mv;
            }
        } catch(Exception e){
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("excecao", e.getMessage());
            return mv;
        }
        return new ModelAndView("login");
    }

    public boolean validarLogin(Usuario userRequestBody){
        Usuario userRepository = repository.findByNome(userRequestBody.getNome());
        if (userRepository == null) throw new InvalidUserException("Nome de usuário não cadastrado!");
        else {
            String MD5Senha = ContatosPersistenceUtil.calcularMD5(userRequestBody.getSenha());
            System.out.println(MD5Senha);
            if (MD5Senha == null) throw new RuntimeException();
            else if (MD5Senha.equals(userRepository.getSenha())) return true;
            else throw new InvalidUserException("Senha incorreta!");
        }
    }
}
