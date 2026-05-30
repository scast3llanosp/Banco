package banco;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/clientes")
public class ClienteController
{
    private final ClienteService service;

    public ClienteController(ClienteService service)
    {
        this.service = service;
    }

    @Post
    public HttpResponse<String> crear(@Body Cliente cliente)
    {
        service.crear(cliente);

        return HttpResponse.ok("Cliente creado");
    }

    @Get
    public List<Cliente> listar()
    {
        return service.listar();
    }

    @Get("/{id}")
    public Cliente obtener(Long id)
    {
        return service.obtener(id);
    }

    @Put("/{id}")
    public HttpResponse<String> actualizar(Long id, @Body Cliente cliente)
    {
        service.actualizar(id, cliente);

        return HttpResponse.ok("Cliente actualizado");
    }

    @Delete("/{id}")
    public HttpResponse<String> eliminar(Long id)
    {
        service.eliminar(id);

        return HttpResponse.ok("Cliente eliminado");
    }
}