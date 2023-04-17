package com.example.expretrofit_;

public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email ;
    private String body ;
    public int getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getBody() {
        return body;
    }

    }
