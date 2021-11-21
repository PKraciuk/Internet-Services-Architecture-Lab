package app.configuration;

import app.clubs.entity.Club;
import app.clubs.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final ClubService clubService;

    @Autowired
    public DataInitializer(ClubService clubService) {
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

    }
}
