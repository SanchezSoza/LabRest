package cl.rs.project.bice.lab.proyectoBiceLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import cl.rs.project.bice.lab.service.BiceLabService;

@SpringBootApplication
@ComponentScan(basePackages={"cl.rs.project.bice.lab"})
public class ProyectoBiceLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBiceLabApplication.class, args);
//		BiceLabService service = new BiceLabService();
//		service.obtenerTodosLosDatos();
	}

}
