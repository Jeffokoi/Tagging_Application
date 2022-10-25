package com.example.Tagging.Service;

import com.example.Tagging.Repository.TagRepository;
import com.example.Tagging.Model.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements Tagservice {

    @Autowired
    TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tags> findAllTags() {
        return (List<Tags>) tagRepository.findAll();
    }

    @Override
    public Tags findAllTagsByID(long id) {
        Optional<Tags> tag = tagRepository.findById(id);
        if (tag.isPresent())
            return tag.get();
        else
            return null;
    }

    @Override
    public void addTags(Tags tags) {
        tagRepository.save(tags);
    }


    @Override
    public void deleteAllData() {
        tagRepository.deleteAll();

    }

    @Override
    public void deleteTagsByID(long id) {
        tagRepository.deleteAllById(Collections.singleton(id));
    }

    @Override
    public Tags updateTags(long id, Tags tags) {
        return tagRepository.save(tags);

    }
    @Override
    public void parentATag (long id) {
        tagRepository.parentATag(id);
    }


    @Override
    public void childATag(long descendant ,long ancestor) { tagRepository.childATag( descendant, ancestor);}

    @Override
    public List<Tags> callChildTag(long id) {return tagRepository.callChildTags(id);}

    @Override
    public List<Tags> callParentTag(long id) {
        return tagRepository.callParentTag(id);
    }

    @Override
    public List<Tags> listParentTags() {
        return (List<Tags>) tagRepository.listParentTags();}
    @Override
    public void tagAnObject(long id, long TaggedObjectID, String ObjectType) {
        tagRepository.tagAnObject(id, TaggedObjectID, ObjectType);}


}


