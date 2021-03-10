package me.zitzmanncedric.repository;

import me.zitzmanncedric.model.Speaker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("speakerRepository")
public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {

    @Override
    public List<Speaker> findAll() {
        List<Speaker> speakers = new ArrayList<>();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Cedric");
        speaker.setLastName("Zitzmann");

        speakers.add(speaker);
        return speakers;
    }

}
