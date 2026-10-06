package readyInterview.springclaudeproject.controller;


import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import readyInterview.springclaudeproject.dto.ProductDto;
import readyInterview.springclaudeproject.entity.IdempotencyRecord;
import readyInterview.springclaudeproject.entity.Product;
import readyInterview.springclaudeproject.repository.IdempotencyRecordRepository;
import readyInterview.springclaudeproject.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final IdempotencyRecordRepository idempotencyRepository;

    public ProductController(ProductService productService, IdempotencyRecordRepository idempotencyRepository) {
        this.productService = productService;
        this.idempotencyRepository = idempotencyRepository;
    }

    @PostMapping("/register")qq
    public Product registerProduct(@RequestBody ProductDto productdto) {

        Product product = new Product();
        product.setProductName(productdto.getName());
        product.setQuantity(productdto.getQuantity());
        product.setPrice(productdto.getPrice());
        return productService.save(product);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<String> purchaseProduct(@PathVariable Long id, @RequestParam int amount
            ,@RequestHeader("Idempotency-key") String idempotencyKey) throws BadRequestException {
        Product product = productService.getById(id);
        IdempotencyRecord idempotency =  idempotencyRepository.findById(idempotencyKey).orElse(null);
        if (idempotency == null) {

            IdempotencyRecord newIdempotency = new IdempotencyRecord();
            newIdempotency.setStatus(IdempotencyRecord.Status.PENDING);
            newIdempotency.setIdempotencyKey(idempotencyKey);
            idempotencyRepository.save(newIdempotency);

            ResponseEntity<String> response = productService.purchase(product, amount);

            newIdempotency.setResponse(response.getBody());
            newIdempotency.setStatus(IdempotencyRecord.Status.COMPLETED);
            idempotencyRepository.save(newIdempotency);
            return response;

        }else {
            return ResponseEntity.ok(idempotency.getResponse());
        }
    }
}
