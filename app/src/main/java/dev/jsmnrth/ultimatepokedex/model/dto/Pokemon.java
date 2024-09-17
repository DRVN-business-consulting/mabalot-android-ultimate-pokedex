package dev.jsmnrth.ultimatepokedex.model.dto;

public class Pokemon {
    private int id;
    private Name name;
    private Image image;
    private Location location;

    public static class Name {
        private String english;
        private String japanese;
        private String chinese;
        private String french;

        // Getters and setters
        public String getEnglish() { return english; }
        public void setEnglish(String english) { this.english = english; }
        public String getJapanese() { return japanese; }
        public void setJapanese(String japanese) { this.japanese = japanese; }
        public String getChinese() { return chinese; }
        public void setChinese(String chinese) { this.chinese = chinese; }
        public String getFrench() { return french; }
        public void setFrench(String french) { this.french = french; }
    }

    public static class Image {
        private String sprite;
        private String thumbnail;
        private String hi_res;

        // Getters and setters
        public String getSprite() { return sprite; }
        public void setSprite(String sprite) { this.sprite = sprite; }
        public String getThumbnail() { return thumbnail; }
        public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
        public String getHi_res() { return hi_res; }
        public void setHi_res(String hi_res) { this.hi_res = hi_res; }
    }

    public static class Location {
        private String name;
        private double latitude;
        private double longitude;

        // Getters and setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getLatitude() { return latitude; }
        public void setLatitude(double latitude) { this.latitude = latitude; }
        public double getLongitude() { return longitude; }
        public void setLongitude(double longitude) { this.longitude = longitude; }
    }

    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Name getName() { return name; }
    public void setName(Name name) { this.name = name; }
    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }


}
