package com.szalak.jsonplaceholder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long userId;
    private Long id;
    private String title;
    private String body;

}
