package com.grv.glammtest.network.response;

public class ApiResponse<E> {
        // act as wrapper, it wraps retrofit response + error if occurs
    public E posts;
    private Throwable error;

    public ApiResponse(E posts) {
        this.posts = posts;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.posts = null;
    }

    public E getPosts() {
        return posts;
    }

    public void setPosts(E posts) {
        this.posts = posts;
    } 

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
