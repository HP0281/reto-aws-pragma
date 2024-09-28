package com.example.awsprueba.service;

import com.example.awsprueba.models.Product;
import com.example.awsprueba.repositories.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerProductoPorId() {
        Product productoMock = new Product();
        productoMock.setId(1);
        productoMock.setName("Producto Test");

        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(productoMock));

        Optional<Product> productOpt = productoService.obtenerProductoPorId(1L);

        Assertions.assertEquals("Producto Test", productOpt.get().getName());
    }

    @Test
    void testListarProductos() {
        List<Product> productos = Arrays.asList(new Product(), new Product());
        Mockito.when(productoRepository.findAll()).thenReturn(productos);

        List<Product> resultado = productoService.listarProductos();
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    void testCrearProducto() {
        Product productoMock = new Product();
        productoMock.setId(1);
        productoMock.setName("Nuevo Producto");
        productoMock.setPrice(100.0);
        productoMock.setQuanty(10);

        Mockito.when(productoRepository.save(productoMock)).thenReturn(productoMock);

        Product productoCreado = productoService.crearProducto(productoMock);

        Assertions.assertNotNull(productoCreado);
        Assertions.assertEquals("Nuevo Producto", productoCreado.getName());
        Assertions.assertEquals(100.0, productoCreado.getPrice());
        Assertions.assertEquals(10, productoCreado.getQuanty());
    }

    @Test
    void testActualizarProducto() {
        Product productoExistente = new Product();
        productoExistente.setId(1);
        productoExistente.setName("Producto Existente");
        productoExistente.setPrice(200.0);
        productoExistente.setQuanty(20);

        Product productoActualizado = new Product();
        productoActualizado.setName("Producto Actualizado");
        productoActualizado.setPrice(300.0);
        productoActualizado.setQuanty(30);

        Mockito.when(productoRepository.findById(1L)).thenReturn(Optional.of(productoExistente));
        Mockito.when(productoRepository.save(productoExistente)).thenReturn(productoExistente);

        Product productoResult = productoService.actualizarProducto(1L, productoActualizado);

        Assertions.assertNotNull(productoResult);
        Assertions.assertEquals("Producto Actualizado", productoResult.getName());
        Assertions.assertEquals(300.0, productoResult.getPrice());
        Assertions.assertEquals(30, productoResult.getQuanty());
    }

    @Test
    void testEliminarProducto() {
        Long idProducto = 1L;
        Mockito.doNothing().when(productoRepository).deleteById(idProducto);

        productoService.eliminarProducto(idProducto);

        Mockito.verify(productoRepository, Mockito.times(1)).deleteById(idProducto);
    }


}