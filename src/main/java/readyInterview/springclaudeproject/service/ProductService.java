package readyInterview.springclaudeproject.service;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import readyInterview.springclaudeproject.entity.Product;
import readyInterview.springclaudeproject.exception.ResourceNotFoundException;
import readyInterview.springclaudeproject.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("This product by id: "+id+" not found!"));
    }

    @Transactional
    public ResponseEntity<String> purchase(Product product, int amount) throws BadRequestException {

        int amountProduct = product.getQuantity();

        if (amountProduct < amount) {
            throw new BadRequestException("there is not enough of product quantity");
        }

        product.setQuantity(amountProduct-amount);
        save(product);
        return ResponseEntity.ok().build();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
