package app.configuration;


import app.player.entity.Player;
import app.player.service.PlayerService;
import app.club.entity.Club;
import app.club.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final PlayerService playerService;
    private final ClubService clubService;

    @Autowired
    public DataInitializer(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }
    @PostConstruct
    private synchronized void init(){
        Club FCWarszawa = Club.builder()
                .name("FC Warszawa")
                .CreationYear(1921)
                .MainColor("Black")
                .Budget(2.8)
                .build();
        Club GornikGdansk = Club.builder()
                .name("Gornik Gdansk")
                .CreationYear(2001)
                .MainColor("Red")
                .Budget(1.3)
                .build();
        Club PiastKatowice = Club.builder()
                .name("Piast Katowice")
                .CreationYear(1937)
                .MainColor("Yellow")
                .Budget(0.8)
                .build();
        clubService.create(FCWarszawa);
        clubService.create(GornikGdansk);
        clubService.create(PiastKatowice);

        Player player1 = Player.builder()
                .name("Piotr Nowak")
                .position("Striker")
                .age(22)
                .height(182)
                .value(830)
                .club(FCWarszawa)
                .build();
        Player player2 = Player.builder()
                .name("Marek Zieńczuk")
                .position("Midfielder")
                .age(28)
                .height(187)
                .value(1300)
                .club(GornikGdansk)
                .build();
        Player player3 = Player.builder()
                .name("Jan Kowalski")
                .position("Defender")
                .age(18)
                .height(162)
                .value(123000)
                .club(PiastKatowice)
                .build();
        playerService.create(player1);
        playerService.create(player2);
        playerService.create(player3);


    }
}