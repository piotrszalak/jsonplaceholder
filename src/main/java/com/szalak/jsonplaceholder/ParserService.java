package com.szalak.jsonplaceholder;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
@Service
public class ParserService {
 
    private RestTemplate restTemplate;

    @Autowired
    public ParserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void saveToJsonFile(){
        String path = "jsonStorage/";
        String suffix = ".json";

        for (Post post : getPostsFromJsonPlaceholder()) {
            JSONObject object = new JSONObject();
            object.put("userId", post.getUserId());
            object.put("id", post.getId());
            object.put("title", post.getTitle());
            object.put("body", post.getBody());

            try {
               FileWriter file =  new FileWriter(path + post.getId() + suffix);
                        file.write(object.toJSONString());
                        file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Post> getPostsFromJsonPlaceholder() {
        ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/",
                Post[].class);
        return Arrays.asList(responseEntity.getBody());
    }


}
