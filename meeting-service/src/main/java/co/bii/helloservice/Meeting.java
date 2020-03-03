package co.bii.helloservice;

@FirebaseDocument("/meetings")
public class Meetings {

    @FirebaseId
    private String id;

    private String title;

}
