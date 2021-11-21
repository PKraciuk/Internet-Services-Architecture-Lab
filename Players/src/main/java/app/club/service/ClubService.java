package app.club.service;

import org.springframework.transaction.annotation.Transactional;
import app.club.entity.Club;
import app.club.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    @Autowired
    public ClubService(ClubRepository repository) {
        this.clubRepository = repository;
    }

    public List<Club> findAllClubs(){
        return clubRepository.findAll();
    }

    public Club create(Club club){
        return  clubRepository.save(club);
    }

    public Optional<Club> find(String name){
        return clubRepository.findById(name);
    }

    public void deleteClub(String id) {
        clubRepository.delete(find(id).orElseThrow());
    }

    @Transactional
    public void update(Club club) {
        clubRepository.save(club);
    }
}
