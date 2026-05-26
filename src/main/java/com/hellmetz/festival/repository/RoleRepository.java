package com.hellmetz.festival.repository;

import com.hellmetz.festival.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// JpaRepository<T, ID> : T = votre entité, ID = type de sa clé primaire
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Récupère un rôle spécifique par son identifiant(autopar spring)
    //Associe une liste de permissions à un rôle (table role_permission) (auto par sprig)
    //Récupère la liste de tous les rôles disponibles (auto par spring)
}

