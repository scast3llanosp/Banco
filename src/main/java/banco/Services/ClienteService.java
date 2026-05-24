package banco;

import jakarta.inject.Singleton;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import java.util.*;

@Singleton
public class ClienteService
{
    private static final String TABLE_NAME = "clientes";
    private final DynamoDbClient dynamo;

    public ClienteService(DynamoDbClient dynamo)
    {
        this.dynamo = dynamo;
    }

    public void crear(Cliente cliente)
    {
        Map<String, AttributeValue> item = new HashMap<>();

        item.put("id", AttributeValue.builder().n(cliente.getId().toString()).build());
        item.put("nombres", AttributeValue.builder().s(cliente.getNombres()).build());
        item.put("apellidos", AttributeValue.builder().s(cliente.getApellidos()).build());
        item.put("direccion", AttributeValue.builder().s(cliente.getDireccion()).build());
        item.put("fechaNacimiento", AttributeValue.builder().s(cliente.getFechaNacimiento()).build());
        item.put("telefono", AttributeValue.builder().s(cliente.getTelefono()).build());
        item.put("identificacion", AttributeValue.builder().s(cliente.getIdentificacion()).build());
        item.put("tipoIdentificacion", AttributeValue.builder().n(cliente.getTipoIdentificacion().toString()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(item)
                .build();

        dynamo.putItem(request);
    }

    public Cliente obtener(Long id)
    {
        Map<String, AttributeValue> key = Map.of("id", AttributeValue.builder().n(id.toString()).build());

        GetItemRequest request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        GetItemResponse response = dynamo.getItem(request);

        if (!response.hasItem())
        {
            return null;
        }

        return mapear(response.item());
    }

    public List<Cliente> listar()
    {
        ScanRequest request = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .build();

        ScanResponse response = dynamo.scan(request);

        List<Cliente> clientes = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items())
        {
            clientes.add(mapear(item));
        }

        return clientes;
    }

    public void actualizar(Long id, Cliente cliente)
    {
        cliente.setId(id);
        crear(cliente);
    }

    public void eliminar(Long id)
    {
        Map<String, AttributeValue> key = Map.of("id", AttributeValue.builder().n(id.toString()).build());

        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        dynamo.deleteItem(request);
    }

    private Cliente mapear(Map<String, AttributeValue> item)
    {
        Cliente cliente = new Cliente();

        cliente.setId(Long.parseLong(item.get("id").n()));
        cliente.setNombres(item.get("nombres").s());
        cliente.setApellidos(item.get("apellidos").s());
        cliente.setDireccion(item.get("direccion").s());
        cliente.setFechaNacimiento(item.get("fechaNacimiento").s());
        cliente.setTelefono(item.get("telefono").s());
        cliente.setIdentificacion(item.get("identificacion").s());
        cliente.setTipoIdentificacion(Integer.parseInt(item.get("tipoIdentificacion").n()));

        return cliente;
    }
}