public class Flight {
    
    private String code;
    private Plane plane;

    public Flight(String code, Plane plane){
        this.code = code;
        this.plane = plane;
    }

    public String getCode(){
        return this.code;
    }

    public Plane getPlane(){
        return this.plane;
    }

    //verificar como ir buscar cenas dentro do flight, acho que vimos na aula, era tipo high cohesion ou assim
}
