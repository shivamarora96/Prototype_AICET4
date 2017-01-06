package com.teamninjas.prototype_aicet4.FeedRecyclerView;


public class MainFeedObject {


    public String title;
    public String desc;
    public String imageUrl;
    public Integer post_ques;
    public Integer numberLikes;
    public Integer numberDislikes;




    public Integer getNumberLikes() {
        return numberLikes;
    }
    public Integer getNumberDislikes() {
        return numberDislikes;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getPost_ques() {
        return post_ques;
    }




    public void setNumberLikes(Integer numberLikes) {
        this.numberLikes = numberLikes;
    }
    public void setNumberDislikes(Integer numberDislikes) {
        this.numberDislikes = numberDislikes;
    }





    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPost_ques(Integer post_ques) {
        this.post_ques = post_ques;
    }




}
