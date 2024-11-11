package com.scaler.productcatalogservice.services;

import com.scaler.productcatalogservice.clients.FakeStoreApiClient;
import com.scaler.productcatalogservice.dtos.FakeStoreProductDto;
import com.scaler.productcatalogservice.models.Category;
import com.scaler.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(from(fakeStoreProductDto));
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) {
        /*
        * Check in cache
        *   if found then return from cache
        *   else
        *       call fakeStore
        *       save in cache
        * */

        FakeStoreProductDto fakeStoreProductDto = null;

        fakeStoreProductDto = (FakeStoreProductDto)redisTemplate.opsForHash().get("_PRODUCTS_",id);

        if( fakeStoreProductDto != null ){
            System.out.println("From Cache");
            return from(fakeStoreProductDto);
        }

        fakeStoreProductDto = fakeStoreApiClient.getProductById(id);

        if( fakeStoreProductDto != null ) {
            System.out.println("From FakeStore");
            redisTemplate.opsForHash().put("_PRODUCTS_",id,fakeStoreProductDto);
            return from(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = from(product);
        FakeStoreProductDto fakeStoreProductDtoResponse =
                requestForEntity("https://fakestoreapi.com/products",HttpMethod.POST,fakeStoreProductDto, FakeStoreProductDto.class).getBody();
        return from(fakeStoreProductDtoResponse);
    }

    public Product replaceProduct(long id, Product product){
        FakeStoreProductDto fakeStoreProductDto = from(product);
        FakeStoreProductDto fakeStoreProductDtoResponse =
        requestForEntity("https://fakestoreapi.com/products/{id}",HttpMethod.PUT, fakeStoreProductDto,FakeStoreProductDto.class,id).getBody();
        return from(fakeStoreProductDtoResponse);
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    // Mapper function
    public Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setID(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setAmount(fakeStoreProductDto.getPrice());
        product.setImageURL(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    public FakeStoreProductDto from(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getID());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getAmount());
        fakeStoreProductDto.setImage(product.getImageURL());
        if( product.getCategory() != null ) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

}
