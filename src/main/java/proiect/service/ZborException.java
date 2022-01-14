package proiect.service;

public class ZborException extends RuntimeException{
    public enum ErrorCodeZbor {
        ZBORUL_NU_A_FOST_GASIT,
        AVIONUL_NU_A_FOST_GASIT,
        PILOTUL_NU_A_FOST_GASIT,
        AEROPORTUL_NU_A_FOST_GASIT,
        DESTINATIA_NU_A_FOST_GASITA

    }

    private ErrorCodeZbor errorCodeZbor;

    public ZborException(ErrorCodeZbor errorCodeZbor){
        this.errorCodeZbor=errorCodeZbor;
    }
    public static ZborException avionNotFound(){ return new ZborException(ErrorCodeZbor.AVIONUL_NU_A_FOST_GASIT);}
    public static ZborException pilotNotFound(){ return new ZborException(ErrorCodeZbor.PILOTUL_NU_A_FOST_GASIT);}
    public static ZborException aeroportNotFound(){ return new ZborException(ErrorCodeZbor.AEROPORTUL_NU_A_FOST_GASIT);}
    public static ZborException destinatieNotFound(){ return new ZborException(ErrorCodeZbor.DESTINATIA_NU_A_FOST_GASITA);}

    public ErrorCodeZbor getErrorCodeZbor() {
        return errorCodeZbor;
    }

    public static ZborException zborNotFound(){
        return new ZborException(ErrorCodeZbor.ZBORUL_NU_A_FOST_GASIT);
    }
}
