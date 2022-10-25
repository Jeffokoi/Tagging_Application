package com.example.Tagging.Repository;

import com.example.Tagging.Model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tags, Long> {

    @Transactional
    @Modifying
    @Query(
            value =
                    "insert into ancestor_table(ancestor) \n" +
                            "select (tag_id) from tags_demo\n" +
                            "where tag_id = ?", nativeQuery = true)
    void parentATag(@Param("tag_id") long id);

    @Transactional
    @Modifying
    @Query (
            value = "Insert into closure_table (ancestor, descendant)\n" +
                    "values (? , ?);" , nativeQuery = true)
    void childATag(@Param("ancestor") long ancestor,
                   @Param("descendant") long descendant);

    @Transactional
    @Modifying
    @Query(
            value = "SELECT d.*\n" +
                    "FROM tags_demo AS d\n" +
                    "JOIN closure_table AS t ON d.tag_id = t.descendant\n" +
                    "WHERE t.ancestor = ?" , nativeQuery = true
    )
    List<Tags> callChildTags(@Param("tag_id") long id );


    @Transactional
    @Modifying
    @Query(
            value = "SELECT d.*\n" +
                    "FROM tags_demo AS d\n" +
                    "JOIN closure_table AS t ON d.tag_id = t.ancestor\n" +
                    "WHERE t.descendant = ?" , nativeQuery = true
    )
    List<Tags> callParentTag(@Param("tag_id") long id );

    @Transactional
    @Modifying
    @Query(
            value = "SELECT d.*\n" +
                    "FROM tags_demo AS d\n" +
                    "JOIN ancestor_table AS t ON d.tag_id = t.ancestor " , nativeQuery = true
    )
    List<Tags> listParentTags();

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO tagjoin (Tagid ,taggedobjectid,objecttype) \n" +
                    "values (? , ? , ?);" , nativeQuery = true
    )
    void tagAnObject (@Param("tag_id") long id ,
                      @Param ("taggedobjectid") long TaggedObjectID,
                      @Param ("objecttype") String ObjectType
    );

}
