package activemq;

public class AMQTest {

	public static void main(String[] args) {
		Conexion connection = new Conexion();
		connection.setParams("local", args[0], "principal", "durable");
		connection.open();
		connection.send("hola, enviado desde local",60000);
	}

}
