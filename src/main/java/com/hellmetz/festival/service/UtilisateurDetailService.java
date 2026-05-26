package com.hellmetz.festival.service;

import com.hellmetz.festival.model.Utilisateur;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.hellmetz.festival.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class UtilisateurDetailService implements UserDetailsService{

    @Autowired  // Spring injecte automatiquement le Repository
    private UtilisateurRepository utilisateurRepository;

    @Autowired  // Spring injecte automatiquement le Repository
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        List<SimpleGrantedAuthority> authorities = utilisateur.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getCodeRole()))
                .collect(Collectors.toList());

        return new User(
                utilisateur.getIdentifiant(),
                utilisateur.getMotDePasse(),
                authorities
        );
    }

    //recup un utilisateur
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    // recup un utilisateur par son id
    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    //cree un utilisateur
    public void creerUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        utilisateur.setActif(true);
        utilisateur.setDateCreation(new Date());
        utilisateurRepository.save(utilisateur);
    }

    // update et insert
    public void save(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    // supp
    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public boolean isAdmin(Utilisateur utilisateur) {
        return utilisateur.getRoles().stream()
                .anyMatch(role -> role.getCodeRole().equals("ADMIN"));
    }

    public List<String> getCodesRoles(Utilisateur utilisateur) {
        return utilisateur.getRoles().stream()
                .map(role -> role.getCodeRole())
                .collect(Collectors.toList());
    }

    public List<String> getCodesPermissions(Utilisateur utilisateur) {
        return utilisateur.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(permission -> permission.getCodePermission())
                .distinct()
                .collect(Collectors.toList());
    }


}
