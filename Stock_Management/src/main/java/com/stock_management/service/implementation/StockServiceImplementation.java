package com.stock_management.service.implementation;

import com.stock_management.dto.stock.StockDto;
import com.stock_management.entity.Stock;
import com.stock_management.repository.ProductRepository;
import com.stock_management.repository.StockRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.StockService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockServiceImplementation implements StockService {
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public StockServiceImplementation(StockRepository stockRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Stock> saveStock(List<StockDto> stocksDto) {
        return stockRepository.saveAll(stocksDto.stream().filter(stockDto -> Objects.nonNull(stockDto.getQuantity()) && stockDto.getQuantity() > 0D).map(this::mapStockDtoToEntity).collect(Collectors.toList()));
    }

    private Stock mapStockDtoToEntity(StockDto stockDto) {
            Stock stock = new Stock();
            stock.setStockId(stockDto.getStockId());
            stock.setProduct(productRepository.findById(stockDto.getProductDto().getProductId()).orElse(null));
            stock.setQuantity(stockDto.getQuantity());
            stock.setUnitsPerBox(stockDto.getUnitsPerBox());
            stock.setUnitsTotal(stock.getProduct().getUnitsPerBox() * stockDto.getQuantity());
            stock.setWholeSalePrice(stockDto.getWholeSalePrice());
            stock.setPricePerBox(stockDto.getPricePerBox());
            stock.setPricePerUnit(stockDto.getPricePerBox() / stockDto.getProductDto().getUnitsPerBox());
            stock.setExpiryDate(stockDto.getExpiryDate().plusDays(1));
            stock.setCreatedBy(userRepository.findById(stockDto.getCreatedBy().getUserId()).orElse(null));
            stock.setCreatedDate(new Date());
            stock.setLastModifiedBy(userRepository.findById(stockDto.getLastModifiedBy().getUserId()).orElse(null));
            stock.setLastModifiedDate(new Date());
            return stock;
    }

    @Override
    public void editStock(StockDto stockDto) throws Exception {
        var optionalStock = stockRepository.findById(stockDto.getStockId());
        var stock = optionalStock.orElse(null);

        var optionalUser = userRepository.findById(stockDto.getLastModifiedBy().getUserId());
        var user = optionalUser.orElse(null);

        if (Objects.nonNull(stock)) {
            stock.setQuantity(stockDto.getQuantity());
            stock.setUnitsPerBox(stockDto.getUnitsPerBox());
            stock.setWholeSalePrice(stockDto.getWholeSalePrice());
            stock.setPricePerBox(stockDto.getPricePerBox());
            stock.setPricePerUnit(stockDto.getPricePerBox() / stockDto.getProductDto().getUnitsPerBox());
            stock.setExpiryDate(stockDto.getExpiryDate());
            stock.setLastModifiedBy(user);
            stock.setLastModifiedDate(new Date());
            stock.setUnitsTotal(stockDto.getQuantity() * stockDto.getProductDto().getUnitsPerBox());
            stockRepository.save(stock);
        } else {
            throw new Exception("stock.not.found");
        }
    }
}
