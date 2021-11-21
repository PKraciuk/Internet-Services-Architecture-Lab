package app.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.club.entity.Club;


@org.springframework.stereotype.Repository
public interface ClubRepository extends JpaRepository<Club,String> {



}
