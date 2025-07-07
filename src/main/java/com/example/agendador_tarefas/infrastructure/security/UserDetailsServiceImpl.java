package com.example.agendador_tarefas.infrastructure.security;


import com.example.agendador_tarefas.business.dto.UsuarioDTO;
import com.example.agendador_tarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    UsuarioClient client;


    public UserDetails carregaDadosUsuario(String email, String token) {
        UsuarioDTO usuarioDTO = client.buscarUsuario(email, token);
        return User
                .withUsername(usuarioDTO.getEmail()) //define o usuario como email
                .password(usuarioDTO.getSenha()) //define a senha do usuario
                .build();
    }
}
