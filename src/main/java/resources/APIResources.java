package resources;

public enum APIResources {

    ADD_PLACE("/maps/api/place/add/json"),
    GET_PLACE("/maps/api/place/get/json");

    private String resource;

    APIResources(String resource) {

        this.resource = resource;
    }

    public String getResource() {

        return resource;
    }
}