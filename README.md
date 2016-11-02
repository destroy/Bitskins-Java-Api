# Bitskins-Java-Api
[Java 1.8] API for BitSkins.com

A Java library to interact with BitSkins official API. 
The project uses Maven to build itself, but you can download compiled 
modules with deps from /bit folder

## Usage example
```java
public class Main {
    public static void main(String[] args) throws IOException, UnsuccessfulRequestException {
        final String API_KEY = ""; //in your profile settings
        final String PASSWORD_SECRET_KEY = ""; //you can get it during enabling two-factor auth
        BitSkinsApi bitSkinsApi = new BitSkinsApi("", "");
        System.out.println(bitSkinsApi.getAccountBalance().getAvailableBalance());
    }
}
```
