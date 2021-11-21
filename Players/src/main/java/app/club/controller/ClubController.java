package app.club.controller;

import app.club.dto.CreateClubRequest;
import app.club.entity.Club;
import app.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Optional;

@RestController
@RequestMapping("api/clubs")
public class ClubController {

    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<Void> createClub(@RequestBody CreateClubRequest request, UriComponentsBuilder builder) {
        Club club = CreateClubRequest
                .dtoToEntityMapper()
                .apply(request);
        club = clubService.create(club);
        return ResponseEntity.created(builder.pathSegment("api", "clubs", "{id}")
                .buildAndExpand(club.getName()).toUri()).build();
    }
    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteClub(@PathVariable("name") String id)
    {
        Optional<Club> club = clubService.find(id);
        if(club.isPresent())
        {
            clubService.deleteClub(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}