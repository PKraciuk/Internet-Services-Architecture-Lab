package app.player.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.club.entity.Club;
import app.player.entity.Player;
import app.club.repository.ClubRepository;
import app.player.repository.PlayerRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    @Autowired
    public PlayerService(PlayerRepository repository, ClubRepository clubRepository) {
        this.playerRepository = repository;
        this.clubRepository=clubRepository;
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public List<Player> findAll(Club club){
        return playerRepository.findAllByClub(club);
    }

    public Player create(Player player){
        return playerRepository.save(player);
    }

    public Optional<Player> find(Long id) {
        return playerRepository.findById(id);
    }

    public Optional<Player> find(Long id, String name) {
        Optional<Club> club = clubRepository.findById(name);
        if(club.isPresent()){
            return playerRepository.findByIdAndClub(id,club.get());
        }
        else
        {
            return Optional.empty();
        }

    }

    @Transactional
    public void update(Player player) {
        playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.delete(find(id).orElseThrow());
    }





}