package app.clubs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import app.clubs.dto.*;
import app.clubs.entity.Club;
import app.clubs.service.ClubService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/clubs")
public class ClubController {
    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService ) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<GetClubsResponse> getClubs() {
        List<Club> all = clubService.findAllClubs();
        Function<Collection<Club>, GetClubsResponse> mapper = GetClubsResponse.entityToDtoMapper();
        GetClubsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetClubResponse> getClub(@PathVariable("name") String id) {
        return  clubService.find(id)
                .map(value -> ResponseEntity.ok(GetClubResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createClub(@RequestBody CreateClubRequest request, UriComponentsBuilder builder) {
        Club club = CreateClubRequest
                .dtoToEntityMapper()
                .apply(request);
        club = clubService.create(club);
        return ResponseEntity.created(builder.pathSegment("api", "characters", "{id}")
                .buildAndExpand(club.getName()).toUri()).build();

    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateClub(@RequestBody UpdateClubRequest request, @PathVariable("name") String name) {
        Optional<Club> club =clubService.find(name);
        if (club.isPresent()) {
            UpdateClubRequest.dtoToEntityUpdater().apply(club.get(), request);
            clubService.update(club.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteClub(@PathVariable("name") String name) {
        Optional<Club> club = clubService.find(name);
        if (club.isPresent()) {
            clubService.deleteClub(name);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
