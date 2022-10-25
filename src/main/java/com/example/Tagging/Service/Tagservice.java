package com.example.Tagging.Service;


import com.example.Tagging.Model.Tags;

import java.util.List;

public interface Tagservice {
    List<Tags> findAllTags();
    Tags findAllTagsByID(long id);
    void addTags(Tags tags);

    void deleteAllData();

    void deleteTagsByID(long id);

    Tags updateTags(long id,Tags tags);


    void parentATag(long id);


    void childATag(long ancestor , long descendant);

    List<Tags> callChildTag(long id);

    List<Tags> callParentTag(long id);

    List<Tags> listParentTags();

    void tagAnObject (long id ,long TaggedObjectID , String ObjectType );
}
