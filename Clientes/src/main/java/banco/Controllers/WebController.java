package banco;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.View;
import io.micronaut.http.MediaType;
import io.micronaut.http.HttpResponse;
import io.micronaut.context.annotation.Value;
import java.net.URI;
import io.micronaut.http.annotation.QueryValue;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController
{
    private final ClienteService service;
    @Value("${app.base-url:}")
    private String baseUrl;

    public WebController(ClienteService service)
    {
        this.service = service;
    }

    @View("clientes")
    @Get(produces = MediaType.TEXT_HTML)
    public Map<String, Object> index(@QueryValue(defaultValue = "") String mensaje)
    {
        Map<String, Object> model = new HashMap<>();

        model.put("baseUrl", baseUrl);
        model.put("mensaje", mensaje);
	model.put("clientes", service.listar());

        return model;
    }

    @Post(value = "/clientes/guardar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> guardar(
        Long id,
        String nombres,
        String apellidos,
        String direccion,
        String fechaNacimiento,
        String telefono,
        String identificacion,
        Integer tipoIdentificacion
    )
    {
        Cliente cliente = new Cliente();

        cliente.setId(id);
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direccion);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setTelefono(telefono);
        cliente.setIdentificacion(identificacion);
        cliente.setTipoIdentificacion(tipoIdentificacion);

        service.crear(cliente);

        return HttpResponse.redirect(URI.create(baseUrl + "/?mensaje=Cliente%20guardado%20satisfactoriamente"));
    }
}