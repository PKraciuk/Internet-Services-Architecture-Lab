package app.player.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.club.entity.Club;
import app.player.entity.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    List<Player> findAllByClub(Club club);

    Optional<Player> findByIdAndClub(Long id, Club club);


}