package com.example.circleimageviewproblem;

public class ImageData
{
   private String title;
   private String imageUrl;

   public ImageData(String title, String imageUrl) {
      this.title = title;
      this.imageUrl = imageUrl;
   }

   public ImageData() {
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }
}
