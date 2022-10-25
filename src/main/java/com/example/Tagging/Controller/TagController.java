package com.example.Tagging.Controller;


import com.example.Tagging.Service.TagServiceImpl;
import com.example.Tagging.Model.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController

public class TagController {

    @Autowired
    TagServiceImpl tagServiceImpl;


    @PostMapping("/addtags")
    public ResponseEntity<String> addTags(@RequestBody Tags tags) {
        tagServiceImpl.addTags(tags);
        ResponseEntity<String> responseEntity =new ResponseEntity<>("Successfully Added Tag!" , HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("/findall")
    public List<Tags> getAllTags(){
        return tagServiceImpl.findAllTags();
    }

    @GetMapping("/findallparent")
    public List<Tags> getAllParent(){return tagServiceImpl.listParentTags();}

    @GetMapping("/findbyid/{id}")
    public Tags getTagsUsingId(@PathVariable Long id) {
        return tagServiceImpl.findAllTagsByID(id);
    }

    @DeleteMapping("/delete")
    public void delete(){
        tagServiceImpl.deleteAllData();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) { tagServiceImpl.deleteTagsByID(id);}

    @PutMapping("/updatetag/{id}")
    public ResponseEntity<String> updateTags (@RequestBody Tags tags,@PathVariable Long id){
        tagServiceImpl.updateTags(id,tags);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Updated Successfully!",HttpStatus.CREATED);
        return responseEntity;

    }
    @PutMapping("/parentatag/{id}")
            public void parentATag(@PathVariable long id){
        tagServiceImpl.parentATag(id);
    }

    @PostMapping ("/childatag")
    public void childATag(@RequestParam long ancestor ,
                          @RequestParam long descendant )
    {tagServiceImpl.childATag(ancestor ,descendant);}

    @GetMapping("/callchildtag/{id}")
    public List<Tags> callChildTags(@PathVariable long id){return tagServiceImpl.callChildTag(id);}

    @GetMapping("/callParentTag/{id}")
    public List<Tags> callParentTags(@PathVariable long id){ return tagServiceImpl.callParentTag(id);}

    @PutMapping("/tagObject")
    public void tagAnObject (@RequestParam long id ,
                             @RequestParam long TaggedObjectID,
                             @RequestParam String ObjectType)
    {tagServiceImpl.tagAnObject(id, TaggedObjectID, ObjectType);}

    @GetMapping("/Clientliststudents")
    public List<Object> getAllStudents(){
        String url = "http://localhost:8080/smsrest/findallstudents";
        RestTemplate restTemplate = new RestTemplate();

        Object[] stds = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(stds);
    }
    @RequestMapping(value = "/clientcreatestudent" , method = RequestMethod.POST)
    public String addStudent(@RequestBody Object object){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<Object>(object,headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(
                "http://localhost:8080/smsrest/addstudents", HttpMethod.POST, entity, String.class).getBody();
    }

    }


