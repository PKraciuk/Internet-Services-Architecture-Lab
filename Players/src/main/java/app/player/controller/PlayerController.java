package app.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import app.player.entity.Player;
import app.club.service.ClubService;
import app.player.service.PlayerService;
import app.player.dto.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    private PlayerService playerService;
    private ClubService clubService;

    @Autowired
    public PlayerController(PlayerService playerService, ClubService clubService ) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<GetPlayersResponse> getPlayers() {
        List<Player> all = playerService.findAll();
        Function<Collection<Player>, GetPlayersResponse> mapper = GetPlayersResponse.entityToDtoMapper();
        GetPlayersResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<GetPlayerResponse> getPlayer(@PathVariable("id") long id) {
        return  playerService.find(id)
                .map(value -> ResponseEntity.ok(GetPlayerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(@RequestBody CreatePlayerRequest request, UriComponentsBuilder builder) {
        Player player = CreatePlayerRequest
                .dtoToEntityMapper(name -> clubService.find(name).orElseThrow())
                .apply(request);
        player = playerService.create(player);
        return ResponseEntity.created(builder.pathSegment("api", "players", "{id}")
                .buildAndExpand(player.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePlayer(@RequestBody UpdatePlayerRequest request, @PathVariable("id") long id) {
        Optional<Player> player =playerService.find(id);
        if (player.isPresent()) {
            UpdatePlayerRequest.dtoToEntityUpdater().apply(player.get(), request);
            playerService.update(player.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable("id") Long id) {
        Optional<Player> player = playerService.find(id);
        if (player.isPresent()) {
            playerService.deletePlayer(id);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }






}
