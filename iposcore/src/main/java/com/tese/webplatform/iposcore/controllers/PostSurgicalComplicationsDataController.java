package com.tese.webplatform.iposcore.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.tese.webplatform.iposcore.models.*;
import com.tese.webplatform.iposcore.repositories.*;
import com.tese.webplatform.iposcore.errors.*;

@RestController
@CrossOrigin
public class PostSurgicalComplicationsDataController{

    @Autowired
    public PostSurgicalComplicationsDataRepository postSurgicalComplicationsData;

    public PostSurgicalComplicationsDataController(PostSurgicalComplicationsDataRepository newPostSurgicalComplicationsData){
        this.postSurgicalComplicationsData = newPostSurgicalComplicationsData;
    }

    @GetMapping("/postSurgicalComplicationsDataInfo")
    public List<PostSurgicalComplicationsData> getPostSurgicalComplicationsData() {
        return postSurgicalComplicationsData.findAll();
    }

    @GetMapping("/postSurgicalComplicationsDataInfo/{id}")
    public PostSurgicalComplicationsData findPostSurgicalComplicationsDataById(@PathVariable Long id) {
        return postSurgicalComplicationsData.findById(id).orElseThrow(PostSurgicalComplicationsDataNotFoundException::new);
    }

    @GetMapping("/postSurgicalComplicationsDataInfo/post_surgical_complication/{post_surgical_complication}")
    public List<PostSurgicalComplicationsData> findByPostSurgicalComplication(@PathVariable int post_surgical_complication) {
        List<PostSurgicalComplicationsData> temp = postSurgicalComplicationsData.findByPostSurgicalComplication(post_surgical_complication);
        if(temp.isEmpty())
            throw new PostSurgicalComplicationsDataNotFoundException();
        return temp;
    }
    
    @PostMapping("/postSurgicalComplicationsDataInfo")
    public PostSurgicalComplicationsData addPostSurgicalComplicationsData(@RequestBody PostSurgicalComplicationsData newPostSurgicalComplicationsData) {
        return postSurgicalComplicationsData.save(newPostSurgicalComplicationsData);
    }
    
    @PutMapping("/postSurgicalComplicationsDataInfo/{id}")
    public PostSurgicalComplicationsData updatePostSurgicalComplicationsDataInfo(@PathVariable Long id, @RequestBody PostSurgicalComplicationsData newPostSurgicalComplicationsData) {
        PostSurgicalComplicationsData postSurgicalComplicationsDataInfoUpdate = postSurgicalComplicationsData.findById(id).orElseThrow(PostSurgicalComplicationsDataNotFoundException::new);
     
        postSurgicalComplicationsDataInfoUpdate.setId(newPostSurgicalComplicationsData.getID());
        postSurgicalComplicationsDataInfoUpdate.setPostSurgicalComplication(newPostSurgicalComplicationsData.getPostSurgicalComplication());
        postSurgicalComplicationsDataInfoUpdate.setPost_surgical_complication_description(newPostSurgicalComplicationsData.getPost_surgical_complication_description());
        postSurgicalComplicationsDataInfoUpdate.setComplication_COD(newPostSurgicalComplicationsData.getComplication_COD());
        postSurgicalComplicationsDataInfoUpdate.setComplication_principal_COD(newPostSurgicalComplicationsData.getComplication_principal_COD());
        postSurgicalComplicationsDataInfoUpdate.setACS_classification_general_complications(newPostSurgicalComplicationsData.getACS_classification_general_complications());
        postSurgicalComplicationsDataInfoUpdate.setACS_classification_specific_complications(newPostSurgicalComplicationsData.getACS_classification_specific_complications());
        postSurgicalComplicationsDataInfoUpdate.setClavien_Dindo_classification(newPostSurgicalComplicationsData.getClavien_Dindo_classification());


        return postSurgicalComplicationsData.save(postSurgicalComplicationsDataInfoUpdate);
    } 

    @DeleteMapping("/postSurgicalComplicationsDataInfo/{id}")
    public void deletePostSurgicalComplicationsDataData(@PathVariable Long id) {
        postSurgicalComplicationsData.findById(id).orElseThrow(PostSurgicalComplicationsDataNotFoundException::new);
        postSurgicalComplicationsData.deleteById(id);
    }



}