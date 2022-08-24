package ru.company;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.company.classes.SanitaryWare;
import ru.company.classes.User;
import ru.company.data.SanitaryWareRepository;
import ru.company.data.UserRepository;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(SanitaryWareRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new SanitaryWare("8723", "Faucet WISENT 22243", SanitaryWare.Type.FAUCET));
                repo.save(new SanitaryWare("3948", "Bath Water 2*1.3 m", SanitaryWare.Type.BATH));
                repo.save(new SanitaryWare("3490", "Sink 0.60m Cersanit GRAND 60", SanitaryWare.Type.SINK));
                repo.save(new SanitaryWare("3242", "Toilet bowl with installation GEBERIT Acanto 50", SanitaryWare.Type.TOILET));
                repo.save(new SanitaryWare("8743", "Faucet WaterFlow2", SanitaryWare.Type.FAUCET));
                repo.save(new SanitaryWare("3978", "Bath Comfort 2*0.9 m", SanitaryWare.Type.BATH));
                repo.save(new SanitaryWare("3400", "Sink 0.60m Fiowjl 60", SanitaryWare.Type.SINK));
                repo.save(new SanitaryWare("3212", "Toilet IUds 13", SanitaryWare.Type.TOILET));

                userRepo.save(new User("habuma", encoder.encode("password"),
                        "Craig Walls", "123 North Street", "Cross Roads", "TX",
                        "76227", "123-123-1234"));
            }
        };
    }
}
