package com.songcheShare.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.songcheShare.project.entities.Song;
import com.songcheShare.project.services.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(SongService songService){
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Song>> typeReference = new TypeReference<List<Song>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/songs.json");
            try{
                List<Song> songs = objectMapper.readValue(inputStream, typeReference);
                songService.save(songs);
                System.out.println("Songs are saved");
            }catch (IOException e){
                System.out.println("Unable to save songs: " + e.getMessage());
            }
        };
    }

}
