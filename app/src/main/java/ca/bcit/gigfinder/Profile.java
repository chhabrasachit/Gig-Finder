/*
    This class represents the Profile of the user.
 */
package ca.bcit.gigfinder;

// This is the Profile Class that is required in the ProfileFragment

/**
 * Profile of the user:
 * required in Profile Fragment to access data.
 */
public class Profile {

    // Initializes the Strings

    private String fullName;
    private String ID;
    private String email;
    private String phoneNumber;
    private String biography;
    private String preference;
    private String experience;
    private String JobTitle;
    private String JobDesc;
    private String message;

    public Profile(){}

    /**
     * Initializer
     * @param fullName :String
     * @param email :String
     * @param phoneNumber :String
     * @param biography :String
     * @param preference :String
     * @param experience :String
     */
    public Profile(String fullName, String email, String phoneNumber,
            String biography, String preference, String experience){

        // Get the instance of strings
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.biography = biography;
        this.preference = preference;
        this.experience = experience;
    }

    /**
     * Initializer
     * @param jobTitle :String
     * @param jobDesc :String
     */
    public Profile(String jobTitle, String jobDesc){
        this.ID = ID;
        this.JobDesc = jobDesc;
        this.JobTitle = jobTitle;
    }

    /**
     * Initiliazer
     * @param email :String
     * @param name :String
     * @param message :String
     */
    public Profile(String email, String name, String message){
        this.email = email;
        this.fullName = name;
        this.message = message;
    }

    // Initialized all the getters and setters for all the strings.

    /**
     * Accessor for the full name
     * @return String
     */
     public String getFullName() {
        return fullName;
    }

    /**
     * Mutator for the full name
     * @param fullName: String
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Accessor for the email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Mutator for the email
     * @param email :String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Accessor for the phone number
     * @return String
     */
     public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Mutator for the phone numner
     * @param phoneNumber : String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Accessor for the biography
     * @return String
     */
     public String getBiography() {
        return biography;
    }

    /**
     * Mutator for the biography
     * @param biography : String
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }

    /**
     * Accessor for the preference
     * @return String
     */
     public String getPreference() {
        return preference;
    }

    /**
     * Mutator for thr preference
     * @param preference : String
     */
    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getExperience() {
        return experience;
    }

    /**
     * Mutator for the experience
     * @param experience :String
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * Accessor for the JobTitle
     * @return String
     */
    public String getJobTitle() {
        return JobTitle;
    }

    /**
     * Mutator for the jobTitle
     * @param jobTitle: String
     */
    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    /**
     * Accessor for ID
     * @return String
     */
    public String getID() {
        return ID;
    }

    /**
     * Mutator for ID
     * @param ID :String
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Accessor for the Jonb Description
     * @return String
     */
    public String getJobDesc() {
        return JobDesc;
    }

    /**
     * Mutator for the Job Description
     * @param jobDesc :String
     */
    public void setJobDesc(String jobDesc) {
        JobDesc = jobDesc;
    }

    /**
     * Accessor for the message
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Mutator for the message
     * @param message :String
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
