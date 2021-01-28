package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.stock_management.dto.product.CountProductsDto;
import com.stock_management.dto.product.ProductDto;
import com.stock_management.dto.product.ProductListDto;
import com.stock_management.dto.product.ProductStockDto;
import com.stock_management.dto.product.SaveProductDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.QProduct;
import com.stock_management.entity.QStock;
import com.stock_management.entity.Stock;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.mapper.SupplierMapper;
import com.stock_management.mapper.UserMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.repository.StockRepository;
import com.stock_management.repository.SupplierRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.ProductService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@EnableAutoConfiguration
public class ProductServiceImplementation implements ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final UserMapper userMapper;

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository, ProductMapper productMapper, SupplierRepository supplierRepository, SupplierMapper supplierMapper, UserRepository userRepository, StockRepository stockRepository, UserMapper userMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
        this.userMapper = userMapper;
    }
    // GET
    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductListDto findAllProductLessThanMinStockAmount(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return getProductListLessThanMinStockAmountDto(products);
    }

    private ProductListDto getProductListLessThanMinStockAmountDto(Page<Product> product) {
        if (product == null) {
            return null;
        } else {
            List<ProductDto> productDto = product.stream().filter(this::filterProduct).map(this::mapListOfProductWithAdditionalDetail).collect(Collectors.toList());
            ProductListDto productListDto = new ProductListDto();
            productListDto.setProductsDto(productDto);
            productListDto.setTotalElements(product.getNumberOfElements());
            productListDto.setTotalPages(product.getTotalPages());
            return productListDto;
        }
    }

    private boolean filterProduct(Product product) {
        var stocks = stockRepository.findStockByProduct_ProductId_AndQuantityIsGreaterThanOrderByCreatedDateDesc(product.getProductId(), 0);
        double quantity = stocks.stream().mapToDouble(Stock::getQuantity).sum();
        return quantity < product.getMinStockAmount();
    }

    @Override
    public ProductListDto findAllProductList(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return getProductListDto(products);
    }

    private ProductListDto getProductListDto(Page<Product> product) {
        if (product == null) {
            return null;
        } else {
            List<ProductDto> productDto = product.stream().map(this::mapListOfProductWithAdditionalDetail).collect(Collectors.toList());
            ProductListDto productListDto = new ProductListDto();
            productListDto.setProductsDto(productDto);
            productListDto.setTotalElements(product.getNumberOfElements());
            productListDto.setTotalPages(product.getTotalPages());
            return productListDto;
        }
    }

    private ProductDto mapListOfProductWithAdditionalDetail(Product product) {
        ProductDto productDto = new ProductDto();
        var stocks = stockRepository.findStockByProduct_ProductId_AndQuantityIsGreaterThanOrderByCreatedDateDesc(product.getProductId(), 0);
        return setProductDto(product, stocks, productDto);
    }


    @Override
    public CountProductsDto countAllProducts() {
        Long numberOfProducts = productRepository.count();
        CountProductsDto countProductsDto = new CountProductsDto();
        countProductsDto.setNumberOfProducts(numberOfProducts);
        return countProductsDto;
    }

    @Override
    public ProductDto findProductById(Long productId) {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            var product = optionalProduct.orElse(null);
            var stocks = stockRepository.findStockByProduct_ProductId_AndQuantityIsGreaterThanOrderByCreatedDateDesc(productId, 0);

            ProductDto productDto = new ProductDto();
            if (Objects.nonNull(product)) {
                setProductDto(product, stocks, productDto);
            }

            return productDto;
    }

    private ProductDto setProductDto(Product product, List<Stock> stocks, ProductDto productDto) {
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setDosage(product.getDosage());
        productDto.setCategory(product.getCategory());
        productDto.setMinStockAmount(product.getMinStockAmount());
        productDto.setUnitsPerBox(product.getUnitsPerBox());
        productDto.setRequirePrescription(product.getRequirePrescription());
        productDto.setSlot(product.getSlot());
        productDto.setCreatedBy(userMapper.mapUserEntityToDto(product.getCreatedBy()));
        productDto.setCreatedDate(product.getCreatedDate());
        productDto.setLastModifiedBy(userMapper.mapUserEntityToDto(product.getLastModifiedBy()));
        productDto.setLastModifiedDate(product.getLastModifiedDate());
        productDto.setProductStocksDto(mapStockToProductStock(stocks));
        productDto.setSupplier(supplierMapper.mapSupplierEntityToDto(product.getSupplier()));
        return productDto;
    }

    private List<ProductStockDto> mapStockToProductStock(List<Stock> stocks) {
        ProductStockDto productStockDto = new ProductStockDto();
        List<ProductStockDto> productStocksDto = new ArrayList<>();
        stocks.forEach(stock -> {
            productStockDto.setStockId(stock.getStockId());
            productStockDto.setQuantity(stock.getQuantity());
            productStockDto.setUnitsTotal(stock.getUnitsTotal());
            productStockDto.setWholeSalePrice(stock.getWholeSalePrice());
            productStockDto.setPricePerBox(stock.getPricePerBox());
            productStockDto.setPricePerUnit(stock.getPricePerUnit());
            productStockDto.setExpiryDate(stock.getExpiryDate());
            productStockDto.setMaxUnitsCanBeEntered(null);
            productStocksDto.add(productStockDto);
        });

        return productStocksDto;
    }

    @Override
    public Long findNumberOfProductsLowInStock() {
        List<Product> products = productRepository.findAll();
        return products.stream().filter(this::filterProduct).count();
    }

    // PUT
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editProduct(ProductDto productDto) throws Exception {
        var optionalProduct = productRepository.findById(productDto.getProductId());
        var product = optionalProduct.orElse(null);

        var optionalUser = userRepository.findById(productDto.getLastModifiedBy().getUserId());
        var user = optionalUser.orElse(null);

        var optionalSupplier = supplierRepository.findById(productDto.getSupplier().getSupplierId());
        var supplier = optionalSupplier.orElse(null);

        var stocks = stockRepository.findStockByProduct_ProductId_AndQuantityIsGreaterThanOrderByCreatedDateDesc(productDto.getProductId(), 0);

        if (Objects.nonNull(product)) {
            product.setProductName(productDto.getProductName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setDosage(productDto.getDosage());
            product.setUnitsPerBox(productDto.getUnitsPerBox());
            product.setRequirePrescription(productDto.getRequirePrescription());
            product.setSlot(productDto.getSlot());
            product.setMinStockAmount(productDto.getMinStockAmount());
            product.setSupplier(supplier);
            product.setLastModifiedBy(user);
            product.setLastModifiedDate(new Date());
            productRepository.save(product);

            if (Objects.nonNull(stocks)) {
//                stock.setProduct(productMapper.mapProductDtoToEntity(productDto));
//                stock.setQuantity(productDto.getBox());
//                stock.setUnitsTotal((productDto.getBox() * productDto.getUnitsPerBox()));
//                stock.setWholeSalePrice(productDto.getWholeSalePrice());
//                stock.setPricePerBox(productDto.getPricePerBox());
//                stock.setPricePerUnit(productDto.getPricePerBox() / productDto.getUnitsPerBox());
//                stock.setExpiryDate(productDto.getExpiryDate().plusDays(1));
//                stockRepository.save(stock);
            }
        } else {
            throw new Exception("product.not.found");
        }
    }

    // POST
    @Override
    @Transactional
    public void saveProduct(SaveProductDto saveProductDto) {
        saveProductAndStock(saveProductDto);
    }

    @Override
    @Transactional
    public void saveProducts(List<SaveProductDto> saveProductsDto) {
        saveProductsDto.forEach(this::saveProductAndStock);
    }

    private void saveProductAndStock(SaveProductDto saveProductDto) {
        Stock stock = new Stock();

        var savedProduct = productRepository.save(mapSaveProductDtoToEntity(saveProductDto));

        stock.setProduct(savedProduct);
        stock.setQuantity(saveProductDto.getBox());
        stock.setWholeSalePrice(saveProductDto.getWholeSalePrice());
        stock.setPricePerBox(saveProductDto.getPricePerBox());
        stock.setExpiryDate(saveProductDto.getExpiryDate().plusDays(1));
        stock.setUnitsTotal((saveProductDto.getBox() * saveProductDto.getUnitsPerBox()));
        stock.setPricePerUnit(saveProductDto.getPricePerBox() / saveProductDto.getUnitsPerBox());
        stock.setCreatedDate(new Date());
        stockRepository.save(stock);
    }

    private Product mapSaveProductDtoToEntity(SaveProductDto saveProductDto) {
        Product product = new Product();
        product.setProductId(saveProductDto.getProductId());
        product.setProductName(saveProductDto.getProductName());
        product.setDescription(saveProductDto.getDescription());
        product.setDosage(saveProductDto.getDosage());
        product.setCategory(saveProductDto.getCategory());
        product.setMinStockAmount(saveProductDto.getMinStockAmount());
        product.setUnitsPerBox(saveProductDto.getUnitsPerBox());
        product.setRequirePrescription(saveProductDto.getRequirePrescription());
        product.setSlot(saveProductDto.getSlot());
        product.setSupplier(supplierMapper.mapSupplierDtoToEntity(saveProductDto.getSupplier()));
        product.setCreatedBy(userMapper.mapUserDtoToEntity(saveProductDto.getCreatedBy()));
        return product;
    }

    @Override
    public ProductListDto findListOfProductsByFilters(String productName, Long supplierId, String category, String slot, LocalDate expiryDate, Boolean productLowInStock, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(productName, supplierId, category, slot, expiryDate);
        Page<Product> product = productRepository.findAll(predicate,pageRequest);
        if (productLowInStock) {
            List<ProductDto> productDtos = product.stream().filter(this::filterProduct).map(this::mapListOfProductWithAdditionalDetail).collect(Collectors.toList());
            return getProductListDto(product, productDtos);
        }
        List<ProductDto> productDtos = product.stream().map(this::mapListOfProductWithAdditionalDetail).collect(Collectors.toList());
        return getProductListDto(product, productDtos);

    }

    private ProductListDto getProductListDto(Page<Product> product, List<ProductDto> productDtos) {
        var productListDto = new ProductListDto();
        productListDto.setProductsDto(productDtos);
        productListDto.setTotalElements(product.getNumberOfElements());
        productListDto.setTotalPages(product.getTotalPages());
        return productListDto;
    }


    private BooleanBuilder buildProductPredicate(String productName, Long supplierId, String category, String slot, LocalDate expiryDate) {
        var qProduct = QProduct.product;
        var qStock = QStock.stock;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!productName.equals("")) {
            booleanBuilder.and(qProduct.productName.toLowerCase().contains(productName.toLowerCase()));
        }
        if(Objects.nonNull(supplierId) && supplierId != 0) {
            booleanBuilder.and(qProduct.supplier.supplierId.eq(supplierId));
        }
        if(!slot.equals("All")) {
            booleanBuilder.and(qProduct.slot.eq(slot));
        }
        if(!category.equals("All")) {
            booleanBuilder.and(qProduct.category.toLowerCase().eq(category.toLowerCase()));
        }
        if(Objects.nonNull(expiryDate)) {
            booleanBuilder.and(qStock.expiryDate.before(expiryDate).and(qStock.quantity.ne(0D)));
        }
        return booleanBuilder;
    }

    @Override
    public List<String> findAllSlots() {
        var qProduct = QProduct.product;
        var slots = new JPAQuery<Product>(entityManager).select(
                qProduct.slot).distinct().from(qProduct).fetchResults().getResults();

        return new ArrayList<>(slots);
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        try {
            List<SaveProductDto> saveProductsDto = excelToProducts(file.getInputStream());
            saveProducts(saveProductsDto);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public List<SaveProductDto> excelToProducts(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<SaveProductDto> saveProductsDto = new ArrayList<SaveProductDto>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                SaveProductDto saveProductDto = new SaveProductDto();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            saveProductDto.setProductName(currentCell.getStringCellValue());
                            break;

                        case 1:
                            if (currentCell.getStringCellValue().equals("NULL")) {
                                saveProductDto.setDescription("");
                            } else {
                                saveProductDto.setDescription(currentCell.getStringCellValue());
                            }
                            break;

                        case 2:
                            if (currentCell.getStringCellValue().equals("NULL")) {
                                saveProductDto.setDosage("0");
                            } else {
                                saveProductDto.setDosage(currentCell.getStringCellValue());
                            }
                            break;

                        case 3:
                            if (currentCell.getStringCellValue().equals("NULL")) {
                                saveProductDto.setCategory("");
                            } else {
                                saveProductDto.setCategory(currentCell.getStringCellValue());
                            }
                            break;

                        case 4:
                            saveProductDto.setBox((double) currentCell.getNumericCellValue());
                            break;

                        case 5:
                            saveProductDto.setUnitsPerBox((int) currentCell.getNumericCellValue());
                            break;

                        case 6:
                            saveProductDto.setUnitsTotal((double) currentCell.getNumericCellValue());
                            break;

                        case 7:
                            saveProductDto.setPricePerBox((double) currentCell.getNumericCellValue());
                            break;

                        case 8:
                            saveProductDto.setPricePerUnit((double) currentCell.getNumericCellValue());
                            break;

                        case 9:
                            saveProductDto.setRequirePrescription(currentCell.getBooleanCellValue());
                            break;

                        case 10:
                            saveProductDto.setSlot(currentCell.getStringCellValue());
                            break;

                        case 11:
                            saveProductDto.setExpiryDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;

                        case 12:
                            var supplier = supplierRepository.findSupplierBySupplierName(currentCell.getStringCellValue());
                            saveProductDto.setSupplier(supplierMapper.mapSupplierEntityToDto(supplier));
                            break;

                        case 13:
                            saveProductDto.setMinStockAmount((int) currentCell.getNumericCellValue());
                            break;

                        case 14:
                            saveProductDto.setWholeSalePrice((double) currentCell.getNumericCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                saveProductsDto.add(saveProductDto);
            }

            workbook.close();

            return saveProductsDto;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
