package me.zitzmanncedric.repository;

import me.zitzmanncedric.model.Speaker;

import java.util.List;

public interface SpeakerRepository {
    List<Speaker> findAll();
}
