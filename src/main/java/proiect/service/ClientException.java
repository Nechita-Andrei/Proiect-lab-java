package proiect.service;

public class ClientException extends RuntimeException{

    public enum ErrorCodeClient {
        CLIENT_NOT_FOUND,
        BAD_CREDENTIALS,
        NOT_ENOUGH_MONEY,
        ACCOUNT_NOT_FOUND,
        DOES_NOT_HAVE_PERMISSION,
        NOT_ELIGIBLE_FOR_DISCOUNT
    }


    private ErrorCodeClient errorCodeClient;
    public ErrorCodeClient getErrorCodeClient() {
        return errorCodeClient;
    }

    private ClientException(ErrorCodeClient errorCodeClient){
        this.errorCodeClient=errorCodeClient;
    }

    public static ClientException clientNotFound(){
        return new ClientException(ErrorCodeClient.CLIENT_NOT_FOUND);
    }

    public static ClientException badCredentials(){
        return new ClientException(ErrorCodeClient.BAD_CREDENTIALS);
    }

    public static ClientException notEnoughMoney(){return new ClientException(ErrorCodeClient.NOT_ENOUGH_MONEY);}

    public static ClientException accountNotFound(){return new ClientException(ErrorCodeClient.ACCOUNT_NOT_FOUND);}

    public static ClientException noPermission(){ return new ClientException(ErrorCodeClient.DOES_NOT_HAVE_PERMISSION);}
    public static ClientException notEligibleForDiscount(){ return new ClientException(ErrorCodeClient.NOT_ELIGIBLE_FOR_DISCOUNT);}

}
