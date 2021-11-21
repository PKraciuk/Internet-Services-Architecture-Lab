package app.player.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import app.player.dto.*;
import app.club.entity.Club;
import app.player.entity.Player;
import app.club.service.ClubService;
import app.player.service.PlayerService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/clubs/{name}/players")
public class ClubPlayerController {


    private PlayerService playerService;
    private ClubService clubService;

    @Autowired
    public ClubPlayerController(PlayerService playerService, ClubService clubService ) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<GetPlayersResponse> getPlayers(@PathVariable("name") String name) {
        System.out.println("AAAA");
        Optional<Club> club = clubService.find(name);
        return club.map(value -> ResponseEntity.ok(GetPlayersResponse.entityToDtoMapper().apply(playerService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable("name") String name,
                                                       @PathVariable("id") long id) {
        return playerService.find(id, name)
                .map(value -> ResponseEntity.ok(GetPlayerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
