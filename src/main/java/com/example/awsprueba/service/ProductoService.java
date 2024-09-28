package com.example.awsprueba.service;

import com.example.awsprueba.models.Product;
import com.example.awsprueba.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Product> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Product> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Product crearProducto(Product producto) {
        return productoRepository.save(producto);
    }

    public Product actualizarProducto(Long id, Product productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setName(productoActualizado.getName());
                    producto.setPrice(productoActualizado.getPrice());
                    producto.setQuanty(productoActualizado.getQuanty());
                    return productoRepository.save(producto);
                }).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}