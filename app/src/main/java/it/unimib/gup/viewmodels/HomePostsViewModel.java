package it.unimib.gup.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.unimib.gup.model.Post;

public class HomePostsViewModel {
    private LiveData<List<Post>> mPosts;

    public void init() {

    }

    public LiveData<List<Post>> getPosts () {
        return  mPosts;
    }
}
