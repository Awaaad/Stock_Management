package com.stock_management.service.implementation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.OrderDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.dto.ProductListDto;
import com.stock_management.dto.UpdateStockAmountDto;
import com.stock_management.entity.Order;
import com.stock_management.entity.Product;
import com.stock_management.entity.QProduct;
import com.stock_management.mapper.ProductMapper;
import com.stock_management.repository.ProductRepository;
import com.stock_management.repository.SupplierRepository;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@EnableAutoConfiguration
public class ProductServiceImplementation implements ProductService {
    @PersistenceContext
    private EntityManager entityManager;

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierRepository supplierRepository;

    @Autowired
    public ProductServiceImplementation(ProductRepository productRepository, ProductMapper productMapper, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.supplierRepository = supplierRepository;
    }

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"productName", "description", "dosage", "category", "box", "unitsPerBox", "unitsTotal", "pricePerBox", "pricePerUnit", "requirePrescription", "slot", "supplier"};
    static String SHEET = "Product_Import";

    // GET
    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductListDto findAllProductList(Pageable pageable) {
        Page<Product> products = productRepository.findAll((org.springframework.data.domain.Pageable) pageable);
        return getProductListDto(products);
    }

    @Override
    public CountProductsDto countAllProducts() {
        Long numberOfProducts = productRepository.count();
        CountProductsDto countProductsDto = new CountProductsDto();
        countProductsDto.setNumberOfProducts(numberOfProducts);
        return countProductsDto;
    }

    // find product by product_id in productDto
    @Override
    public ProductDto findProductById(Long productId) {
            java.util.Optional<Product> product = productRepository.findById(productId);
            var oneProduct = product.orElse(null);
            return productMapper.mapProductEntityToDto(oneProduct);
    }

    @Override
    public Long findNumberOfProductsLowInStock() {
        List<Product> products = productRepository.findAll();
        Long numberOfProductsLowInStock = products.stream().filter(product -> product.getBox() <= 3).count();
        return numberOfProductsLowInStock;
    }

    // POST
    @Override
    public void saveProduct(ProductDto productDto) {
        productDto.setUnitsTotal(productDto.getBox() * productDto.getUnitsPerBox());
        productDto.setPricePerUnit(productDto.getPricePerBox() / productDto.getUnitsPerBox());
        var saveProductInformation = productMapper.mapProductDtoToEntity(productDto);
        productRepository.save(saveProductInformation);
    }



    // PUT
    public void editProduct(ProductDto productDto) {
        var product = findProductById(productDto.getProductId());
        if (product != null) {
            product.setProductName(productDto.getProductName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setBox(productDto.getBox());
            product.setDosage(productDto.getDosage());
            product.setUnitsPerBox(productDto.getUnitsPerBox());
            product.setUnitsTotal(productDto.getBox() * productDto.getUnitsPerBox());
            product.setOldPricePerBox(productDto.getOldPricePerBox());
            product.setPricePerBox(productDto.getPricePerBox());
            product.setPricePerUnit(productDto.getPricePerBox() / productDto.getUnitsPerBox());
            product.setRequirePrescription(productDto.getRequirePrescription());
            product.setSlot(productDto.getSlot());
            product.setExpiryDate(productDto.getExpiryDate());
            product.setSupplier(productDto.getSupplier());
            productRepository.save(productMapper.mapProductDtoToEntity(product));
        } else {
            System.out.println("Product Not Found!");
        }
    }

    @Override
    public void quickStockControl(List<UpdateStockAmountDto> updateStockAmountDto) {
        List<UpdateStockAmountDto> updateStockAmountDtos = new ArrayList<>();
        for (UpdateStockAmountDto usad : updateStockAmountDto) {
            if (!Objects.equals(usad.getAmount(), 0))
            updateStockAmountDtos.add(usad);
        }
        productRepository.saveAll((updateStockAmountDtos.stream().map(this::mapProduct).collect(Collectors.toList()))
        );
    }

    private Product mapProduct(UpdateStockAmountDto updateStockAmountDto) {
        var product = productRepository.findById(updateStockAmountDto.getId());
        if (product.isPresent()) {
            var productEntity = product.get();
            var currentBoxStock = productEntity.getBox();
            var currentUnitStock = productEntity.getUnitsTotal();
            productEntity.setBox(currentBoxStock + updateStockAmountDto.getAmount());
            productEntity.setUnitsTotal(updateStockAmountDto.getAmount() * productEntity.getUnitsPerBox() + currentUnitStock);
            if (Objects.nonNull(updateStockAmountDto.getNewPrice())) {
                productEntity.setOldPricePerBox(productEntity.getPricePerBox());
                productEntity.setPricePerBox(updateStockAmountDto.getNewPrice());
            }
            return productEntity;
        } else {
            System.out.println("Product Not Found!");
        }
        return null;
    }

    @Override
    public void saveProducts(ProductListDto productListDto) {
        for (ProductDto productDto: productListDto.getProductDtos()) {
            productDto.setOldPricePerBox(0D);
            productDto.setUnitsTotal(productDto.getBox() * productDto.getUnitsPerBox());
            productDto.setPricePerUnit(productDto.getPricePerBox() / productDto.getUnitsPerBox());
        }
        var saveMultipleProduct = productListDto.getProductDtos().stream().map(productMapper::mapProductDtoToEntity).collect(Collectors.toList());
        productRepository.saveAll(saveMultipleProduct);
    }

    @Override
    public void upload(MultipartFile file) throws IOException {
        try {
            List<Product> products = excelToProducts(file.getInputStream());
            productRepository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public ProductListDto findListOfProductsByFilters(String productName, Long supplierId, String category, String slot, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildProductPredicate(productName, supplierId, category, slot);
        Page<Product> product = productRepository.findAll(predicate,pageRequest);
        List<ProductDto> productDtos = product.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
        var productListDto = new ProductListDto();
        productListDto.setProductDtos(productDtos);
        productListDto.setTotalElements(product.getNumberOfElements());
        productListDto.setTotalPages(product.getTotalPages());
        return productListDto;
    }


    private BooleanBuilder buildProductPredicate(String productName, Long supplierId, String category, String slot) {
        var qProduct = QProduct.product;
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
//        if(!requirePrescription.equals(null)) {
//            booleanBuilder.and(qProduct.requirePrescription.eq(requirePrescription));
//        }
        if(!category.equals("All")) {
            booleanBuilder.and(qProduct.category.toLowerCase().eq(category.toLowerCase()));
        }
        return booleanBuilder;
    }

    @Override
    public List<String> findAllSlots() {
        var qProduct = QProduct.product;
        var slots = new JPAQuery<Product>(entityManager).select(
                qProduct.slot).distinct().from(qProduct).fetchResults().getResults();

        List<String> list = new ArrayList<>();
        for (String s : slots) {
            list.add(s);
        }

        return list;
    }

    private ProductListDto getProductListDto(Page<Product> product) {
        if (product == null) {
            System.out.println("Product Not Found!");
            return null;
        } else {
            List<ProductDto> productDto = product.stream().map(productMapper::mapProductEntityToDto).collect(Collectors.toList());
            ProductListDto productListDto = new ProductListDto();
            productListDto.setProductDtos(productDto);
            productListDto.setTotalElements(product.getNumberOfElements());
            productListDto.setTotalPages(product.getTotalPages());
            return productListDto;
        }
    }

    public boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public List<Product> excelToProducts(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            List<Product> products = new ArrayList<Product>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Product product = new Product();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            product.setProductName(currentCell.getStringCellValue());
                            break;

                        case 1:
                            if (currentCell.getStringCellValue().equals("NULL")) {
                                product.setDescription("");
                            } else {
                                product.setDescription(currentCell.getStringCellValue());
                            }
                            break;

                        case 2:
                            if (currentCell.getStringCellValue().equals("NULL")) {
                                product.setDosage("0");
                            } else {
                                product.setDosage(currentCell.getStringCellValue());
                            }
                            break;

                        case 3:
                            product.setCategory(currentCell.getStringCellValue());
                            break;

                        case 4:
                            product.setBox((double) currentCell.getNumericCellValue());
                            break;

                        case 5:
                            product.setUnitsPerBox((int) currentCell.getNumericCellValue());
                            break;

                        case 6:
                            product.setUnitsTotal((double) currentCell.getNumericCellValue());
                            break;

                        case 7:
                            product.setOldPricePerBox((double) currentCell.getNumericCellValue());
                            break;

                        case 8:
                            product.setPricePerBox((double) currentCell.getNumericCellValue());
                            break;

                        case 9:
                            product.setPricePerUnit((double) currentCell.getNumericCellValue());
                            break;

                        case 10:
                            product.setRequirePrescription(currentCell.getBooleanCellValue());
                            break;

                        case 11:
                            product.setSlot(currentCell.getStringCellValue());
                            break;

                        case 12:
                            product.setExpiryDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;

                        case 13:
                            var supplier = supplierRepository.findSupplierBySupplierName(currentCell.getStringCellValue());
                            product.setSupplier(supplier);
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                products.add(product);
            }

            workbook.close();

            return products;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
