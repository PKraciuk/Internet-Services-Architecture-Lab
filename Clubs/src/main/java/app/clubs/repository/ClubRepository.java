package app.clubs.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import app.clubs.entity.Club;


@org.springframework.stereotype.Repository
public interface ClubRepository extends JpaRepository<Club,String> {



}
