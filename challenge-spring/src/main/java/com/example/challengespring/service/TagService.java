package com.example.challengespring.service;

import com.example.challengespring.model.Tag;
import com.example.challengespring.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag getTag(Integer id) {
        return tagRepository.findById(id).get();
    }

    public void deleteTag(Integer id) {
        tagRepository.deleteById(id);
    }
}
