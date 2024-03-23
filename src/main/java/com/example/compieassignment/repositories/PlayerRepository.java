package com.example.compieassignment.repositories;

import com.example.compieassignment.dto.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String> {}
